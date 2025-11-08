package ci.ada_bank.Modeles;

import ci.ada_bank.Modeles.Enumeration.TransactionType;
import ci.ada_bank.Services.strategies.RetraitStrategy;
import ci.ada_bank.Services.strategies.TransactionStrategy;
import ci.ada_bank.Services.strategies.VirementStrategy;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;


public class TransactionTest {

    private static final Long id = 2L;
    private static final BigDecimal montant = BigDecimal.valueOf(2000L);
    private static final TransactionType typeTransaction = TransactionType.Depot; // "depot" ou "retrait"
    private static final Compte compte = new Compte();
    private static final String dateTransaction = ""; // Format de date, par exemple "YYYY-MM-DD"
    private static final String description = "e";
    private static  TransactionStrategy transactionStrategy ;

    @Test
    public void assertThatFieldsAreEquals(){
        Transaction transaction = new Transaction();
        transaction.setId(id);
        transaction.setDateTransaction(dateTransaction);
        transaction.setDescription(description);
        transaction.setMontant(montant);
        transaction.setTypeTransaction(typeTransaction);
        transaction.setCompte(compte);
        transaction.setTransactionStrategy(transactionStrategy);

        assertThat(transaction).isNotNull()
                .satisfies(transaction1 -> assertEquals(id,transaction1.getId()))
                .satisfies(transaction1 -> assertEquals(dateTransaction,transaction1.getDateTransaction()))
                .satisfies(transaction1 -> assertEquals(description,transaction1.getDescription()))
                .satisfies(transaction1 -> assertEquals(typeTransaction,transaction1.getTypeTransaction()))
                .satisfies(transaction1 -> assertEquals(compte,transaction1.getCompte()))
                .satisfies(transaction1 -> assertEquals(transactionStrategy,transaction1.getTransactionStrategy()))
                .satisfies(transaction1 -> assertEquals(montant,transaction1.getMontant()));


        transaction = new Transaction(id,montant,typeTransaction,compte,dateTransaction,description);
        transaction.setId(id);
        transaction.setDateTransaction(dateTransaction);
        transaction.setDescription(description);
        transaction.setMontant(montant);
        transaction.setTypeTransaction(typeTransaction);
        transaction.setCompte(compte);
        transaction.setTransactionStrategy(transactionStrategy);

        assertThat(transaction).isNotNull()
                .satisfies(transaction1 -> assertEquals(id,transaction1.getId()))
                .satisfies(transaction1 -> assertEquals(dateTransaction,transaction1.getDateTransaction()))
                .satisfies(transaction1 -> assertEquals(description,transaction1.getDescription()))
                .satisfies(transaction1 -> assertEquals(typeTransaction,transaction1.getTypeTransaction()))
                .satisfies(transaction1 -> assertEquals(compte,transaction1.getCompte()))
                .satisfies(transaction1 -> assertEquals(transactionStrategy,transaction1.getTransactionStrategy()))
                .satisfies(transaction1 -> assertEquals(montant,transaction1.getMontant()));


    }
}