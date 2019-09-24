<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>  
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
    <%!
    // The JSP declaration tag using (%!) is used to declare classes, fields and methods.
    public class Usuario{
        public int id;
        public String nome;
        public String email;
    }
    %>
    <%
    String db = "projeto";
    String user = "root"; 
    String senha = ""; 
    java.sql.Connection connection;
    List<Usuario> pessoas = new ArrayList<Usuario>();
    try {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db, user, senha);
        //out.println (db+ " database successfully opened.");
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select id,nome,email from users order by id");
        while (rs.next()) {
                Usuario pessoa = new Usuario();
                pessoa.id = rs.getInt("id");
                pessoa.nome = rs.getString("nome");
                pessoa.email = rs.getString("email");
                //out.println("adiciona "+pessoa.nome);
                pessoas.add(pessoa);
        }
        statement.close();
        rs.close();
        connection.close();
    }
    catch(SQLException e) {
      out.println("SQLException caught: " +e.getMessage());
    }

    %>
    <center> 
        <h1>Lista de Usu&aacute;rios usando Conex&atilde;o SQL Convencional</h1> 
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
            <% 
                Iterator i = pessoas.iterator();
                while (i.hasNext()) {
                    Usuario x = (Usuario)i.next();
                    out.println( "<tr>" );
                    out.println( "<td>"+x.id+  "</td>");
                    out.println( "<td>"+x.nome+  "</td>");
                    out.println( "<td>"+x.email+  "</td>");
                    out.println( "</tr>" );
                }
            %>
        </tbody>
    </table>
        
    
</body>
</html>