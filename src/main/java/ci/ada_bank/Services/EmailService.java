package ci.ada_bank.Services;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailService {

    public static void sendEmail(String toEmail, String subject, String body) {

        String host = System.getenv("SMTP_HOST");
        String port = System.getenv("SMTP_PORT");
        String username = System.getenv("MAIL_USER");
        String password = System.getenv("MAIL_PASS");


        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);


        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Création du message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(body);

            // Envoi du message
            Transport.send(message);
            System.out.println("Email envoyé avec succès à " + toEmail);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
