package ci.ada.controller;

import ci.ada.services.AccountService;
import ci.ada.services.BankService;
import ci.ada.services.ClientService;
import ci.ada.services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@AllArgsConstructor
@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private AccountService accountService;
    private ClientService clientService;
    private TransactionService transactionService;
    private BankService bankService;

    @GetMapping
    public String Dashboard(Model model) {
        model.addAttribute("banks",bankService.findAllBanks());
        model.addAttribute("clients",clientService.findAllClients());
        model.addAttribute("transactions",transactionService.findAllTransactions());
        model.addAttribute("accounts",accountService.findAllAccounts());
        model.addAttribute("top15banks",bankService.findAllTop15Banks());
        model.addAttribute("volume",transactionService.totaltransactionVolume().toString());
        return "dashboard";
    }


}
