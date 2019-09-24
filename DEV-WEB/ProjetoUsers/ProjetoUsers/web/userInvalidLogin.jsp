<%@ page language="java" 
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
%>
<%
    String errorMsg = (String) session.getAttribute("errorMsg");
    if (errorMsg == null){
        errorMsg = "fa&ccedil;a login!";
    }
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"    
    "http://www.w3.org/TR/html4/loose.dtd">

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Refresh" content="3; url=login.jsp?logout=yes" />
        <title>Invalid Login</title>
        <link rel="stylesheet" type="text/css" href="css/sstyle.css">
    </head>

    <body>
        <center>
            <h1><%= errorMsg %></h1>
        </center>
    </body>

</html>
