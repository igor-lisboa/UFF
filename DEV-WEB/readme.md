https://www.youtube.com/channel/UCi3b60P2qkh6S6OWPpp-tJA

1 prova (x8)
1 trab (x2)

https://www.w3schools.com/html/


-cookie
-javascript
-servlet
-jsp


--FORM
	MARCAR OPCOES
	MULTI OPCOES
	BOTOES
	AREAS DE EDICAO
	COMBOBOX (1 OU MAIS SELECOES)
	LISTA
	CHECKBOX
	RADIO BUTTONS
	ACTION (destino)
	METODO (GET / POST)
	
	

HTML

<!DOCTYPE html>
<html>
<head>
<title>Page Title</title>
</head>
<body>

<h1><mark>This</mark> is a <sub>Heading</sub></h1>
<p>This <q>is</q> a <del>paragraph</del>.</p>

<blockquote cite="http://www.worldwildlife.org/who/index.html"> 
For 50 years, WWF has been protecting the future of nature. The world's leading conservation organization, WWF works in 100 countries and is supported by 1.2 million members in the United States and close to 5 million globally.</blockquote>

<bdo dir="rtl">What a beautiful day!</bdo>

<p>The <abbr title="World Health Organization">WHO</abbr> was founded in 1948.</p>

<table> 
<caption>Names</caption>
  <tr> 
    <th>First Name</th> 
    <th>Last Name</th> 
    <th>Points</th> 
  </tr> 
  <tr> 
    <td>Jill</td> 
    <td>Smith</td> 
    <td>50</td> 
  </tr> 
</table>

<ul style="list-style-type:square;"> 
  <li>Coffee</li> 
  <li>Tea</li> 
  <li>Milk</li> 
</ul>

<ol type="A"> 
  <li>Coffee</li> 
  <li>Tea</li> 
  <li>Milk</li> 
</ol>

<ol type="I"> 
  <li>Coffee</li> 
  <li>Tea</li> 
  <li>Milk</li> 
</ol>

<dl> 
	<dt>Coffee</dt> 
	<dd>- black hot drink</dd>
  	<dt>Milk</dt> 
  	<dd>- white cold drink</dd>
</dl>

<p>Code example: 
<code>var person;</code>
</p>

<p>Code example:</p>
<pre>
<code>var person = {
  firstName:"John",
  lastName:"Doe"
}</code>
</pre>

<p>A simple math equation: <var>x</var> = 3 + 2; </p>

<form> 
<input type="button" value="OK"> 
</form>

<form> 
<input type="radio" name="gender"
 value="male"> Male 
<input type="radio" name="gender"
 value="female"> Female 
</form>

<form action="/action_page.php"> 
Name: <input type="text" name="name"> 
<input type="submit"> 
</form>

<form action="/action_page.php"> 
<select name="cars"> 
</select> 
</form>

<form action="/action_page.php"> 
<select name="cars"> 
<option value="Volvo">Volvo</option> 
<option value="Ford">Ford</option> 
</select> 
</form>

<form action="/action_page.php"> 
<input type="text" name="username"> 
<input type="submit" value="Submit Form"> 
</form>

<form action="/action_page.php"> 
  Favorite color: 
  Blue <input value="blue" type="radio" name="color">
Red <input value="red" type="radio" name="color"> 
  <input type="submit"> 
</form>

<form action="/action_page.php"> 
<button>Click Me</button>
</form>

</body>
</html>



TABLE

<table style="width:100%">
  <tr>
    <th>Firstname</th>
    <th>Lastname</th> 
    <th>Age</th>
  </tr>
  <tr>
    <td>Jill</td>
    <td>Smith</td> 
    <td>50</td>
  </tr>
  <tr>
    <td>Eve</td>
    <td>Jackson</td> 
    <td>94</td>
  </tr>
</table>


LIST

<ul>
  <li>Coffee</li>
  <li>Tea</li>
  <li>Milk</li>
</ul>

<pre> - formatacao solta </pre>
<br>
<h1> - titulo </h1>
<br>
<em> - enfatizar </em>
<br>
<mark> - sublinhar </mark>
<br>
<sub> - desce e diminui texto </sub>
<br>
<del> - risca </del>
<br>
<q> - bota entre aspas </q>
<br>
<blockquote> - centraliza texto (utilizacao p citacao) </blockquote>
<br>
<bdo> - utilizando dir permite mudar direcao do texto (rtl ou ltr) </bdo>
<br>
<abbr> - utilizando title vc coloca um tooltip em cima de siglas </abbr>
<br>
<!-- -comentario-->
<caption> - na table pode ser usado como titulo </caption>
<br>
<ol> - gera lista ordenada </ol>
<br>


POST
suas informacoes nao estao protegidas
protocolo http trafega pela internet sem encriptacao (por isso é desprotegido)


GET
joga informacoes pela url
colocando ? infs e dividindo por &


BUTTON TYPES
submit - manda dados para serem processados
reset - limpa campo

