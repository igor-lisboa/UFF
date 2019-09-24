/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.ic.projeto;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Eduardo
 */
public class Login extends HttpServlet {
    
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    void gravaCookies(HttpServletResponse response, String email, String senha, int time){
        Cookie ckemail = new Cookie("email", email);
        ckemail.setMaxAge(time); // usualmente 24h
        response.addCookie( ckemail );
        Cookie cksenha = new Cookie("senha", senha);
        cksenha.setMaxAge(time); // usualmente 24h
        response.addCookie( cksenha );     
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String mensagemErro = "";
        String redirectURL = "mostraUsers.jsp?msg=(validado%20por%20cookies)"; // ou minhaAplicacao.jsp
        // PRIMEIRO testar a existencia de cookies email e senha
        Boolean testarCookies= true;// pode ser ligado ou desligado o trecho de teste
        if (testarCookies){ 
            // a pagina a ser carregada verifica se ha´ algum cookie no equipamento
            // se houver redireciona para uma página gravaCookies.jsp exibindo o seu valor e 
            // também um form com botão para eliminar esses cookies.
            Cookie cookie = null;
            Cookie[] cookies = null;
            String email="", senha="";

            // Get an array of Cookies associated with the this domain
            cookies = request.getCookies();
            if( cookies != null ) { // ATENCAO: no minimo havera o cookie de sessao
                // obter os cookies
                //for (int i = 0; i < cookies.length; i++) {
                //    cookie = cookies[i];
                for (Cookie aux: cookies) { // sintaxe melhor que as duas linhas acima
                    cookie = aux;
                    if ("email".equals(cookie.getName( ))){
                        email = cookie.getValue( );
                    }
                    if ("senha".equals(cookie.getName( ))){
                        senha = cookie.getValue( );
                    }
                }
                if (email != null && !"".equals(email) && senha != null && !"".equals(senha)){ 
                    boolean sucesso = AcessaBanco.conecta(email, senha); // testar com true OU false
                    if (sucesso){
                        gravaCookies(response, email, senha, 60*60*24);   
                        response.sendRedirect(redirectURL);
                        return;// nao esquecer esse return apos response.sendRedirect
                        //https://stackoverflow.com/questions/29913900/response-sendredirect-processing
                    } else {
                        // tem cookies, mas nao foram validados pelo BD
                        mensagemErro = "digite email e senha corretamente (cookies invalidos)";
                    }
                } else {
                    // nao ha cookies validos e sera exibido um form sem erros
                    mensagemErro = ""; 
                }
            }
        }
        
        
        // POSTERIORMENTE AO TESTE DE COOKIES vamos testar a existencia de parametros email e senha
        Boolean testarParametros = false; // pode ser ligado ou desligado o trecho de teste
        String parametrosDigitados = request.getParameter("parametrosDigitados");
        if (parametrosDigitados!=null && !"".equals(parametrosDigitados)){
            testarParametros = true; 
            // não estou apenas acessando a pagina, houve submit do form com um campo hidden indicando isso
        }
        String email = "", senha ="";
        if(testarParametros){
            email = request.getParameter("email");
            senha = request.getParameter("senha");
            mensagemErro="digite email e senha!";
            if(email!=null && !"".equals(email) && senha!=null && !"".equals(senha)){
                boolean sucesso = AcessaBanco.conecta(email, senha); // testar com true OU false
                if (sucesso){
                    mensagemErro=""; // conteudo ok e nem será mostrado, pois vai redirecionar
                    gravaCookies(response, email, senha, 60*60*24);
                    redirectURL ="mostraUsersJSTL.jsp?msg=(validado%20por%20parametros)";// ou minhaAplicacao.jsp
                    response.sendRedirect(redirectURL);
                    return;// nao esquecer esse return apos response.sendRedirect
                    //https://stackoverflow.com/questions/29913900/response-sendredirect-processing
                } else {
                    mensagemErro="O email e a senha nao estao cadastrados!";
                }
                
            }
            
        }
              
        
        
        // FINALMENTE, se nao houve redirecionamento entao vamos escrever o form
        // Ou seja, o form pode ser escrito se 
        // 1)você chamou diretamente (nenhum erro)
        // 2)se ha cookies errados
        // 3)se ha parametros errados, uma tentativa de login errada
        gravaCookies(response, email, senha, 0);// mata cookies antigos 0 segundos de validade
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Login</title>");  
            // voce pode usar aspas simples ' ou \" no codigo html
            out.println("<script type=\"text/javascript\" src=\"js/validador.js\"></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<form name='login' method = 'post'  action = 'Login'><br/>");
            out.println("email: <input type = 'text' name = 'email' value='"+email+"'/><br/>");
            out.println("senha: <input type = 'text' name = 'senha'/><br/>");
            out.println("<input type = 'hidden' name = 'parametrosDigitados' value='parametrosDigitados'/><br/>");
            out.println("<input type = 'button' value = 'faz login' onClick='var r = validaEmail(document.forms[\"login\"][\"email\"].value);if (r) submit(); '/><br/>");
            out.println("</form>");
            if (!"".equals(mensagemErro)){
                out.println(mensagemErro);
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        service(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        service(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
        
      

}
