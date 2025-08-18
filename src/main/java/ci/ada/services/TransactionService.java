package ci.ada.services;

import ci.ada.Repository.AccountRepository;
import ci.ada.Repository.TransactionRepository;
import ci.ada.entity.Account;
import ci.ada.entity.Transaction;
import ci.ada.enums.TypeTransaction;
import ci.ada.pattern.TransactionStrategy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    private final Map<String, TransactionStrategy> strategies;
    private final EmailService emailService;

    public Transaction createTransaction(Long compteSourceId,
                                          Long compteDestId,
                                          BigDecimal montant,
                                          TypeTransaction type,
                                          String description) {
        Account compteSource = accountRepository.findById(compteSourceId)
                .orElseThrow(() -> new RuntimeException("Compte source introuvable"));

        Account compteDestinataire = (compteDestId != null)
                ? accountRepository.findById(compteDestId).orElse(null)
                : null;

        TransactionStrategy strategy = selectStrategy(type);

        Transaction transaction = strategy.executeTransaction(compteSource, compteDestinataire, montant, description);
        accountRepository.save(compteSource);
        //emailService.sendAccountCreationEmail();
        if (compteDestinataire != null) {
            accountRepository.save(compteDestinataire);
        }
        transactionRepository.save(transaction);

        log.info("Transaction {} réussie: {}", type, transaction);
        return transaction;
    }

    private TransactionStrategy selectStrategy(TypeTransaction type) {
        return switch (type) {
            case DEPOT -> strategies.get("depotStrategy");
            case RETRAIT -> strategies.get("retraitStrategy");
            case VIREMENT -> strategies.get("virementStrategy");
            default -> throw new IllegalArgumentException("Type de transaction non supporté: " + type);
        };
    }

    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction findTransactionById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    public void deleteTransactionById(Long id) {
        transactionRepository.deleteById(id);
    }

}
