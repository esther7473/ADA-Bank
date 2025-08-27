package ci.ada.models.entity;

import ci.ada.models.Transaction;
import ci.ada.models.enums.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Super;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.SuperCall;

@Entity
@Table(name = "basicOperation")

@NoArgsConstructor
@AllArgsConstructor
public class BasicOperationEntity extends Transaction {

    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    @ManyToOne
    /*
    @JoinColumn(name = "account_id") //pas obligatoire, il sert juste a renommer la colonne de jointure
    */
    private AccountEntity account;

}


