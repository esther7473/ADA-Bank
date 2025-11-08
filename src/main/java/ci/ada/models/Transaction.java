package ci.ada.models;

import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Transaction extends Auditing {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Column(nullable = false)
    private String reference;

    @Column(nullable = false)
    private BigDecimal amount;

}
