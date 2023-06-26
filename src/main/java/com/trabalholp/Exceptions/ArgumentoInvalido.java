package com.trabalholp.Exceptions;

public class ArgumentoInvalido extends Exception{
    public ArgumentoInvalido(int error_code){
        switch(error_code){
            case 0:
                System.out.println("Não foi informado nenhum parâmetro para a busca.");
                break;
            case 1:
                System.out.println("Ocorreu um erro inesperado.");
                break;
            case 2:
                System.out.println("Os parâmetros informados não correspondem as classes de parâmetros informadas.");
        }
    }
}
