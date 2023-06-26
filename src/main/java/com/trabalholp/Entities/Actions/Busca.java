package com.trabalholp.Entities.Actions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import com.trabalholp.Exceptions.ArgumentoInvalido;
import com.trabalholp.Exceptions.SenhaErrada;
import com.trabalholp.Exceptions.UsuarioInexistente;

public class Busca {
    private String tabela;
    public Busca(String cpf, String senha, String tabela) throws SenhaErrada, UsuarioInexistente{
        this.tabela = tabela;
    }

    public ResultSet busque(ArrayList<String> atributo, ArrayList<String> argumento) throws SQLException, ClassNotFoundException, ArgumentoInvalido{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/TrabalhoLP", "sys_seller", "123456");
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM " + tabela + " WHERE ";
            
            if(atributo.size() != argumento.size()) throw new ArgumentoInvalido(2);
            
            switch(atributo.size()){
                case 0:
                    throw new ArgumentoInvalido(0);
                case 1:
                    String param = argumento.get(0), classe = atributo.get(0);
                    Boolean eString = classe.equals("price") || classe.equals("stock") || classe.equals("provider_id") || classe.equals("id");
                    sql += classe + (!eString ? (" LIKE '%" + param + "%'") : (" = " + param));
                    break;
                default:
                    System.out.println(atributo.size());
                    for(String param2 : atributo){
                        String param1 = argumento.get(atributo.indexOf(param2));
                        Boolean eString1 = param2.equals("price") || param2.equals("stock") || param2.equals("provider_id") || param2.equals("id");
                        if(!(atributo.indexOf(param2) == (atributo.size() - 1))){
                            sql += param2 + (!eString1 ? (" LIKE '%"+ param1 +"%' AND ") : (" = "+ param1 +" AND "));
                        }
                        else sql += param2 + (!eString1 ? (" LIKE '%"+ param1 +"%'") : (" = "+ param1));
                    }
                    break;
            }

            return stmt.executeQuery(sql);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }return null;
    }

}
