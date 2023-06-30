package com.trabalholp.Entities.Actions;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Scanner;

import com.trabalholp.Entities.Users.Cliente.CartaoDeCredito;
import com.trabalholp.Exceptions.ClienteInexistente;

public class Compra {
    public Compra(String client_cpf, ArrayList<String> producs_id){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/TrabalhoLP", "sys_seller", "123456");
            Statement stmt = con.createStatement();
            ResultSet cliente = stmt.executeQuery("SELECT name FROM user_client WHERE cpf = '" + client_cpf + "'");
            if(!cliente.next()) throw new ClienteInexistente();
            String nome = cliente.getString("name");
            
            System.out.println(localizaCartao(client_cpf));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        } catch (ClienteInexistente e){
        }   
    }
    private CartaoDeCredito localizaCartao(String client_cpf){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/TrabalhoLP", "sys_seller", "123456");
            Statement stmt = con.createStatement();
            ResultSet cartoes = stmt.executeQuery("SELECT card_number FROM user_credit_card WHERE cpf_owner = '" + client_cpf + "'");
            int numCartoes = 1;

            System.out.println("Por favor selecione o cartão que será utilizado na compra:");
            while (cartoes.next()) {
                CartaoDeCredito cc = new CartaoDeCredito(cartoes.getString("card_number"));

                System.out.println("Cartão " + numCartoes + ":");
                System.out.println("Numero: " + cc.getNumero());
                numCartoes++;
            }

            Scanner sc = new Scanner(System.in);
            int cartaoSele = sc.nextInt();
            sc.close();

            cartoes = stmt.executeQuery("SELECT card_number FROM user_credit_card WHERE cpf_owner = '" + client_cpf + "' LIMIT 10 OFFSET " + (cartaoSele - 1));
            CartaoDeCredito cc = new CartaoDeCredito(cartoes.getString("card_number"));

            return cc;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}