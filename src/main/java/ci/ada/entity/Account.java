package ci.ada.entity;

import ci.ada.enums.AccountStatus;
import ci.ada.enums.AccountType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.engine.internal.Cascade;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private BigDecimal balance;
    private AccountType typeAccount;
    private AccountStatus status;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;
    private LocalDateTime createdAt;

    public void depot(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
    }
    public void retrait(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public boolean hasSufficientBalance(BigDecimal montant) {
        return balance != null && montant != null && balance.compareTo(montant) >= 0;
    }
}
