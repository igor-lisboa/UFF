<%-- include dinamico para ser usado com programacao < jsp : include page="incCookiesAndParamsTest.jsp" / > --%>
<%-- use o include estatico abaixo /> --%>
<%@include file="include/incCookiesAndParamsTest.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="css/sstyle.css">
        <script type="text/javascript" src="js/validador.js"></script>
    </head>
    <body>
        <form name="login" action = "login.jsp" onsubmit="return validateForm();" method = "GET">
              
        <!-- experimente table2 ... 4 -->
        <table class='table1'>
	<thead>
                <tr >
		<th class='text-left' >
                    Acesso:
		</th>
                </tr>
                <tr>
		<th class='text-left'>
                    
		</th>
		<th class='text-left'>
                    
		</th>
                </tr>
        </thead>
        <tbody class='table-hover'>
            <tr>
                <td>CPF</td><td><input type = "text" name = "cpf" value = '<%= cpf %>' /></td>
            </tr>
            <tr>
                <td>senha</td><td><input type = "password" name = "senha"  value = '<%= senha %>' /></td>
            </tr>
            <tr>
                <td></td><td><input type = "submit" value = "submit" />&nbsp;<input type="button" name="bt1" value="outra forma" onclick="var r = validateForm();if (r) submit(); " /></td>
            </tr>
            <tr>
                <td><strong>&nbsp;auto&nbsp;login?</strong></td><td><input  type="checkbox" name="setCookie" id="setCookie" value="yes" <%= checked %>/> (n&atilde;o marque em computadores p&uacute;blicos)</td>
            </tr>
            
        </tbody>
        </table>
        </form>
           
    </body>
</html>
