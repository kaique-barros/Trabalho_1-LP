package com.trabalholp.External_connections.Firebase;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class Connection {
    public static Firestore db;

    public static void conectar(){
            try {
            FileInputStream json = new FileInputStream("Key_Firebase_Trabalho.json");

            FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(json))
                .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

}
