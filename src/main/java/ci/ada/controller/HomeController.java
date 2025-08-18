package ci.ada.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/dashboard")
    public String Dashboard() {
        return "dashboard";
    }

    @GetMapping("/bank")
    public String Bank() {
        return "bank";
    }
}
