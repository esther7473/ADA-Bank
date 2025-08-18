package ci.ada.controller;

import ci.ada.entity.Client;
import ci.ada.services.ClientService;
import ci.ada.services.BankService;
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
@RequestMapping("/dashboard/client")
public class ClientController {

    private final ClientService clientService;
    private final BankService bankService; // Pour récupérer la liste des banques dans les formulaires

    @GetMapping
    public String showAllClients(Model model) {
        List<Client> clients = clientService.findAllClients();
        if (clients.isEmpty()) {
            log.info("No clients found");
        }
        model.addAttribute("clients", clients);
        model.addAttribute("client", new Client());
        model.addAttribute("banks", bankService.findAllBanks()); // Pour sélectionner la banque
        return "client"; // Nom de la vue Thymeleaf
    }

    @PostMapping("/addClient")
    public String createClient(@ModelAttribute Client client, Model model) {
        client.setCreatedAt(LocalDateTime.now());
        clientService.createClient(client);
        return "redirect:/dashboard/client";
    }

    @GetMapping("/getClient/{id}")
    public String getClientById(@PathVariable Long id, Model model) {
        Client client = clientService.findClientById(id);
        if (client == null) {
            log.warn("Client with ID {} not found", id);
            return "redirect:/dashboard/client";
        }
        model.addAttribute("client", client);
        model.addAttribute("banks", bankService.findAllBanks());
        return "client"; // Vue pour afficher/modifier le client
    }

    @PostMapping("/updateClient/{id}")
    public String updateClient(@PathVariable Long id, @ModelAttribute Client client) {
        client.setId(id);
        clientService.updateClientById(id, client);
        return "redirect:/dashboard/client";
    }

    @PostMapping("/deleteClient/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientService.deleteClientById(id);
        return "redirect:/dashboard/client";
    }
}
