package ci.ada_bank.Modeles;

import ci.ada_bank.Modeles.Enumeration.TransactionType;

import java.math.BigDecimal;

public class TransactionDTO {

    private Long id;
    private BigDecimal montant;
    private TransactionType typeTransaction;
    private String numeroCompte;
    private String dateTransaction;
    private String description;

    public TransactionDTO(Long id, BigDecimal montant, TransactionType typeTransaction,
                          String numeroCompte, String dateTransaction, String description) {
        this.id = id;
        this.montant = montant;
        this.typeTransaction = typeTransaction;
        this.numeroCompte = numeroCompte;
        this.dateTransaction = dateTransaction;
        this.description = description;
    }



}
