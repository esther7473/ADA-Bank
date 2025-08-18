package ci.ada.pattern;

import ci.ada.entity.Account;
import ci.ada.entity.Transaction;
import ci.ada.enums.AccountStatus;
import ci.ada.enums.TypeTransaction;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component("virementStrategy    ")
public class VirementStrategy implements TransactionStrategy{
    @Override
    public Transaction executeTransaction(Account compteSource, Account compteDestinataire, BigDecimal montant, String description) {
        if (!validateTransaction(compteSource, compteDestinataire, montant)) {
            throw new IllegalArgumentException("Transaction de virement invalide");
        }

        compteSource.retrait(montant);
        compteDestinataire.depot(montant);

        if(description == null){
            description = generateDefaultDescription(compteSource,compteDestinataire,montant);
        }
        return Transaction.builder()
                .dateTransaction(LocalDateTime.now())
                .montant(montant)
                .compteSource(compteSource)
                .compteDestinataire(compteDestinataire)
                .typeTransaction(TypeTransaction.VIREMENT)
                .description(description)
                .build();
    }

    @Override
    public boolean validateTransaction(Account compteSource, Account compteDestinataire, BigDecimal montant) {
        if (compteSource == null && compteDestinataire == null) {
            return false;
        }

        if (montant == null || montant.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }

        assert compteSource != null;
        if (compteSource.getId().equals(compteDestinataire.getId())) {
            return false;
        }

        if (!compteSource.hasSufficientBalance(montant)) {
            return false;
        }

        return compteSource.getStatus() != AccountStatus.FERMER
                && compteSource.getStatus() != AccountStatus.SUSPENDU
                && compteDestinataire.getStatus() != AccountStatus.FERMER
                && compteDestinataire.getStatus() != AccountStatus.SUSPENDU;
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public String generateDefaultDescription(Account compteSource, Account compteDestinataire, BigDecimal montant) {
        return String.format("Virement de %s F CFA du compte %s vers le compte %s",
                montant,
                compteSource.getNumber(),
                compteDestinataire.getNumber());
    }
}
