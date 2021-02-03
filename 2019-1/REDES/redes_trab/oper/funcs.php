<?php
/**
 * Definições gerais de funcionamento
 */
date_default_timezone_set('America/Sao_Paulo');
set_time_limit(0);

global $root_dir, $root_url, $host_sel, $host_porta, $host_usuario, $host_senha;
// variáveis que independem de servidor
$root_dir = dirname(__FILE__) . '/../';
$root_url = 'http://localhost/redes_trab';
// variáveis que independem de servidor
$host_sel = valor('frmhost');
$host_porta = valor('frmporta');
$host_usuario = valor('frmlogin');
$host_senha = valor('frmsenha');

function valor_set($variavel, $conteudo)
{
    if (gettype($conteudo) == "array") {
        $lista = "";
        foreach ($conteudo as $item) {
            $lista .= ($lista != "" ? "," : "") . addslashes($item);
        }
        $_REQUEST[$variavel] = $lista;
        return;
    }
    $_REQUEST[$variavel] = addslashes($conteudo);
}

function grava_pag_no_disco($url, $arquivo = null)
{
    if ($arquivo == null) {
        $nomearq = tempnam(sys_get_temp_dir(), "");
    }
    file_put_contents($nomearq, file_get_contents($url));
    return $nomearq;
}

function expr_expr($delim, $prim, $seg)
{
    if (terminado_por($prim, $delim) and comecado_por($seg, $delim)) {
        return $prim . substr($seg, strlen($delim));
    } else if (terminado_por($prim, $delim) xor comecado_por($seg, $delim)) {
        return $prim . $seg;
    } else {
        return $prim . (($prim != "" and $seg != "") ? $delim : "") . $seg;
    }
}

function valor($variavel, $default = '', $entifica = false)
{
    if (isset($_REQUEST[$variavel])) {

        $ret = $_REQUEST[$variavel];
        if (gettype($ret) == "array") {
            $ret = join(",", $ret);
        }
        $ret = stripslashes($ret);
    } else {
        $ret = "";
    }

    if (nz($ret, '') === "") {
        $ret = $default;
    }

    if ($entifica) {
        return htmlentities($ret);
    } else {
        return $ret;
    }
}

function comando($variavel)
{
    if (isset($_REQUEST[$variavel])) {
        return true;
    }
    return false;
}

function limpa_request($prefixo)
{
    foreach (array_elem_comecado_por($_REQUEST, $prefixo) as $campo => $param) {
        $_REQUEST[$campo] = '';
    }
}

function array_elem_comecado_por($ar, $prefixo)
{
    $ret = array();
    foreach ($ar as $chave => $conteudo) {
        if (comecado_por($chave, $prefixo)) {
            $ret[$chave] = $conteudo;
        }
    }
    return $ret;
}

function comecado_por($var, $comeco)
{
    if (gettype($comeco) != "array") {
        return (substr($var, 0, strlen($comeco)) == $comeco);
    }
    foreach ($comeco as $cada) {
        if (substr($var, 0, strlen($cada)) == $cada) {
            return true;
        }
    }
    return false;
}

function nz($valor, $default)
{
    if ($valor === null || $valor === '') {
        return $default;
    }
    return $valor;
}

function encode_iso88591($string)
{
    $string = mb_convert_encoding($string, 'ISO-8859-1', 'auto');
    if (preg_match("/[^A-Za-z0-9\ ]/", $string)) {
        $text = "=?iso-8859-1?q?";
        for ($i = 0; $i < strlen($string); $i++) {
            if (preg_match("/[^A-Za-z0-9]/", $string[$i])) {
                $text .= "=" . dechex(ord($string[$i]));
            } else {
                $text .= $string[$i];
            }
        }
        $ret = $text . "?=";
    } else {
        $ret = $string;
    }

    return $ret;
}

function decomp_regex($masc, $txt)
{
    preg_match($masc, $txt, $cap, PREG_OFFSET_CAPTURE);
    return $cap;
}

