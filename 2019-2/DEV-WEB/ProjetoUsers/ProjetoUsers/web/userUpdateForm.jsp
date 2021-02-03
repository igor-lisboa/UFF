<%@page import="br.uff.ic.projeto.UserBean"%>
<%@include file="include/incIsValidUser.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>

<!--

TO DO:
tabela aqui e controle de login
remover paginas inuteis
testar no mySQL

-->
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>edi&ccedil;&atilde;o</title>
        <link rel="stylesheet" type="text/css" href="css/sstyle.css">
        <script type="text/javascript" src="js/validador.js"></script>
    </head>
    <body>
        <h1>User Update</h1>
        <% if (request.getAttribute("user") != null) {%> 
        <form method="POST" action='UserController' name="frmAddUser">    
            <table  class='table1'>
                <tbody>
                    <tr>
                        <td>ID : </td>
                        <td><input type="text" name="id"
                                   value="<c:out value="${user.id}" />" readonly="readonly"/><br/>(definido pelo BD)
                        </td>
                    </tr>
                    <tr>
                        <td>CPF :</td><td><input 
                                type="text" name="cpf"
                                value="<c:out value="${user.cpf}" />" /> </td>
                    </tr>
                    <tr>
                        <td>Senha : </td><td><input 
                                type="password" name="senha" 
                                value=""  /> </td> 
                    </tr>
                    <tr>
                        <td>Nome : </td><td><input
                                type="text" name="nome"
                                value="<c:out value="${user.nome}" />" /> </td> 
                    </tr>
                    <tr>
                        <td>Senha PIN : </td><td><input
                                type="text" name="pin"
                                value="<c:out value="${user.pin}" />" /> </td> 
                    </tr>
                    <tr>
                        <td>Email : </td><td><input
                                type="text" name="email"
                                value="<c:out value="${user.email}" />" /> </td> 
                    </tr>   
                    <tr>
                        <td>Administrador ? : </td><td><input
                                type="text" name="admin"
                                value="<c:out value="${user.admin}" />" /> </td> 
                    </tr> 
                    <tr>
                        <td>data acesso : </td><td><input
                                type="text" name="acesso"
                                value="<fmt:formatDate pattern="yyyy/MM/dd" value="${user.acesso}" />" readonly="readonly"/><br/>(sem edi&ccedil;&atilde;o)  </td>
                    </tr>
                    <tr>
                        <td></td><td><input  type="submit" value="Submit" /></td>
                    </tr>
                </tbody>
            </table>
        </form>
        <%} else {%>
        <%}%>
    </body>
</html>
