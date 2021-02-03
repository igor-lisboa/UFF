package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import br.uff.ic.projeto.UserBean;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/include/incCookiesAndParamsTest.jsp");
  }

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

      out.write('\n');
      out.write('\n');

    String cpf = request.getParameter("cpf");
    String senha = request.getParameter("senha");
    String setCookie = request.getParameter( "setCookie");
    String ck_pin="";
    String checked = "checked";
    if (setCookie == null || "".equals(setCookie)  )
        checked = "";
    String logout = request.getParameter("logout");
    if (logout == null || "".equals(logout)){ // se nao for um pedido explicito de logout
        // a pagina a ser carregada verifica se ha´ algum cookie no equipamento
        // se houver redireciona para uma página gravaCookies.jsp exibindo o seu valor e 
        // também um form com botão para eliminar esses cookies.
        String redirectURL = "nada";
        Cookie cookie = null;
        Cookie[] cookies = null;

        // Get an array of Cookies associated with the this domain
        cookies = request.getCookies();
        if( cookies != null ) { // ATENCAO: no minimo havera o cookie de sessao
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                if ("ck_pin".equals(cookie.getName( ))){
                    ck_pin = cookie.getValue( );
                }
            }
            if (ck_pin != null && !"".equals(ck_pin)){
                if (cpf != null && !"".equals(cpf) && senha != null && !"".equals(senha)){ // pin é parametro de retorno
                    request.getSession().setAttribute("cpf", cpf);
                    request.getSession().setAttribute("senha", senha);
                } else {
                    cpf ="";
                    request.getSession().setAttribute("cpf", cpf);
                    senha ="";
                    request.getSession().setAttribute("senha", senha);
                }
                setCookie = "";
                request.getSession().setAttribute("setCookie", setCookie);
                request.getSession().setAttribute("ck_pin", ck_pin);
                    
                redirectURL = "UserLoginController"; 
                response.sendRedirect(redirectURL);
                return;// nao esquecer esse return apos response.sendRedirect
                //https://stackoverflow.com/questions/29913900/response-sendredirect-processing
            }
        }
        // Se não houver cookies no cliente, a pagina cookies.jsp apresentará um form que, quando estiver
        // corretamente prenchido (todos os campos requeridos) 
        // irá coletar esses valores a serem gravados por gravaCookies.jsp
        if (cpf != null && !"".equals(cpf) && senha != null && !"".equals(senha)){ // pin é parametro de retorno
            request.getSession().setAttribute("cpf", cpf);
            request.getSession().setAttribute("senha", senha);
            if(setCookie == null || "".equals(setCookie)){
                setCookie = "";
            }
            request.getSession().setAttribute("setCookie", setCookie);
            ck_pin = "";
            request.getSession().setAttribute("ck_pin", ck_pin);
            redirectURL = "UserLoginController"; 
            response.sendRedirect(redirectURL);
            return;// nao esquecer esse return apos response.sendRedirect
            //https://stackoverflow.com/questions/29913900/response-sendredirect-processing
        }
    } else {
        // faz logout
        UserBean ub =  (UserBean) session.getAttribute("currentSessionUser");
        if (ub != null ){
            ub.setValid(false);
        }
        // mata cookies no equipamento
        System.out.println("MATA cookie");
        Cookie ckpin = new Cookie("ck_pin", "nothing");
        // cookie vai expirar agora
        ckpin.setMaxAge(0); 
        response.addCookie( ckpin );
    }
    
    if (cpf == null || "".equals(cpf)  )
        cpf = "";
    if (senha == null  || "".equals(senha)  )
        senha = "";
    
//MVC https://www.youtube.com/watch?v=3Rlm70kz0Kc 
//https://www.youtube.com/playlist?list=PLE0F6C1917A427E96
//http://met.guc.edu.eg/OnlineTutorials/JSP%20-%20Servlets/Full%20Login%20Example.aspx
   

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/sstyle.css\">\n");
      out.write("        <script type=\"text/javascript\" src=\"js/validador.js\"></script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <form name=\"login\" action = \"login.jsp\" onsubmit=\"return validateForm();\" method = \"GET\">\n");
      out.write("              \n");
      out.write("        <!-- experimente table2 ... 4 -->\n");
      out.write("        <table class='table1'>\n");
      out.write("\t<thead>\n");
      out.write("                <tr >\n");
      out.write("\t\t<th class='text-left' >\n");
      out.write("                    Acesso:\n");
      out.write("\t\t</th>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("\t\t<th class='text-left'>\n");
      out.write("                    \n");
      out.write("\t\t</th>\n");
      out.write("\t\t<th class='text-left'>\n");
      out.write("                    \n");
      out.write("\t\t</th>\n");
      out.write("                </tr>\n");
      out.write("        </thead>\n");
      out.write("        <tbody class='table-hover'>\n");
      out.write("            <tr>\n");
      out.write("                <td>CPF</td><td><input type = \"text\" name = \"cpf\" value = '");
      out.print( cpf );
      out.write("' /></td>\n");
      out.write("            </tr>\n");
      out.write("            <tr>\n");
      out.write("                <td>senha</td><td><input type = \"password\" name = \"senha\"  value = '");
      out.print( senha );
      out.write("' /></td>\n");
      out.write("            </tr>\n");
      out.write("            <tr>\n");
      out.write("                <td></td><td><input type = \"submit\" value = \"submit\" />&nbsp;<input type=\"button\" name=\"bt1\" value=\"outra forma\" onclick=\"var r = validateForm();if (r) submit(); \" /></td>\n");
      out.write("            </tr>\n");
      out.write("            <tr>\n");
      out.write("                <td><strong>&nbsp;auto&nbsp;login?</strong></td><td><input  type=\"checkbox\" name=\"setCookie\" id=\"setCookie\" value=\"yes\" ");
      out.print( checked );
      out.write("/> (n&atilde;o marque em computadores p&uacute;blicos)</td>\n");
      out.write("            </tr>\n");
      out.write("            \n");
      out.write("        </tbody>\n");
      out.write("        </table>\n");
      out.write("        </form>\n");
      out.write("           \n");
      out.write("    </body>\n");
      out.write("</html>\n");
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
