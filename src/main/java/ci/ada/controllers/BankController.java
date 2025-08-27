package ci.ada.controllers;

import ci.ada.services.BankFacade;
import ci.ada.services.UserFacade;
import ci.ada.services.dto.BankDTO;
import ci.ada.services.dto.BankToRegisterDto;
import ci.ada.services.dto.UserAccountDTO;
import ci.ada.services.dto.UserToregisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/bank")
//@SessionAttributes("admin")
public class BankController {

    private final UserFacade userFacade;
    private final BankFacade bankFacade;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("admin", new UserAccountDTO());
        return "dashboard/bank/bankRegister";
    }


    @PostMapping("/createBank")
    public String createBank(@ModelAttribute UserToregisterDTO admin,BankToRegisterDto bankToRegisterDto, Model model) {
        BankDTO bankDTO = userFacade.registerNewBank(bankToRegisterDto, admin);
        model.addAttribute("bank", bankFacade.getBankData(bankDTO.getSlug()));
        return "redirect:/dashboard/bank/bankHome";
    }

    @GetMapping("/register")
    public String bank(Model model) {
        return "dashboard/bank/bankRegister";
    }
}
