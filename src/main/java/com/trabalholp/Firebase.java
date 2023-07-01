package com.trabalholp;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class Firebase {
    public static Firestore db;

    public static void conectar(){
        try {
            FileInputStream json = new FileInputStream("trabalho-lp-firebase-adminsdk-muj02-ac5ed21d25.json");

            FirebaseOptions options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(json))
            .build();

            FirebaseApp.initializeApp(options);

            db = FirestoreClient.getFirestore();
            System.out.println("deu certo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
