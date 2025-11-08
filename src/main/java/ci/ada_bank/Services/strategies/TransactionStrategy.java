package ci.ada_bank.Services.strategies;

import ci.ada_bank.Modeles.Transaction;

import java.math.BigDecimal;

public interface TransactionStrategy {
    void makeTransaction(Transaction transaction,String numeroCompte, BigDecimal montant);

}
