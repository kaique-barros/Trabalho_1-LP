package com.trabalholp.Entities.Actions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.trabalholp.Exceptions.SenhaErrada;
import com.trabalholp.Exceptions.UsuarioInexistente;

public class Estoque {
    public Estoque(String cpf, String senha) throws SenhaErrada, UsuarioInexistente{
    }

    public int verificaEstoque(String nome) throws SQLException, ClassNotFoundException{
        try {
            Class.forName("mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/TrabalhoLP", "sys_seller", "123456");
            Statement stmt = con.createStatement();
            ResultSet rsClient = stmt.executeQuery("SELECT * FROM product_stock WHERE name = '"+ nome +"'");

            int i = 0;
            System.out.println("Os seguintes produtos correspondem a busca:");
            while(rsClient.next()){
                System.out.println("----------------------------------------------------");
                System.out.println("Nome: " + rsClient.getString("name"));
                System.out.println("Descrição: " + rsClient.getString("description"));
                System.out.println("Imagem: " + rsClient.getString("figure_path"));
                System.out.println("Quantidade em estoque: " + rsClient.getString("stock"));
                i++;
            }

            return i;


        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public void mantemEstoque(){
        
    }

}