RADIO
coloque todos input radio c msm name


INPUT
size
value
name
type
placeholder
disabled
maxlength
readonly

TEXTAREA


TABLE
colspan 2 ocupa 2 epacos ma linha
rowspan 2 ocupa 2 espacos na coluna


DIAGRAMA DE CLASS
DIAGRAMA DE ENTIDADE DE RELACIONAMENTO
FLUXO DE PAGINAS

<ul><li>LIST</li></ul>
style no ul => list-style-type:square


APLICACAO WEB
intregar ambiente com uma aplicacao convencional
programacao misturada com visual
SERVLET out.println => html

JSP = toda html e no meio programacao (dinamica)

JAVASCRIPT roda no navegador

COOKIES 
Variavel de sessao para saber quem é vc


SERVLET
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class AloMundo extends HttpServlet {
public void doGet(HttpServletRequest request,
HttpServletResponse response) throws IOException {
PrintWriter out = response.getWriter();
response.setContentType("text/html");
out.println("<HTML><BODY>");
out.println("<P>Servlet Alo Mundo...</P>");
for (int i = 0; i < 10; i++) {
out.println(i +"<BR>");
}
out.println("</BODY></HTML>");
}
}


JSP
<HTML>
<BODY>
<P>JSP Alo Mundo...</P>
<% for (int i = 0; i < 10; i++) { %>
<%= i %><BR>
<% } %>
</BODY>
</HTML>


Servlet x JSP
• Servlet: – Java é a linguagem principal – Indicado para implementar regras de negócio e manipulação de dados
• JSP: – HTML é a linguagem principal – Indicado para interface com o usuário


URL
protocolo
dominio
porta
caminho
recurso
parametros e argumentos
http://www.xpto.com:8080/pub/exemplo.jsp?p1=v1&p2=v2




doGet -> funcao overide q pega o get do servlet
doPost -> funcao overide q pega o post do servlet
processRequest -> pega tanto o get quanto o post

servlet
request.getParameter(STRING VARIAVEL_NOME)

jsp
<% %>
...
> 05/09
# JDBC
* acesso a BD
  * necessário baixar driver p mysql
# JSP
* java servlet pages
  * pagina html (.jsp)
  * facilita
  * pode printar dentro dos <% %>
    * out.println("")
    * concat eh +
## cookie
* addCookie(cookie)
* grava msgm no navegador
## sessao
* setAttribute
* getAttribute
* getAttributeNames
* setMaxInactiveInterval
* invalidate
  * finaliza sessao
## redirect
### ocorre antes de qualquer impressao
* sendRedirect(location)
* <jsp:foward page="http://pagina.com">
## inclusao de arquivos
* <% @include ... %>
  * primeira vez q executa e ate fechar servlet vai ser assim
* <jsp:include .../>
  * a cada mudanca vai puxar alteracao

# novo proj
* java web
* nome do proj
* tomcat ou grasfish
* n usar nenhum framework
* finalizar


# SERVLETS E JSP EM CONJUNTO
* MVC - MODEL VIEW CONTROLLER
  * modelo
    * processos de analise
      * BD
  * visao
    * interface
  * controle
    * executam casos de uso do projeto

## modelo
* so conhece outros modelos
* JavaBeans

## visao
* conhece apenas o modelo
* JSP

## controle
* conversa com o modelo e com a view
* sabe d outras entidades de controle
* SERVLET

## vantagens
* alto grau de utilizacao
* responsabilidades claras
* possibilidades de multiplas visualizacoes


# requestDispatcher

# PAGINAS
* HOME
* FILTRO
* PRODUTO
* CARRINHO
* PAGAMENTO
    * CONFIRMACAO DE PAGAMENTO
* LOGIN
    * CADASTRO LOGIN
	
	
	
***
# NETBEANS 8.2 (tudo)
# XAMPP (MYSQL/PHPAdmin)
# ACESSO AO BANCO DE DADOS
* aba de servicos do NETBEANS
* DRIVER MYSQL
    * https://dev.mysql.com/downloads/connector/j/
# <%= campo %>
* usado para printar conteudo no jsp
# <%@ file="caminho/arq.jsp" %>
* usado para incluir arqs
# <%@ page language="java" contentType="text/html; charset=UTF-8" import="bd.uff.ic.projeto.UserBean" %>
* exemplo de inicio de arquivo jsp que trata dados
# sessao
* HttpSession session = request.getSession(true);
* session.getAttribute("currentSessionUser");
# cookie
* Cookie ckpin = new Cookie("ck_pin","nothing");
    * cria cookie
* ckpin.setMaxAge(0);
    * define tempo de vida
# equals
* cpf.equals("")
    * verifica se o cpf esta vazio
# redirect
* response.sendRedirect("UserControler?action=UserList");
# meta REFRESH
* <meta http-equiv="Refresh" content="3; url=Login.jsp?Logout=yes">
# RequestDispatcher
* RequestDispatcher view = request.getRequestDispatcher(forward);
* view.forward(request, response);
