/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.ic.projeto;

import java.util.Date;

/**
 *
 * @author Eduardo
 */
public class UserBean {

    private String cpf;
    private String senha;
    private String email;
    private String nome;
    private String pin;
    private Date acesso;
    private int admin;
    private int id;
    public boolean valid;

 
    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
    
    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int newAdmin) {
        admin = newAdmin;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int newID) {
        id = newID;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String newEmail) {
        email = newEmail;
    }
    public Date getAcesso() {
        return acesso;
    }

    public void setAcesso(Date newAcesso) {
        acesso = newAcesso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String newNome) {
        nome = newNome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String newSenha) {
        senha = newSenha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String newCPF) {
        cpf = newCPF;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean newValid) {
        valid = newValid;
    }
    
    @Override
    public String toString(){
        return "user: id="+id+ " cpf="+cpf+ " senha="+senha+ " pin="+getPin()+" email="+email+" nome="+nome+" valid="+valid+" admin="+admin+" acesso="+acesso;
    }

   

}
