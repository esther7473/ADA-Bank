package ci.ada.entity;

import ci.ada.enums.TypeTransaction;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private LocalDateTime dateTransaction;

    @NonNull
    private BigDecimal montant;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "sender_account_id")
    private Account compteSource;

    @ManyToOne
    @JoinColumn(name = "receiver_account_id")
    private Account compteDestinataire;

    @Column(name = "type_transaction")
    private TypeTransaction typeTransaction;

    @NonNull
    private String description;
}
