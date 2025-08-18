package ci.ada.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final String from = "sunservices94@gmail.com"; // ton mail expéditeur

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    private void send(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }


    public void sendWelcomeEmail(String to, String clientName, String bankName) {
        String subject = "Bienvenue chez " + bankName + " 🎉";
        String body = "Bonjour " + clientName + ",\n\n" +
                "Bienvenue chez " + bankName + " !\n" +
                "Nous sommes ravis de vous compter parmi nos clients.\n\n" +
                "Votre conseiller reste à votre disposition.\n\n" +
                "Cordialement,\nL’équipe " + bankName;
        send(to, subject, body);
    }


    public void sendAccountCreationEmail(String to, String clientName, String accountNumber, String bankName) {
        String subject = "Votre nouveau compte bancaire a été créé ✅";
        String body = "Bonjour " + clientName + ",\n\n" +
                "Votre compte bancaire chez " + bankName + " a bien été créé.\n" +
                "Numéro de compte : " + accountNumber + "\n\n" +
                "Vous pouvez dès à présent effectuer vos opérations.\n\n" +
                "Cordialement,\n" + bankName;
        send(to, subject, body);
    }


    public void sendTransactionReceipt(String to, String clientName, String operationType,
                                       BigDecimal amount, String accountNumber, String bankName) {
        String subject = "Reçu de votre opération (" + operationType + ")";
        String body = "Bonjour " + clientName + ",\n\n" +
                "Votre opération a été effectuée avec succès.\n\n" +
                "Type d’opération : " + operationType + "\n" +
                "Montant : " + amount + " FCFA\n" +
                "Compte débité/crédité : " + accountNumber + "\n\n" +
                "Merci d’avoir choisi " + bankName + ".\n\n" +
                "Cordialement,\n" + bankName;
        send(to, subject, body);
    }
}
