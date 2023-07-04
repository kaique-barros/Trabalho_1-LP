package com.trabalholp.Execeptions;

import javax.swing.JOptionPane;

public class UsuarioNaoEncontrado extends Exception{
    public UsuarioNaoEncontrado(){
        JOptionPane.showMessageDialog(null, "Não encontramos nenhum usuário com suas credenciais no sistema");
    }
}
