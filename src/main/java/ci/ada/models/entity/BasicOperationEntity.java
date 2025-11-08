package ci.ada.models.entity;

import ci.ada.models.Transaction;
import ci.ada.models.enums.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


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
    private AccountEntity account;

}


