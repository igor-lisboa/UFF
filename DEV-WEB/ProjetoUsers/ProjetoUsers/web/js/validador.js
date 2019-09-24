function validateForm() {
    var cpf = document.forms["login"]["cpf"].value;
    cpf = cpf.replace(/[^\d]+/g,'');// retira o que nao for numerico
    //alert(cpf);
    if (cpf === "") {
        alert("preencha o campo CPF");
        return false;
    }
    if (testaCPF(cpf) === false){
        alert("o CPF digitado eh invalido");
        return false;
    }
    return true;
}
 
function validaEmail(email) {
    // valida a expressao, estude https://regexone.com/
    // https://www.w3resource.com/javascript/form/email-validation.php
    if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)){
        return (true)
    }
    alert("You have entered an invalid email address!")
    return (false)
}

function testaCPF(strCPF) { // testa o digito verificador
    var Soma;
    var Resto;
    Soma = 0;
    if (strCPF === "00000000000") return false;
     
    for (i=1; i<=9; i++) 
        Soma = Soma + parseInt(strCPF.substring(i-1, i)) * (11 - i);
    Resto = (Soma * 10) % 11;
   
    if ((Resto === 10) || (Resto === 11))  
        Resto = 0;
    if (Resto !== parseInt(strCPF.substring(9, 10)) ) 
        return false;
   
    Soma = 0;
    for (i = 1; i <= 10; i++) 
        Soma = Soma + parseInt(strCPF.substring(i-1, i)) * (12 - i);
    Resto = (Soma * 10) % 11;
   
    if ((Resto === 10) || (Resto === 11))  
        Resto = 0;
    if (Resto !== parseInt(strCPF.substring(10, 11) ) ) 
        return false;
    return true;
}