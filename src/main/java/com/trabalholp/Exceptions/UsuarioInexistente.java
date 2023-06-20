package com.trabalholp.Exceptions;

public class UsuarioInexistente extends Exception{
    public UsuarioInexistente(){
        super("O usuário informado não corresponde a nenhum presente na base de dados");
    }
}
