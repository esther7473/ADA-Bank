package ci.ada.pattern;

import ci.ada.entity.Account;
import ci.ada.entity.Transaction;
import ci.ada.enums.AccountStatus;
import ci.ada.enums.TypeTransaction;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Component("depotStrategy")
public class DepoStrategy implements TransactionStrategy {


    @Override
    public Transaction executeTransaction(Account compteSource, Account compteDestinataire, BigDecimal montant, String description) {

        if (!validateTransaction(compteSource, compteDestinataire, montant)) {
            throw new IllegalArgumentException("Transaction de dépôt invalide");
        }

        compteSource.depot(montant);
        if(description == null){
            description = generateDefaultDescription(compteSource,compteDestinataire,montant);
        }
        return Transaction.builder()
                .dateTransaction(LocalDateTime.now())
                .montant(montant)
                .compteSource(compteSource)
                .compteDestinataire(null)
                .typeTransaction(TypeTransaction.DEPOT)
                .description(description)
                .build();
    }

    @Override
    public boolean validateTransaction(Account compteSource, Account compteDestinataire, BigDecimal montant) {
        if (compteSource == null) {
            return false;
        }

        if (montant == null || montant.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }

        if (compteDestinataire != null) {
            return false;
        }

        return compteSource.getStatus() != AccountStatus.FERMER && compteSource.getStatus() != AccountStatus.SUSPENDU;
    }

    @Override
    public String getDescription() {
        return " ";
    }

    @Override
    public String generateDefaultDescription(Account compteSource, Account compteDestinataire, BigDecimal montant) {
        return String.format("Dépôt de %sF CFA sur le compte %s",
                montant,
                compteSource.getNumber());
    }
}

