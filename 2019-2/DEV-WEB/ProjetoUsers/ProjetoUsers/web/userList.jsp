<%@page import="br.uff.ic.projeto.UserBean"%>
<%@include file="include/incIsValidUser.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%  // Apenas um teste de ServletContext
    // Abra em outro navegador com um outro usuario Ex: 409.097.837-80 senha 0000
    ServletContext context=getServletContext();  
    Integer c1 = (Integer) context.getAttribute("contador");
    if (c1!=null){
        int c = c1.intValue() + 1;
        c1 = c ;
    } else { 
        c1 = new Integer(0);
    }
    context.setAttribute("contador", c1);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show All Users</title>
<link rel="stylesheet" type="text/css" href="css/sstyle.css">
</head>

<jsp:useBean id="currentSessionUser" class="br.uff.ic.projeto.UserBean" scope="session">
            
            <%-- intialize bean properties --%>
            <%-- 
            <jsp:setProperty name="currentSessionUser" property="field1" value="value1" /> 
            --%>
</jsp:useBean>

<body>
    <center>
        <h1>Controle de Usu&aacute;rios</h1> 
        
        </center>
    <table class='table1'>
        <thead>
            <tr>
                <% if (currentSessionUser.getAdmin() == 1) { %>
                    <th>User ID</th>
                    <th>nome</th>
                    <th>e-mail</th>
                    <th>CPF</th>
                    <th>Senha</th>
                    <th>PIN</th>
                    <th>Administrador ?</th>
                    <th>Cria&ccedil;&atilde;o</th>
                    <th colspan='2'>opera&ccedil;&otilde;es</th>
                <%} else { %>
                    <th>nome</th>
                    <th>e-mail</th>
                <%}  %>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${usersDao}" var="user">
                <tr>
                    <% 
                        int id=((UserBean)(pageContext.findAttribute("user"))).getId() ;
                        String  cssMark = "";
                        if (currentSessionUser.getId() == id) { //if (1==1){//
                            cssMark = "style='color:blue;background-color: lightyellow;'";
                        } 
                    %>
                    <% if (currentSessionUser.getAdmin() == 1) { %>
                        <td <%= cssMark %>><c:out value="${user.id}" /></td>
                        <td <%= cssMark %>><c:out value="${user.nome}" /></td>
                        <td <%= cssMark %>><c:out value="${user.email}" /></td>
                        <td <%= cssMark %>><c:out value="${user.cpf}" /></td>
                        <td <%= cssMark %>><c:out value="${user.senha}" /></td>
                        <td <%= cssMark %>><c:out value="${user.pin}" /></td>
                        <td <%= cssMark %>><c:out value="${user.admin}" /></td>
                        <td <%= cssMark %>><fmt:formatDate pattern="dd MMM,yyyy" value="${user.acesso}" /></td>
                        <td <%= cssMark %>><a href="UserController?action=update&id=<c:out value="${user.id}"/>">Update</a></td>
                        <td <%= cssMark %>><a href="UserController?action=delete&id=<c:out value="${user.id}"/>">Delete</a></td>
                    <%} else { %>
                        <td <%= cssMark %>><c:out value="${user.nome}" /></td>
                        <td <%= cssMark %>><c:out value="${user.email}" /></td>
                    <%}  %>
                    
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <% if (currentSessionUser.getAdmin() == 1) { %>
        <p><a href="UserController?action=insert&id=0">add User</a></p>
    <% } %>
    <p> <a href="login.jsp?logout=yes">logout</a><br/></p>
    <p> <a href="login.jsp">trocar usu&aacute;rio</a><br/></p>
        
        

    Valor do Bean UserBean: 
    <br/><%= currentSessionUser %>
    <br/>nome <jsp:getProperty name="currentSessionUser" property="nome" />
    <br/>contador: <%= c1 %>
    
</body>
</html>