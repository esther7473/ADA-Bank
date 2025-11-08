package ci.ada_bank.Modeles;

import ci.ada_bank.Modeles.Enumeration.TransactionType;
import ci.ada_bank.Services.strategies.TransactionStrategy;
import ci.ada_bank.Services.strategies.VirementStrategy;

import java.math.BigDecimal;

public class Transaction {
    private Long id;
    private BigDecimal montant;
    private TransactionType typeTransaction; // "depot" ou "retrait"
    private Compte compte;
    private String dateTransaction; // Format de date, par exemple "YYYY-MM-DD"
    private String description;
    private TransactionStrategy transactionStrategy;

    public Transaction() {

    }

    public Transaction(Long id, BigDecimal montant, TransactionType typeTransaction, Compte compte, String dateTransaction, String description) {
        this.id = id;
        this.montant = montant;
        this.typeTransaction = typeTransaction;
        this.compte = compte;
        this.dateTransaction = dateTransaction;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public TransactionType getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(TransactionType typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public String getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(String dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TransactionStrategy getTransactionStrategy() {
        return transactionStrategy;
    }

    public void setTransactionStrategy(TransactionStrategy transactionStrategy) {
        this.transactionStrategy = transactionStrategy;
    }
    public void makeTransaction(TransactionStrategy transactionStrategy, BigDecimal montant, String numeroCompte){
        transactionStrategy.makeTransaction(this, numeroCompte,montant);
    }

   // public void makeVirement(VirementStrategy virementStrategy, String receiver, String sender, BigDecimal montant){
   //     virementStrategy.makeVirement(receiver,sender,montant);
   // }
}
