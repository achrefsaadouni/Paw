/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author vinga
 */
public class Mail {
    
	public static void send(String recepteur,String titre,String contenu){

		final String username = "pawzcorporation@gmail.com"; // enter your mail id
		final String password = "pawz0000";// enter ur password

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
                    
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
                        @Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
                
		try {
                        System.out.println(recepteur);
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("pawzcorporation@gmail.com")); // same email id
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(recepteur));// whome u have to send mails that person id
			message.setSubject(titre);
			message.setText(contenu);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}