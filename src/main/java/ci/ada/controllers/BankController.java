package ci.ada.controllers;

import ci.ada.services.UserFacade;
import ci.ada.services.dto.BankDTO;
import ci.ada.services.dto.UserAccountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/bank")
public class BankController {

    private final UserFacade userFacade;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("admin", new UserAccountDTO());
        return "dashboard/bank/bankRegister";
    }

    @GetMapping("/home")
    public String home(Model model) {
        return "dashboard/bank/bankHome";
    }

    @PostMapping("/createBank")
    public String createBank(@ModelAttribute BankDTO bankDTO) {
        userFacade.registerNewBank(bankDTO);
        return "redirect:/dashboard/bank/bankHome";
    }

    @GetMapping("/register")
    public String bank(Model model) {
        return "dashboard/bank/bankRegister";
    }
}
