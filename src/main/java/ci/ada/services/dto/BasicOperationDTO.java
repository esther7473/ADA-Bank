package ci.ada.services.dto;

import ci.ada.models.enums.OperationType;
import lombok.*;


@NoArgsConstructor
//@Builder
@AllArgsConstructor
public class BasicOperationDTO extends TransactionDTO {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    private OperationType operationType;

    private AccountDTO account;
}
