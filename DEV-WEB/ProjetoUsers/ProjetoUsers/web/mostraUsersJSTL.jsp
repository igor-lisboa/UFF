<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show All Users</title>
<link rel="stylesheet" type="text/css" href="css/estilo.css">
</head>

<body>  
    <sql:setDataSource
        var="myDS"
        driver="com.mysql.jdbc.Driver"
        url="jdbc:mysql://localhost:3306/projeto"
        user="root" password=""
    />
     
    <sql:query var="listUsers"   dataSource="${myDS}">
        SELECT * FROM users  order by nome;
    </sql:query>
    <center>
    <h1>Lista Usu&aacute;rios usando JSTL </h1> 
        <%  // mensagem adicional
            String msg = request.getParameter("msg");
            if (msg != null && !"".equals(msg)){
                out.print("<h1>"+msg+"</h1>");
            }
        %>
        
    </center>
    <table class='tabela1'>
        <thead>
            <tr>
                    <th>User ID</th>
                    <th>nome</th>
                    <th>e-mail</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${listUsers.rows}">
                <tr>
                        <td ><c:out value="${user.id}" /></td>
                        <td ><c:out value="${user.nome}" /></td>
                        <td ><c:out value="${user.email}" /></td> 
                </tr>
            </c:forEach>
        </tbody>
    </table>
        
    
</body>
</html>