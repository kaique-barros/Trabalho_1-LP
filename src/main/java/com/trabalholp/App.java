package com.trabalholp;

import com.trabalholp.Entities.Users.Vendedor;
import com.trabalholp.Exceptions.SenhaErrada;
import com.trabalholp.Exceptions.UsuarioInexistente;

public class App {
    public static void main( String[] args ){
        try {
            Vendedor ven = new Vendedor("test_cpf", "1234567890");
            System.out.println(ven.cadastrarProduto("test_product_1", "just a test product for programming", "test_path_1", 10, 10.0));
            System.out.println(ven.cadastrarProduto("test_product_2", "just a test product for programming", "test_path_2", 20, 20.0));
            System.out.println(ven.cadastrarProduto("test_product_3", "just a test product for programming", "test_path_3", 30, 30.0));
            System.out.println(ven.cadastrarProduto("test_product_4", "just a test product for programming", "test_path_4", 40, 40.0));
            System.out.println(ven.cadastrarProduto("test_product_5", "just a test product for programming", "test_path_5", 50, 50.0));
            System.out.println(ven.cadastrarProduto("test_product_6", "just a test product for programming", "test_path_6", 60, 60.0));
            System.out.println(ven.cadastrarProduto("test_product_7", "just a test product for programming", "test_path_7", 70, 70.0));
            System.out.println(ven.cadastrarProduto("test_product_8", "just a test product for programming", "test_path_8", 80, 80.0));
            System.out.println(ven.cadastrarProduto("test_product_9", "just a test product for programming", "test_path_9", 90, 90.0));
        } catch (SenhaErrada e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UsuarioInexistente e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}