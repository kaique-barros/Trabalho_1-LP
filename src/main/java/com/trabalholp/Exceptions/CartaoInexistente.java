package com.trabalholp.Exceptions;

public class CartaoInexistente extends Exception{
    public CartaoInexistente(){
        System.out.println("O numero informado não corresponde a nenhum numero na base de dados");
    }
}
