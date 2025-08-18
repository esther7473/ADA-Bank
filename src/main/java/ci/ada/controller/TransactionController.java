package ci.ada.controller;

import ci.ada.Repository.AccountRepository;
import ci.ada.Repository.BankRepository;
import ci.ada.Repository.ClientRepository;
import ci.ada.Repository.TransactionRepository;
import ci.ada.entity.Bank;
import ci.ada.entity.Transaction;
import ci.ada.enums.TypeTransaction;
import ci.ada.services.TransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/dashboard/transaction")
public class TransactionController {

    private TransactionRepository transactionRepository;
    private TransactionService transactionService;
    private AccountRepository accountRepository;
    private ClientRepository clientRepository;

    @GetMapping
    public String showAllTransactions(Model model){
        List<Transaction> transactions = transactionRepository.findAll();
        if (transactions.isEmpty()) {
            log.info("No transactions found");
        }
        model.addAttribute("transactions", transactions);
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("clients", clientRepository.findAll());
        model.addAttribute("accounts", accountRepository.findAll());
        return "transaction";
    }


    @PostMapping("/addTransaction")
    public String createTransaction(@RequestParam Long compteSourceId,
                                         @RequestParam(required = false) Long compteDestinataireId,
                                         @RequestParam BigDecimal montant,
                                         @RequestParam TypeTransaction type,
                                         @RequestParam(required = false) String description) {
        try {
            Transaction transaction = transactionService.createTransaction(
                    compteSourceId,
                    compteDestinataireId,
                    montant,
                    type,
                    description
            );
            log.info("Transaction {} effectuée avec succès", type);
            return "redirect:/dashboard/transaction";
        } catch (Exception e) {
            log.error("Erreur lors de la création de la transaction: {}", e.getMessage(), e);
            throw new RuntimeException("Erreur lors de la transaction", e);
        }
    }

    // Récupérer une transaction par ID
    @GetMapping("getTransaction/{id}")
    public Transaction getTransactionById(@PathVariable Long id) {
        try {
            return transactionService.findTransactionById(id);
        } catch (Exception e) {
            log.error("Erreur lors de la récupération de la transaction {}", id, e);
            throw new RuntimeException("Transaction introuvable", e);
        }
    }

    @DeleteMapping("deleteTransaction/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        try {
            transactionService.deleteTransactionById(id);
            log.info("Transaction {} supprimée avec succès", id);
        } catch (Exception e) {
            log.error("Erreur lors de la suppression de la transaction {}", id, e);
            throw new RuntimeException("Erreur lors de la suppression", e);
        }
    }
}
