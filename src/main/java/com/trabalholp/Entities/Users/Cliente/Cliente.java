package com.trabalholp.Entities.Users.Cliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Scanner;

import com.trabalholp.Exceptions.SenhaErrada;
import com.trabalholp.Exceptions.UsuarioInexistente;

public class Cliente {
    String nome, cpf, rg, email, telefone;

    public String logar(String cpf, String senha) throws UsuarioInexistente, SenhaErrada{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/trabalholp", "sys_client", "123456");
            ResultSet rsClient = con.createStatement()
                                    .executeQuery("SELECT * FROM user_client WHERE cpf = '"+ cpf +"'");

            if(rsClient.next()){
                if(rsClient.getString("password").equals(senha)){
                    this.nome = rsClient.getString("name");
                    this.email = rsClient.getString("email");
                    this.rg = rsClient.getString("rg");
                    this.telefone = rsClient.getString("cell_number");
                    this.cpf = cpf;

                } else throw new SenhaErrada();
            } else throw new UsuarioInexistente();

            return "Logado com sucesso";
        } catch (ClassNotFoundException e) {
            return e.getMessage();
        } catch (SQLException e){
            return e.getMessage();
        }
    }

    public void addCartaoCredito(String numero, String cvv, String validade){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/trabalholp", "sys_client", "123456");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO user_credit_card VALUES ('"+ numero +"', '"+ cvv +"', '"+ validade +"', '"+ cpf +"')");

            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public void addEndereco(String rua, String bairro, String numero, String cep, String referencia){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/trabalholp", "sys_client", "123456");

            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO client_adress VALUES ('"+ rua +"', '"+ bairro +"', '"+ numero +"', '"+ cep +"', '"+ referencia +"', '"+ cpf +"', NULL)");

            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public void removeCartao(){
        try {
            ArrayList<String> numeros = new ArrayList<>();
            Scanner sc = new Scanner(System.in);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/trabalholp", "sys_client", "123456");
            ResultSet rsClient = con.createStatement()
                                    .executeQuery("SELECT * FROM user_credit_card WHERE cpf_owner = '" + cpf + "'");

            while(rsClient.next()){
                numeros.add(rsClient.getString("card_number"));
            }
            rsClient.close();

            System.out.println("Qual desses cartões você deseja excluir? :");
            for(String numero : numeros){
                System.out.println((numeros.indexOf(numero) + 1) + " - " + numero);
            }
            int i = sc.nextInt() - 1;
            
            con.createStatement()
                .executeUpdate("DELETE FROM user_credit_card WHERE card_number = '" + numeros.get(i) +"'");

            sc.close();
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void removeEndereco(){
        try {
            ArrayList<String> ids = new ArrayList<>();
            Scanner sc = new Scanner(System.in);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/trabalholp", "sys_client", "123456");
            ResultSet rsClient = con.createStatement()
                                    .executeQuery("SELECT * FROM client_adress WHERE cpf_owner = '" + cpf + "'");

            while(rsClient.next()){
                ids.add(rsClient.getString("id"));
            }
            rsClient.close();

            System.out.println("Qual desses endereços você deseja excluir? :");
            for(String id : ids){
                rsClient = con.createStatement()
                              .executeQuery("SELECT * FROM client_adress WHERE id = '"+ id + "'");
                rsClient.next();
                System.out.println("------------------------------------------------");
                System.out.println(ids.indexOf(id) + 1 + ":");
                System.out.println("Rua: " + rsClient.getString("street"));
                System.out.println("Bairro: " + rsClient.getString("district"));
                System.out.print("Numero: " + rsClient.getString("number"));
                System.out.println("  -  CEP: " + rsClient.getString("cep"));

                rsClient.close();
            }
            String i = ids.get(sc.nextInt() - 1);

            con.createStatement()
                .executeUpdate("DELETE FROM client_adress WHERE id = " + i);

            sc.close();
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
