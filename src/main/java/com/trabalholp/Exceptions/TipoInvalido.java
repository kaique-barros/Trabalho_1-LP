package com.trabalholp.Exceptions;

public class TipoInvalido extends Exception{
    public TipoInvalido(){
        System.out.println("O tipo de email informado naço corresponde a nenhum cadastrado no sistema");
    }
}
