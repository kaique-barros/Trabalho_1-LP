package com.trabalholp.Entities.Cliente;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.trabalholp.Execeptions.UsuarioNaoEncontrado;

public final class Cliente {
   
    public static boolean Cadastrar(
        @javax.annotation.Nonnull String nome, 
        @javax.annotation.Nonnull String email, 
        @javax.annotation.Nonnull String cpf, 
        @javax.annotation.Nonnull String rg, 
        @javax.annotation.Nonnull Calendar nascimento, 
        @javax.annotation.Nonnull String senha
    ){
        try {
            Firestore db = FirestoreClient.getFirestore();
            DocumentReference user = db.collection("users").document(cpf);
            Map<String, Object> data = new HashMap<>();
            data.put("Nome", nome);
            data.put("Email", email);
            data.put("RG", rg);
            data.put("Data de Nascimento", formataData(nascimento));
            data.put("Senha", senha);

            ApiFuture<WriteResult> rs = user.set(data);

            rs.get().getUpdateTime();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }
    
    public static boolean veficaSenha(
        @javax.annotation.Nonnull String cpf,
        @javax.annotation.Nonnull String senha){
            Firestore db = FirestoreClient.getFirestore();
            
            ApiFuture<DocumentSnapshot> userFuture = db.collection("users").document(cpf).get();
            try {
                DocumentSnapshot user = userFuture.get();
                String senhaNoBanco = user.getString("Senha");
                if(senhaNoBanco == null) throw new UsuarioNaoEncontrado();
                return senhaNoBanco.equals(senha);
            } catch (InterruptedException | ExecutionException | NullPointerException | UsuarioNaoEncontrado e) {
                e.printStackTrace();
            }      
            return false;
        }
        
    public static String getCampo(
            @javax.annotation.Nonnull String senha,
            @javax.annotation.Nonnull String campo,
            @javax.annotation.Nonnull String cpf
            ){
                if(Cliente.veficaSenha(cpf, senha)){
                    Firestore db = FirestoreClient.getFirestore();
                    ApiFuture<DocumentSnapshot> userFuture = db.collection("users").document(cpf).get();
                    try {
                        DocumentSnapshot user = userFuture.get();
                    return user.getString(campo);
                    } catch (InterruptedException | ExecutionException | NullPointerException e) {
                        e.printStackTrace();
                    }     
                return null;
            }
            return null;
    }

    public static boolean atualizaCampo(
        @javax.annotation.Nonnull String senha,
        @javax.annotation.Nonnull String campo,
        @javax.annotation.Nonnull String cpf,
        @javax.annotation.Nonnull String novoValor
        ){
            if(Cliente.veficaSenha(cpf, senha)){
                Firestore db = FirestoreClient.getFirestore();
                DocumentReference docRef = db.collection("users").document(cpf);
                docRef.update(campo, novoValor);

                return true;
        }
        return false;
    }
    
    private static String formataData(Calendar data){
        String dia = Integer.toString(data.get(Calendar.DATE));
        String mes = Integer.toString(data.get(Calendar.MONTH));
        String ano = Integer.toString(data.get(Calendar.YEAR));
        String dataFormatada = dia + "/" + mes + "/" + ano;
    
        return dataFormatada;
    }

}
