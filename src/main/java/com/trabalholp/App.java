package com.trabalholp;

import java.sql.SQLException;

import com.trabalholp.Entities.Actions.Email;
import com.trabalholp.Exceptions.ArgumentoInvalido;
import com.trabalholp.Exceptions.SenhaErrada;
import com.trabalholp.Exceptions.UsuarioInexistente;

public class App {
    public static void main( String[] args ) throws ClassNotFoundException, SQLException, SenhaErrada, UsuarioInexistente, ArgumentoInvalido{
        Email em = new Email();
        System.out.println(em.sendMail("jurandircasaconforto@gmail.com", "nice", "compra")); 

    }
}