function decomp_email_nome($txt)
{
    $ar = array();
    $temp = decomp_regex("/(?<email>[^ ]*)( *|$)(?<nome>.*)/", $txt);
    $ar["email"] = trim($temp['email'][0], '<>\'"');
    $ar["nome"] = trim($temp['nome'][0], '\'"');
    if ($ar["nome"] == "") {
        $ar["nome"] = $ar["email"];
    }
    $ar["nome"] = $ar["nome"];
    $ar["dominio"] = strtolower(substr($ar["email"], strpos($ar["email"], "@") + 1));
    return $ar;
}

function envia_email($de, $para, $assunto, $msg, $cc = "", $bcc = "", $reply_to = "", $smtp_host = "", $smtp_porta = "", $smtp_usuario = "", $smtp_senha = "")
{
    global $phpmailer_host, $phpmailer_auth, $phpmailer_port, $envia_email_erro, $envia_email_tmps, $root_dir, $root_url, $host_sel, $host_porta, $host_usuario, $host_senha;
    $envia_email_erro = '';
    try {
        require_once dirname(__FILE__) . "/class.phpmailer.php";
        $mail = new PHPMailer(true);
        $mail->IsSMTP();
        $mail->IsHTML(true);
        $mail->SMTPDebug = true;
        $smtp_host = nz($smtp_host, $phpmailer_host);
        $mail->Host = $smtp_host;
        $mail->Port = nz($smtp_porta, $phpmailer_port);
        if (nz($smtp_usuario, "") != "" and nz($smtp_senha, "") != "") {
            $mail->Username = $smtp_usuario;
            $mail->Password = base64_decode($smtp_senha);
            $mail->SMTPAuth = true;
        } else {
            $mail->SMTPAuth = false;
        }
        $mail->Subject = encode_iso88591($assunto);
        $ar = decomp_email_nome($de);
        $mail->SetFrom($ar['email'], encode_iso88591($ar['nome']));
        $ar = decomp_email_nome($para);
        $mail->AddAddress($ar['email'], encode_iso88591($ar['nome']));
        $mail->Helo = $ar["dominio"];
        if ($reply_to != "") {
            $ar = decomp_email_nome($reply_to);
            $mail->AddReplyTo($ar['email'], encode_iso88591($ar['nome']));
        }
        if ($cc != "") {
            $ar = decomp_email_nome($cc);
            $mail->AddCC($ar['email'], encode_iso88591($ar['nome']));
        }
        if ($bcc != "") {
            $ar = decomp_email_nome($bcc);
            $mail->AddBCC($ar['email'], encode_iso88591($ar['nome']));
        }
        $msg = mb_convert_encoding($msg, 'ISO-8859-1', 'UTF-8');

        $mail->Body = $msg;
        $mail->AltBody = html_entity_decode(strip_tags($msg));
        $mail->Send();
        return $mail->smtp->ret;
    } catch (Exception $e) {
        throw $e;
    }
}

function print_r_pre($obj, $retorna = false)
{
    $ret = "<pre>";
    $ret .= print_r($obj, true);
    $ret .= "</pre>";
    if ($retorna) {
        return $ret;
    }
    echo $ret;
}

function download_arquivo($arquivo, $nome_apres)
{
    ob_clean();

    header("Expires: Mon, 26 Jul 1997 05:00:00 GMT");
    header("Last-Modified: " . gmdate("D,d M YH:i:s") . " GMT");
    header("Cache-Control: no-cache, must-revalidate");
    header("Pragma: no-cache");
    header("Content-Type: application/octet-stream;");
    header("Content-Disposition: attachment; filename=\"{$nome_apres}\"");
    header("Content-Description: PHP Generated Data");
    header('Content-Transfer-Encoding: binary');
    header('Accept-Ranges: bytes');
    readfile($arquivo);
}

function all_trim($texto, $delim = array(' '))
{
    $achou = true;
    while ($achou) {
        $achou = false;
        foreach ($delim as $item) {
            if (starts_with($texto, $item)) {
                $achou = true;
                $texto = substr($texto, strlen($item));
            }
            if (ends_with($texto, $item)) {
                $achou = true;
                $texto = substr($texto, 0, -strlen($item));
            }
        }
    }
    return $texto;
}

