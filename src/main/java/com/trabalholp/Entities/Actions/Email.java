package com.trabalholp.Entities.Actions;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
    public String sendMail(String destinatario, String assunto, String mensagem){
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
            massage.setFrom(new InternetAddress(remetente));
            massage.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            massage.setSubject(assunto);
            massage.setContent("<h1>TITULO MT FODA</h1>", "text/html");
            Transport.send(massage);
            return "Email enviado com sucesso";
        } catch (AddressException e) {
            return e.getMessage();
        } catch (MessagingException e) {
            return e.getMessage();
        }
    }


}
