package com.trabalholp.Entities.Users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.trabalholp.Exceptions.*;

public class Funcionario {
    private String nome, cpf;
    private double salario, comissao;
    
    public Funcionario(String cpf, String senha) throws SenhaErrada, UsuarioInexistente{
        logar(cpf, senha);
    }

    public void logar(String cpf, String senha) throws SenhaErrada, UsuarioInexistente{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/trabalholp", "sys_seller", "123456");
            ResultSet rsCliente = con.createStatement()
                                     .executeQuery("SELECT * FROM user_worker WHERE cpf = '" + cpf +"'");
            
            if(rsCliente.next()){
                if(rsCliente.getString("password").equals(senha)){
                    System.out.println("Logado");

                    this.nome = rsCliente.getString("name");
                    this.cpf = cpf;
                    this.salario = rsCliente.getDouble("salary");
                    this.comissao = rsCliente.getDouble("commission");
                } else throw new SenhaErrada();
            } else throw new UsuarioInexistente();

            con.close();
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getInfo(){
        System.out.println(nome);
        System.out.println(cpf);
        System.out.println(salario);
        System.out.println(comissao);
    }

}
