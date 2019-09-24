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
public class UsersDao {
    
    // inicio do codigo de login
    static Connection currentCon = null;
    static ResultSet rs = null;

    public static UserBean login(UserBean bean) {
        //preparing some objects for connection 
        Statement stmt = null;
        String cpf = bean.getCpf();
        String senha = bean.getSenha();
        
        // TO DO
        // dependendo do que chegou use CPF/senha ou pin
        String searchQuery
                = "select * from users where cpf='"
                + cpf
                + "' AND senha='"
                + senha
                + "'"; 
        
        
        String pin = bean.getPin();
        if (pin != null && !"".equals(pin)){
            searchQuery
                = "select * from users where pin='"+ pin + "'"; 
        }
                    

        // "System.out.println" prints in the console; Normally used to trace the process
        System.out.println("seu cpf eh " + cpf);
        System.out.println("sua senha eh " + senha);
        System.out.println("sua pin eh " + pin);
        System.out.println("Query: " + searchQuery);
        try {
            // projeto eh o nome do banco
            /*connect to DB MySQL veja SQL ao final da pagina
            Class.forName("com.mysql.jdbc.Driver");
            currentCon = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/projeto", "root", "");
            //*/
            
            /*connect to DB Derby veja SQL ao final da pagina
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String url="jdbc:derby://localhost:1527/projeto;create=true;user=APP;password=APP";
            currentCon = DriverManager.getConnection(url);
            //*/
            currentCon = Database.getConnection();
                     
            
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean more = rs.next();
            // if user does not exist set the isValid variable to false
            if (!more) {
                System.out.println("Sorry, you are not a registered user! Please sign up first");
                bean.setValid(false);
            } else if (more) {//if user exists set the isValid variable to true
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                cpf = rs.getString("cpf");
                senha = rs.getString("senha");
                pin = rs.getString("pin");
                int admin = rs.getInt("admin");
                int id = rs.getInt("id");
                //Date acesso = rs.getTimestamp("acesso");
                // TODO demais atributos

                System.out.println("Welcome " + nome);
                bean.setNome(nome);
                bean.setEmail(email);
                bean.setCpf(cpf);
                bean.setSenha(senha);
                bean.setAdmin(admin);
                bean.setId(id);
                //bean.setAcesso(acesso);
                bean.setPin(pin);
                bean.setValid(true);
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

        return bean;

    }
    // fim do codigo de login
    
    private Connection connection;
 
    public UsersDao() {
        connection = Database.getConnection();
    }
 
    public void checkUser(UserBean user) {
        try {
            PreparedStatement ps = connection.prepareStatement("select id from users where id = ?");
            int id = user.getId();
            System.out.println("vai atualizar usuario com ID=" + id );
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {// found   
                System.out.println("update usuario com ID=" + id );
                updateUser(user);
            } else {
                System.out.println("inserir usuario com ID=" + id );
                addUser(user);
            }
        } catch (Exception ex) {
            System.out.println("Error in check() -->" + ex.getMessage());
        } 
    }
    
    public void addUser(UserBean user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into users(cpf, senha, pin, nome, email, admin) values (?, ?, ?, ?, ?, ? )");
            // Parameters start with 1
            preparedStatement.setString(1, user.getCpf());
            preparedStatement.setString(2, user.getSenha());
            preparedStatement.setString(3, user.getPin());
            preparedStatement.setString(4, user.getNome());
            preparedStatement.setString(5, user.getEmail());  
            preparedStatement.setInt(6, user.getAdmin());            
            // banco define o now() preparedStatement.setDate(6, new java.sql.Date(user.getAcesso().getTime()));
            preparedStatement.executeUpdate();
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    public void deleteUser(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from users where id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    public void updateUser(UserBean user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update users set cpf=?, senha=?, pin=?, email=?, nome=?, admin=?"
                    + " where id=?");
            //System.out.println(new java.sql.Date(user.getAcesso().getTime()));
            
            // Parameters start with 1
            preparedStatement.setString(1, user.getCpf());
            preparedStatement.setString(2, user.getSenha());
            preparedStatement.setString(3, user.getPin());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getNome());
            preparedStatement.setInt(6, user.getAdmin());
            //preparedStatement.setDate(7, new java.sql.Date(user.getAcesso().getTime()));
            preparedStatement.setInt(7, user.getId());
            preparedStatement.executeUpdate();
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    public List<UserBean> getAllUsers() {
        List<UserBean> users = new ArrayList<UserBean>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from users");
            while (rs.next()) {
                UserBean user = new UserBean();
                user.setId(rs.getInt("id"));
                user.setCpf(rs.getString("cpf"));
                user.setSenha(rs.getString("senha"));
                user.setPin(rs.getString("pin"));
                user.setNome(rs.getString("nome"));
                user.setEmail(rs.getString("email"));
                user.setAcesso(rs.getDate("acesso"));
                user.setAdmin(rs.getInt("admin"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        return users;
    }
 
    public UserBean getUserById(int id) {
        UserBean user = new UserBean();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where id=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
 
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setCpf(rs.getString("cpf"));
                user.setSenha(rs.getString("senha"));
                user.setPin(rs.getString("pin"));
                user.setNome(rs.getString("nome"));
                user.setEmail(rs.getString("email"));
                user.setAcesso(rs.getDate("acesso"));
                user.setAdmin(rs.getInt("admin"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        return user;
    }
}


/* Criacao da tabela users no BD Derby projeto
jdbc:derby://localhost:1527/projeto [APP em APP]
Abra comando e "execute arquivo" referente ao cut&paste do trecho abaixo

drop table users;

CREATE TABLE users

(    
   ID INT not null primary key
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),  
  cpf varchar(14) NOT NULL UNIQUE,
  senha varchar(20) NOT NULL DEFAULT '0000',
  pin varchar(80) NOT NULL DEFAULT '@123',
  nome varchar(80) NOT NULL,
  email varchar(80) NOT NULL UNIQUE,
  acesso timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  admin int DEFAULT 0
);


INSERT INTO users (users.CPF, users.senha, users.pin, users.nome, users.email, users.acesso, users.admin) VALUES
('987.654.321-00', '123', '@1', 'Lauro Kozovits', 'lauro@ic.uff.br', '2019-04-19 00:00:16', 1),
('409.097.837-80', '0000', '@2', 'Clark Kent', 'superman@gmail.com', '2019-04-19 00:00:16', 0);

-- extra 

INSERT INTO users (users.CPF, users.senha, users.pin, users.nome, users.email, users.acesso, users.admin) VALUES
('637.706.857-33', '123', '@1', 'Derby Silva', 'derby@gmail.com', '2019-04-19 00:00:16', 1);

*/






/*
Criacao da tabela users em MySQL
atencao para reposicao dos comentarios retirando os espacos: / * e * /

-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 26-Abr-2019 Ã s 01:30
-- VersÃ£o do servidor: 10.1.38-MariaDB
-- versÃ£o do PHP: 7.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


-- ATENCAO retirar os espacos / * e * / das quatro linhas abaixo
/ *!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT * /;
/ *!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS * /;
/ *!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION * /;
/ *!40101 SET NAMES utf8mb4 * /;

--
-- Database: `projeto`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `senha` varchar(20) NOT NULL DEFAULT '0000',
  `pin` varchar(80) NOT NULL DEFAULT '@123',
  `nome` varchar(80) NOT NULL,
  `email` varchar(80) DEFAULT NULL,
  `acesso` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `admin` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `users`
--

INSERT INTO `users` (`id`, `cpf`, `senha`, `pin`, `nome`, `email`, `acesso`, `admin`) VALUES
(1, '987.654.321-00', '123', '@1', 'Lauro Kozovits', 'lauro@ic.uff.br', '2019-04-19 00:00:16', 1),
(2, '409.097.837-80', '0000', '@2', 'Clark Kent', 'superman@gmail.com', '2019-04-19 00:00:16', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `cpf` (`cpf`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

-- ATENCAO retirar os espacos / * e * / das tres linhas abaixo
/ *!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT * /;
/ *!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS * /;
/ *!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION * /;


*/
