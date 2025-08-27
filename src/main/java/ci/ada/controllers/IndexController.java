package ci.ada.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String index(Model model) {
        return "index";
    }
    @GetMapping("login")
    public String login(Model model) {
        return "login";
    }


    @GetMapping("dashboard")
    public String dashboard(Model model) {
        return "dashboard";
    }

    @GetMapping("dashboard/client")
    public String client(Model model) {
        return "dashboard/client";
    }
}
