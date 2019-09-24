package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;

public final class mostraUsers_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


    // The JSP declaration tag using (%!) is used to declare classes, fields and methods.
    public class Usuario{
        public int id;
        public String nome;
        public String email;
    }
    
  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("  \n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\n");
      out.write("    \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("<title>Show All Users</title>\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/estilo.css\">\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("    ");
      out.write("\n");
      out.write("    ");

    String db = "projeto";
    String user = "root"; 
    String senha = ""; 
    java.sql.Connection connection;
    List<Usuario> pessoas = new ArrayList<Usuario>();
    try {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db, user, senha);
        out.println (db+ " database successfully opened.");
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

    
      out.write("\n");
      out.write("    <center> \n");
      out.write("        <h1>Lista de Usu&aacute;rios usando Conexao SQL Convencional</h1> \n");
      out.write("        ");
  // mensagem adicional
            String msg = request.getParameter("msg");
            if (msg== null || "".equals(msg)){
                out.print("<h1>"+msg+"</h1>");
            }
        
      out.write("\n");
      out.write("        </center>\n");
      out.write("    <table class='tabela1'>\n");
      out.write("        <thead>\n");
      out.write("            <tr>\n");
      out.write("                    <th>User ID</th>\n");
      out.write("                    <th>nome</th>\n");
      out.write("                    <th>e-mail</th>\n");
      out.write("            </tr>\n");
      out.write("        </thead>\n");
      out.write("        <tbody>\n");
      out.write("            ");
 
                Iterator i = pessoas.iterator();
                while (i.hasNext()) {
                    Usuario x = (Usuario)i.next();
                    out.println( "<tr>" );
                    out.println( "<td>"+x.id+  "</td>");
                    out.println( "<td>"+x.nome+  "</td>");
                    out.println( "<td>"+x.email+  "</td>");
                    out.println( "</tr>" );
                }
            
      out.write("\n");
      out.write("        </tbody>\n");
      out.write("    </table>\n");
      out.write("        \n");
      out.write("    \n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
