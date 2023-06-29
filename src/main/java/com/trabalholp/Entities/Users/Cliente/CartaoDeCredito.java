package com.trabalholp.Entities.Users.Cliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.trabalholp.Exceptions.CartaoInexistente;
import com.trabalholp.Exceptions.SenhaErrada;

public class CartaoDeCredito{
    String numeroCartao, cvv, vigor, cpf_owner;
    public CartaoDeCredito(String numeroCartao){
        this.numeroCartao = numeroCartao;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/TrabalhoLP", "sys_seller", "123456");
            Statement stmt = con.createStatement();
            ResultSet rsClient = stmt.executeQuery("SELECT * FROM user_credit_card WHERE card_number = '" + numeroCartao + "'");
            if(!rsClient.next()) throw new CartaoInexistente();

            this.cvv = rsClient.getString("cvv");
            this.vigor = rsClient.getString("vigor");
            this.cpf_owner = rsClient.getString("cpf_owner");

        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        } catch (CartaoInexistente e){

        }
   }
   
    public String getNumero(){
        StringBuilder strB = new StringBuilder(numeroCartao);

        for(int i = 4; i < 12; i++){
            strB.deleteCharAt(i);
            strB.insert(i, "*");
        }

        return strB.toString();
    }


    public String getNumero(String senha){
         try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/TrabalhoLP", "sys_seller", "123456");
            Statement stmt = con.createStatement();
            ResultSet rsClient = stmt.executeQuery("SELECT * FROM user_client WHERE cpf = '" + cpf_owner + "'");
            if(!rsClient.next()) throw new CartaoInexistente();

            if(!senha.equals(rsClient.getString("password"))) throw new SenhaErrada();

            return numeroCartao;
        } catch (ClassNotFoundException | SQLException e){
            return e.getMessage();
        } catch (CartaoInexistente | SenhaErrada e){
            return "return false";
        }
    }

}
