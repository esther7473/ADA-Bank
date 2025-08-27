package ci.ada.controllers;

import ci.ada.services.UserFacade;
import ci.ada.services.dto.CustomerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard/Customer")
@RequiredArgsConstructor
public class CustomerController {

    private UserFacade userFacade;

    @GetMapping()
    public String customerDashboard(Model model) {
        //model.addAttribute("client",client);
        return "dashboard/client";
    }

    @PostMapping("/createCustomer")
    public String createCustomer(@ModelAttribute CustomerDTO customerDTO) {
        userFacade.registerNewCustomer(customerDTO);
        return "redirect:/dashboard/client";
    }


}
