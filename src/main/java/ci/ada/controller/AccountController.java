package ci.ada.controller;

import ci.ada.entity.Account;
import ci.ada.entity.Bank;
import ci.ada.services.AccountService;
import ci.ada.services.ClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/dashboard/account")
public class AccountController {
    private final AccountService accountService;
    private final ClientService clientService;

    @GetMapping
    public String showAllAccount(Model model){

        List<Account> accounts= accountService.findAllAccounts();
        if(accounts.isEmpty()){
            log.info("No accounts found");
        }
        model.addAttribute("accounts", accounts);
        model.addAttribute("account", new Account());
        model.addAttribute("clients", clientService.findAllClients());

        return "account";
    }

    @PostMapping("/addAccount")
    public String createAccount(@ModelAttribute Account account){
        account.setCreatedAt(LocalDateTime.now());
        accountService.createAccount(account);

        return "redirect:/dashboard/account";

    }

    @GetMapping("/getAccount/{id}")
    public String updateAccount(@PathVariable Long id, Account account){

        accountService.updateAccountById(id,account);
        return "account";

    }
    @PostMapping("/updateAccount/{id}")
    public String getAccountById(@PathVariable Long id, @ModelAttribute Account account){
        account.setId(id);
        accountService.updateAccountById(id,account);
        return "redirect:/dashboard/account";

    }


    @PostMapping("/deleteAccount/{id}")
    public String deleteAccount(@PathVariable Long id){
        accountService.deleteAccountById(id);
        return "redirect:/dashboard/account";
    }

}
