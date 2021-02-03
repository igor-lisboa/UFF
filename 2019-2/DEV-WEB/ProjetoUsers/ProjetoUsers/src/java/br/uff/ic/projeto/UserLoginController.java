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
import javax.servlet.http.HttpSession;


public class UserLoginController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {	    
            UserBean user = new UserBean();
            String cpf = (String) request.getSession().getAttribute("cpf");
            String senha = (String) request.getSession().getAttribute("senha");
            //String vaiGravarCookie = "nao";
            String at_setCookie = (String) request.getSession().getAttribute("setCookie");
            String ck_pin = (String) request.getSession().getAttribute("ck_pin");
            user.setCpf(cpf);
            user.setSenha(senha);
            user.setPin(ck_pin);
            boolean killCookie = false;
            if ((at_setCookie == null || "".equals(at_setCookie)) &&
                 cpf != null && !"".equals(cpf)   ){
                killCookie = true;
            }
            //user.setCPF(request.getParameter("cpf"));
            //user.setSenha(request.getParameter("senha"));
            user = UsersDao.login(user);
            if (user.isValid()) {
                // se forem validos e mandou gravar cookie, grava entao os cookies de sessao
                if (at_setCookie!=null && !"".equals(at_setCookie)){
                    //vaiGravarCookie = "sim";
                    // Create cookies for auto login.      
                    Cookie ckpin = new Cookie("ck_pin", user.getPin());

                    // cookie vai expirar em 24h
                    System.out.println("grava cookie");
                    ckpin.setMaxAge(60*60*24); 
                    response.addCookie( ckpin );
                } else {
                    // se nao mandou gravar cookie e se nao esta vindo de um Cookie previamente gravado
                    if (killCookie){           
                        System.out.println("MATA eventuais atributos de login");
                        request.getSession().setAttribute("cpf", "");
                        request.getSession().setAttribute("senha", "");
                        System.out.println("MATA cookie");
                        Cookie ckpin = new Cookie("ck_pin", user.getPin());
                        // cookie vai expirar agora
                        ckpin.setMaxAge(0); 
                        response.addCookie( ckpin );
                    }
                }
                HttpSession session = request.getSession(true);	    
                session.setAttribute("currentSessionUser",user); 
                response.sendRedirect("UserController?action=userList"); //logged-in page //userLogged    		
            } else {
                HttpSession session = request.getSession(true);	    
                session.setAttribute("currentSessionUser",user);
                response.sendRedirect("userInvalidLogin.jsp"); //error page 
            }
        } 	
        catch (Throwable theException) 	{
            System.out.println(theException); 
        }
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
