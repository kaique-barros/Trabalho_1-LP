package com.trabalholp.Exceptions;

public class SenhaErrada extends Exception{
    public SenhaErrada(){
        super("A senha informada não corresponde a senha do usuário.");
    }
}
