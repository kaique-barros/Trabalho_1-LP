package com.trabalholp.Entities.Actions;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.trabalholp.Exceptions.TipoInvalido;

public class Email {
    public String sendMail(String destinatario, String assunto, String tipo){
        String remetente = "jurandircasaconforto@gmail.com";
        
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", 587);

        Session ses = Session.getDefaultInstance(prop, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("jurandircasaconforto@gmail.com", "uhkmmirhsahktalm");
            }
        });
        try {
            MimeMessage massage = new MimeMessage(ses);
            String modelDir = "C:/Users/kaiqb/OneDrive/Documents/Cefet/LP/TrabalhoLP/"+ emailType(tipo) +"_mailModel.html";
            massage.setFrom(new InternetAddress(remetente));
            massage.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            massage.setSubject(assunto);
            massage.setContent(lerModelo(modelDir), "text/html");
            Transport.send(massage);
            return "Email enviado com sucesso";
        } catch (AddressException e) {
            return e.getMessage();
        } catch (MessagingException e) {
            return e.getMessage();
        } catch (TipoInvalido e){
            return "erro";
        }
    }

    private String lerModelo(String modelDir){
        try {
            FileInputStream arquivo = new FileInputStream(modelDir);
            InputStreamReader isr = new InputStreamReader(arquivo);
            BufferedReader buffRead = new BufferedReader(isr);

            String lin = buffRead.readLine();
            String modelo = lin;
            
            while (lin != null) {
                lin = buffRead.readLine();
                modelo += lin;
            }
            
            buffRead.close();
            isr.close();
            arquivo.close();

            return modelo;
        } catch (FileNotFoundException e) {
            return e.getMessage();
        } catch (IOException e){
            return e.getMessage();
        }
    }

    private String emailType(String tipo) throws TipoInvalido{
        switch (tipo) {
            case "compra":
                return "purchase";
            case "repor estoque":
                return "replanish";
            default:
                throw new TipoInvalido();
        }
    }

}
