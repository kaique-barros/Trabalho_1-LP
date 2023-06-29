package com.trabalholp.Exceptions;

public class ClienteInexistente extends Exception{
    public ClienteInexistente(){
        System.out.println("O cpf informado não corresponde a nenhum cliente no sistema");
    }
}
