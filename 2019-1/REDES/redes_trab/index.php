<?php
include_once("oper/funcs.php");

global $root_dir, $root_url, $host_sel, $host_porta, $host_usuario, $host_senha;

if (comando("testa")) {
    try {
        mandar_mensagem(valor('frmmsg'), valor('frmdestino'), valor('frmremetente'), 'Testando ' . date("Y-m-d H:i:s"));
    } catch (Exception $e) {
        echo $e->getMessage();
    }
} else if (comando('download_file')) {
    download_arquivo(valor('download_file'), "arq_teste.txt");
    die;
}
?>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Trabalho de Redes</title>
    <meta name="viewport">
    <style>
        * {
            font-family: arial;
        }

        body {
            justify-content: center;
            align-items: center;
        }

        textarea {
            width: 100%;
            height: 100px;
        }

        label {
            display: inline-block;
            margin: 5px;
        }

        form {
            display: block;
            width: 300px;
            text-align: left;
        }

        input {
            float: right;
        }
    </style>
</head>

<body>
    <center>
        <form method="POST">
            <h2>DADOS PARA ENVIO</h2>
            <label>Host</label>
            <!--mail.ita.locamail.com.br-->
            <input required="true" type="text" name="frmhost" value="<?= valor('frmhost') ?>" />
            <br>
            <label>Porta</label>
            <!--587-->
            <input required="true" type="number" name="frmporta" value="<?= valor('frmporta') ?>" />
            <br>
            <label>Login</label>
            <input required="true" type="text" name="frmlogin" value="<?= valor('frmlogin') ?>" />
            <br>
            <label>Senha (em b64)</label>
            <input required="true" type="password" name="frmsenha" value="<?= valor('frmsenha') ?>" />
            <br>
            <label>Rementente</label>
            <!--IGOR.RODRIGUES.LISBOA@gmail.com-->
            <input required="true" type="email" name="frmremetente" value="<?= valor('frmremetente') ?>" />
            <h2>MSG</h2>
            <label>Destinatário</label>
            <!--IGOR.RODRIGUES.LISBOA@gmail.com-->
            <input required="true" type="email" name="frmdestino" value="<?= valor('frmdestino') ?>" />
            <br>
            <label>Corpo da Mensagem</label>
            <textarea required="true" name="frmmsg"><?= valor('frmmsg') ?></textarea>
            <hr>
            <button name="testa" type="submit">Envia EMAIL</button>
            <hr>
            <a href="?download_file=teste.txt">Faz requisição de download</a>
        </form>
    </center>
</body>

</html>