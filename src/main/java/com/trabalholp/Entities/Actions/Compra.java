package com.trabalholp.Entities.Actions;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import com.trabalholp.Exceptions.ClienteInexistente;

public class Compra {
    public void comprar(String client_cpf, ArrayList<String> producs_id){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/TrabalhoLP", "sys_seller", "123456");
            Statement stmt = con.createStatement();
            ResultSet rsClient = stmt.executeQuery("SELECT * FROM user_credit_card WHERE cpf = '" + client_cpf + "'");
            if(!rsClient.next()) throw new ClienteInexistente();

            int i = 1;
            System.out.println("Selecione o cartão que será utilizado na compra:");
            do {
                System.out.println("Cartão " + i + ":");
                System.out.println("Numero: ");
            } while (rsClient.next());



        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        } catch (ClienteInexistente e){

        }
        
    }
}
