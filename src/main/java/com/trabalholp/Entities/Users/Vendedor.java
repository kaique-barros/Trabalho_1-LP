package com.trabalholp.Entities.Users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.trabalholp.Exceptions.SenhaErrada;
import com.trabalholp.Exceptions.UsuarioInexistente;

public class Vendedor extends Funcionario{
    
    public Vendedor(String cpf, String senha) throws SenhaErrada, UsuarioInexistente{
        super(cpf, senha);
    }

    public String cadastrarCliente(String nome, String rg, String cpf, String email, String telefone) throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/TrabalhoLP", "sys_seller", "123456");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO user_client VALUES ('"+ nome +"', '"+ cpf +"', '"+ rg +"', '"+ email +"', '"+ telefone +"', NULL)");
            
            con.close();
            return nome + " foi cadastrado(a) com sucesso.";
        } catch (ClassNotFoundException e) {
            return e.getMessage();
        } catch(SQLException e){
            return e.getMessage();
        }
    }

    public String cadastrarProduto(String nome, String description, String img_path, int estoque, double preco, String providerID){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/TrabalhoLP", "sys_seller", "123456");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO product_stock VALUES ('"+nome+"', '"+description+"', "+preco+", "+estoque+", '"+img_path+"', "+ providerID +", NULL)");

            con.close();
            return "Produto Adicionado";
        } catch (ClassNotFoundException e) {
            return e.getMessage();
        } catch (SQLException e){
            return e.getMessage();
        }
    }

    public String atualizarEstoque(String nome, int estoque){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/TrabalhoLP", "sys_seller", "123456");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE product_stock SET stock = '"+ estoque +"' WHERE name = '"+ nome +"'");

            con.close();
            return "O estoque do produto foi alterado.";
        } catch (ClassNotFoundException e) {
            return e.getMessage();
        } catch (SQLException e){
            return e.getMessage();
        }
    }

    public String atualizarEstoque(String nome, double preco){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/TrabalhoLP", "sys_seller", "123456");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE product_stock SET price = '"+ preco +"' WHERE name = '"+ nome +"'");

            con.close();
            return "O preço do produto foi alterado.";
        } catch (ClassNotFoundException e) {
            return e.getMessage();
        } catch (SQLException e){
            return e.getMessage();
        }
    }

}