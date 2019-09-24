/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.ic.projeto;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Eduardo
 */
@WebServlet(name = "UserController", urlPatterns = {"/UserController"})

public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT = "/userInsertForm.jsp";
    private static String UPDATE = "/userUpdateForm.jsp";
    private static String LIST_USER = "/userList.jsp";
    private UsersDao dao;
 
    public UserController() {
        super();
        dao = new UsersDao();
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = "userList";
        try {
            action = request.getParameter("action");
        } catch(Exception e){
            System.out.println("sem action");
            action = "userList";
        }
 
        if (action == null){   
            System.out.println("action null");
            forward = LIST_USER;
            request.setAttribute("usersDao", dao.getAllUsers());
        } else if (action.equalsIgnoreCase("delete")){
            System.out.println("delete");
            int id = Integer.parseInt(request.getParameter("id"));
            dao.deleteUser(id);
            forward = LIST_USER;
            request.setAttribute("usersDao", dao.getAllUsers());    
        } else if (action.equalsIgnoreCase("update")){
            System.out.println("update");
            forward = UPDATE;
            int id = Integer.parseInt(request.getParameter("id"));
            UserBean user = dao.getUserById(id);
            request.setAttribute("user", user);  
        } else if (action.equalsIgnoreCase("insert")){
            System.out.println("insert");
            forward = INSERT;
            int id = Integer.parseInt(request.getParameter("id"));
            UserBean user = dao.getUserById(id);
            request.setAttribute("user", user);
        } else if (action.equalsIgnoreCase("userList")){
            System.out.println("userList");
            forward = LIST_USER;
            request.setAttribute("usersDao", dao.getAllUsers());
        } else {
            System.out.println("insert or edit");
            forward = INSERT;
        }
 
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserBean user = new UserBean();
        // para criacao de novo usuario, id eh automatico, colocado pelo BD
        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter("id"));
            System.out.println(id);
        } catch(Exception e){
            e.printStackTrace();
            id = -2;
        } 
        user.setId(id);
        user.setCpf(request.getParameter("cpf"));
        user.setSenha(request.getParameter("senha"));
        user.setPin(request.getParameter("pin"));
        user.setNome(request.getParameter("nome"));
        user.setEmail(request.getParameter("email"));
        String administrador = request.getParameter("admin");
        if (administrador == null || "".equals(administrador)){
            administrador = "0";
        }
        user.setAdmin(Integer.parseInt(administrador));
        // data de acesso eh controlada pelo BD
        dao.checkUser(user);

        RequestDispatcher view = request.getRequestDispatcher(LIST_USER);
        request.setAttribute("usersDao", dao.getAllUsers());
        view.forward(request, response);
    }
}