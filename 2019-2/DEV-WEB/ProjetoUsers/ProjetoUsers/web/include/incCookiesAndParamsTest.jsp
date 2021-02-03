<%@ page language="java" 
         contentType="text/html; charset=UTF-8"
         import="br.uff.ic.projeto.UserBean"
%><%
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
   
%>