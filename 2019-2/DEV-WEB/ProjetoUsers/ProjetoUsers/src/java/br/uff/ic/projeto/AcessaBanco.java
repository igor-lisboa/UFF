/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.ic.projeto;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Eduardo
 */
public class AcessaBanco {
    static Connection currentCon = null;
    static ResultSet rs = null;
    public static boolean conecta(String email, String senha){
        Statement stmt = null;
        boolean sucesso = false;
        String searchQuery
                = "select * from users where email='"
                + email
                + "' AND senha='"
                + senha
                + "'"; 
        
        try {
            System.out.println(searchQuery);
            currentCon = Database.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean more = rs.next();
            // if user does not exist set the isValid variable to false
            if (!more) {
                System.out.println("Sorry, you are not a registered user! Please sign up first");
            } else if (more) {//if user exists set the isValid variable to true
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                sucesso = true;
            }
        } catch (Exception ex) {
            System.out.println("Login failed: An Exception has occurred! " + ex.getMessage());
        } finally { //some exception handling
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
                rs = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                }
                stmt = null;
            }
            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (Exception e) {
                }
                currentCon = null;
            }
        }
        return sucesso;
        // fim do codigo de login
    }
    
}
