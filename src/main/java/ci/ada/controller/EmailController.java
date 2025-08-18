package ci.ada.controller;

import ci.ada.services.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/email")
@AllArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @GetMapping("/welcome")
    public String sendWelcomeMail(
            @RequestParam String to,
            @RequestParam String name,
            @RequestParam String bank
    ) {
        emailService.sendWelcomeEmail(to, name, bank);
        return " Mail de bienvenue envoyé à " + to;
    }


    @GetMapping("/account")
    public String sendAccountMail(
            @RequestParam String to,
            @RequestParam String name,
            @RequestParam String account,
            @RequestParam String bank
    ) {
        emailService.sendAccountCreationEmail(to, name, account, bank);
        return " Mail de création de compte envoyé à " + to;
    }


    @GetMapping("/transaction")
    public String sendTransactionMail(
            @RequestParam String to,
            @RequestParam String name,
            @RequestParam String type,
            @RequestParam BigDecimal amount,
            @RequestParam String account,
            @RequestParam String bank
    ) {
        emailService.sendTransactionReceipt(to, name, type, amount, account, bank);
        return "Mail de transaction envoyé à " + to;
    }
}