function starts_with($texto, $comeco)
{
    return comecado_por($texto, $comeco);
}

function ends_with($texto, $final)
{
    return terminado_por($texto, $final);
}

function terminado_por($var, $termino)
{
    if (gettype($termino) === "string") {
        $termino = array($termino);
    }
    foreach ($termino as $item) {
        $tam = strlen($item);
        $result = (substr($var, strlen($var) - $tam, $tam) == $item);
        if ($result) {
            return true;
        }
    }
    return false;
}

function root_url($compl = "")
{
    global $root_url;
    $ret = str_replace("\\", "/", $root_url);
    $ret = expr_expr("/", $ret, "/");
    $ret = expr_expr("/", $ret, $compl);
    $ret = expr_expr("/", $ret, "/");
    return $ret;
}

function texto_aleatorio($qtd, $maiusc = true, $minusc = true, $complex = false, $num = true)
{
    $opc_maiusc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    $opc_minusc = strtolower($opc_maiusc);
    $opc_complex = "!@#$&*_-+=?|\/";
    $opc_num = "0123456789";
    $opc = "";
    $txt = "";
    if ($maiusc) {
        $opc .= $opc_maiusc;
    }
    if ($minusc) {
        $opc .= $opc_minusc;
    }
    if ($complex) {
        $opc .= $opc_complex;
    }
    if ($num) {
        $opc .= $opc_num;
    }
    for ($z = 1; $z <= $qtd; $z++) {
        $pos = mt_rand(0, (strlen($opc) - 1));
        $txt .= substr($opc, $pos, 1);
    }
    return $txt;
}

function decomp_email($email)
{
    $ret['NOME'] = '';
    $ret['EMAIL'] = '';
    preg_match('/[\'"](?<NOME>.*)[\'"](?<EMAIL>.*)/', $email, $decomp, PREG_OFFSET_CAPTURE);
    if (array_key_exists('EMAIL', $decomp)) {
        $ret['EMAIL'] = all_trim($decomp['EMAIL'][0], array(" ", "[", "]", "<", ">"));
        if (array_key_exists('NOME', $decomp)) {
            $ret['NOME'] = all_trim($decomp['NOME'][0]);
        }
    } else {
        $email = all_trim($email, array(" ", "[", "]", "<", ">"));
        if ($pos = strrpos($email, " ")) {
            $ret['NOME'] = substr($email, 0, $pos);
            $ret['EMAIL'] = substr($email, $pos + 1);
        } else {
            $ret['EMAIL'] = $email;
        }
    }
    $ret['COMPLETO'] = $ret['NOME'] . " <" . $ret['EMAIL'] . ">";
    $ret['EMAIL'] = all_trim($ret['EMAIL'], array(" ", "[", "]", "<", ">"));
    $ret['DOMINIO'] = strtolower(substr($ret['EMAIL'], strpos($ret['EMAIL'], "@") + 1));
    $ret["EMAIL_PADRONIZADO"] = ($ret["NOME"] ? ("\"" . $ret["NOME"] . "\"" . " ") : "") . "<" . $ret["EMAIL"] . ">";
    if ($ret['NOME']) {
        $pos = strpos($ret['NOME'], " ");
        if ($pos == 0) {
            $pos = strlen($ret['NOME']);
        }
        $ret['NOME_PRIMEIRO'] = substr($ret['NOME'], 0, $pos);
        $ret['NOME_RESTANTE'] = substr($ret['NOME'], $pos + 1);
    }
    return $ret;
}

function email_to_sendmail($email_esend)
{
    $decomp = decomp_email($email_esend);
    if ($decomp["EMAIL"] == "") {
        return "";
    }
    return "<" . $decomp["EMAIL"] . ">" . ($decomp["NOME"] != "" ? (" " . $decomp["NOME"]) : "");
}

