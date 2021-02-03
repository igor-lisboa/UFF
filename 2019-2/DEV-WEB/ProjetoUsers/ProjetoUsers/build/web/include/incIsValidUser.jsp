<%@ page language="java" 
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256" 
         import="br.uff.ic.projeto.UserBean"
         
%>
<%
    UserBean ub =  (UserBean) session.getAttribute("currentSessionUser");
    if (ub == null || !ub.isValid()){
        //if (ub == null)
            session.setAttribute("errorMsg", "voc&ecirc; precisa fazer login com um usu&aacute;rio e senha v&aacute;lidos!");
        //else
        //    session.setAttribute("errorMsg", "usu&aacute;rio inv&aacute;lido!");
        String redirectURL = "userInvalidLogin.jsp"; 
        response.sendRedirect(redirectURL);
        return;
    }
%>
