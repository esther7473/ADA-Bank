package ci.ada.services.dto;

import lombok.*;

import java.math.BigDecimal;


@NoArgsConstructor
//@Builder
@AllArgsConstructor
public class TransactionDTO extends AuditingDTO{
    private String reference;

    private BigDecimal amount;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