function mandar_mensagem($msg = "", $destinatario = "", $remetente = "", $titulo = "", $cc = "", $bcc = "", $replyto = "")
{
    global $host_sel, $host_porta, $host_usuario, $host_senha;
    try {
        $smtpret = envia_email(email_to_sendmail($remetente), email_to_sendmail($destinatario), $titulo, $msg, "", "", "", $host_sel, $host_porta, $host_usuario, $host_senha);
    } catch (Exception $ex) {
        echo $ex->getMessage();
        return false;
    }
    print_r_pre($smtpret);
    return true;
}

function files_by_date($folder)
{
    $files = array();
    foreach (scandir($folder) as $file) {
        $path = $folder . '/' . $file;
        if (is_dir($path)) {
            continue;
        }

        $files[filemtime($path)]['path'] = $path;
        $files[filemtime($path)]['file'] = $file;
    }
    krsort($files);
    return $files;
}

function tenta_enviar_telnet($msg, $host, $porta, $remetente, $login, $senha, $destino)
{
    $destino_ar = decomp_email($destino);
    $remetente_ar = decomp_email($remetente);

    $ar = array();
    $telnet = "TELNET " . $host . " " . $porta;
    array_push($ar, $telnet);
    $ehlo = "EHLO " . $destino_ar["DOMINIO"];
    array_push($ar, $ehlo);
    $auth_login = "AUTH LOGIN";
    array_push($ar, $auth_login);
    //login
    array_push($ar, $login);
    //senha
    array_push($ar, $senha);
    //&lt;   =>     <
    //&gt;   =>     >
    $mailfrom = "MAIL FROM:&lt;" . $remetente_ar['EMAIL'] . "&gt;";
    array_push($ar, $mailfrom);
    $rcptto = "RCPT TO:&lt;" . $destino_ar['EMAIL'] . "&gt;";
    array_push($ar, $rcptto);
    $data = "DATA";
    array_push($ar, $data);
    //cabecalho
    $from = "FROM: CMD &lt;" . $remetente_ar['EMAIL'] . "&gt;";
    array_push($ar, $from);
    $to = "TO: Destino &lt;" . $destino_ar['EMAIL'] . "&gt;";
    array_push($ar, $to);
    $subject = "SUBJECT: Assunto Fixo CMD";
    array_push($ar, $subject);
    //date('D, d M Y H:i:s O')
    //date('d-m-y')
    //$date = "DATE:".date('D, d M Y H:i:s O');
    //array_push($ar, $date);
    //msg
    array_push($ar, $msg);
    //espaco ponto espaco
    $esp_ponto_esp = "\r\n\r\n.\r\n\r\n";
    array_push($ar, $esp_ponto_esp);
    print_r_pre($ar);
$retorno=null;
    if (valor('socket')) {
        echo "socket";
        $socket = fsockopen("udp://127.0.0.1", 23);
        if ($socket) {
            echo "Conectou!<br /><br />";
        } else {
            echo "Falha na Conexão!<br /><br />";
            return;
        }
        $exec="";
        foreach ($ar as $item) {
            echo $item . "<br>";
            $exec.= $item . "\r\n";
        }
        fputs($socket, $exec);
        fclose($socket);
    } else {
        $exec="";
        foreach ($ar as $item) {
            echo $item . "<br>";
            $exec.= $item . "\r\n";
        }
        echo "shell";
        $retorno = shell_exec($item);
        echo $retorno;
        $retorno=null;
        echo "exec";
        exec($item, $retorno);
        print_r_pre($retorno);
    }
}

function get_lines($socket)
{
    $data = "";
    while ($str = @fgets($socket, 515)) {
        $data .= $str;
        // if 4th character is a space, we are done reading, break the loop
        if (substr($str, 3, 1) == " ") {
            break;
        }
    }
    return $data;
}

//telnet [host de envio] [porta]
//EHLO [dominio do servidor de destino]
//AUTH LOGIN
//[usuario em base 64]
//[senha em base 64]
//MAIL FROM:<[email origem]>
//RCPT TO:<[email destino]>
//DATA
//SUBJECT: [assunto]
//[msg]
//
//.
