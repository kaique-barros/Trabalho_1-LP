package com.trabalholp;

import com.trabalholp.Entities.Users.Cliente.Cliente;

public class App {
    public static void main( String[] args ){
        Cliente cc = new Cliente("test_cpf", "123123");
        cc.addCartaoCredito("123456789012345", "123", "22/22");
        cc.removeCartao();



    }
}