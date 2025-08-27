package ci.ada.models.entity;

import ci.ada.models.enums.AccountType;
import ci.ada.models.enums.OperationType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class BasicOperationEntityTest {

    private final Long id =3L;
    private final OperationType  operationType=OperationType.DEPOSIT;
    private final AccountEntity accountEntity = AccountEntity.builder().accountType(AccountType.COURANT).balance(BigDecimal.ZERO).build();
    private final String reference="sssss";
    private final BigDecimal amount =BigDecimal.valueOf(32);

    private BasicOperationEntity basicOperationEntity;

    @Test
    public void assertThatFieldsAreEquals() {
        basicOperationEntity = new BasicOperationEntity();
        basicOperationEntity.setId(id);
        basicOperationEntity.setOperationType(operationType);
        basicOperationEntity.setAccount(accountEntity);
        basicOperationEntity.setReference(reference);
        basicOperationEntity.setAmount(amount);

        assertThat(basicOperationEntity).satisfies(basicOperationEntity1 -> assertEquals(id, basicOperationEntity1.getId()))
                .satisfies(basicOperationEntity1 -> assertEquals(operationType, basicOperationEntity1.getOperationType()))
                .satisfies(basicOperationEntity1 -> assertEquals(accountEntity, basicOperationEntity1.getAccount()))
                .satisfies(basicOperationEntity1 -> assertEquals(reference, basicOperationEntity1.getReference()))
                .satisfies(basicOperationEntity1 -> assertEquals(amount, basicOperationEntity1.getAmount()));




    }

}
