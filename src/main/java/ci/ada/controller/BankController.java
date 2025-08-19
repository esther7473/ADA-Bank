package ci.ada.controller;

import ci.ada.entity.Bank;
import ci.ada.services.BankService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/dashboard/bank")
public class BankController {

    private BankService bankService;

    @GetMapping
    public String showAllBanks(Model model){
        List<Bank> banks = bankService.findAllBanks();

        if(banks.isEmpty()){
            log.info("No banks found");
        }
        model.addAttribute("banks", banks);
        model.addAttribute("bank", new Bank());

        return "bank";
    }

    @GetMapping("/searchBanks")
    public String searchBanks(@RequestParam(name = "search", required = false) String keyword, Model model) {
        List<Bank> filteredBanks = bankService.searchBanks(keyword);
        model.addAttribute("filteredBanks", filteredBanks);
        model.addAttribute("keyword", keyword);
        return "bank";
    }


    @PostMapping("/addBank")
    public String createBank(@ModelAttribute Bank bank,Model model){
        bank.setCreateAt(LocalDateTime.now());
        if (bank.getNumbreClient() == null) {
            bank.setNumbreClient(0);
        }
        log.debug(bank.getNumbreClient().toString());
        bankService.createBank(bank);

        return "redirect:/dashboard/bank";

    }

    @GetMapping("/getBank/{id}")
    public String updateBank(@PathVariable Long id, Bank bank){

        bankService.updateBankById(id,bank);
        return "bank";

    }

    @PostMapping("/updateBank/{id}")
    public String getBankById(@PathVariable Long id, @ModelAttribute Bank bank){
        bank.setId(id);
        bankService.updateBankById(id,bank);
        return "redirect:/dashboard/bank";

    }


    @PostMapping("/deleteBank/{id}")
    public String deleteBank(@PathVariable Long id){
        bankService.deleteBankById(id);
        return "redirect:/dashboard/bank";
    }




}
