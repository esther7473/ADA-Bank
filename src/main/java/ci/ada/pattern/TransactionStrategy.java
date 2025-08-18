package ci.ada.pattern;

import ci.ada.entity.Account;
import ci.ada.entity.Transaction;

import java.math.BigDecimal;

public interface TransactionStrategy {
    Transaction executeTransaction(Account compteSource, Account compteDestinataire,
                                   BigDecimal montant, String description);
    boolean validateTransaction(Account compteSource, Account compteDestinataire, BigDecimal montant);
    String getDescription();
    String generateDefaultDescription(Account compteSource, Account compteDestinataire, BigDecimal montant);
}
