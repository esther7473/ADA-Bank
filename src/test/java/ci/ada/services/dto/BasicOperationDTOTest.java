package ci.ada.services.dto;

import ci.ada.models.enums.OperationType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigDecimal;

class BasicOperationDTOTest {

    private final Long id = 1L;
    private final OperationType operationType = OperationType.DEPOSIT;
    private final AccountDTO account = new AccountDTO(1L, "ACC-123", new BigDecimal("1000.00"), null, null, null);

    private BasicOperationDTO basicOperationDTO;

    @BeforeEach
    void setUp() {
        basicOperationDTO = new BasicOperationDTO();
    }

    @Test
    void assertThatFieldsAreEquals() {
        basicOperationDTO.setId(id);
        basicOperationDTO.setOperationType(operationType);
        basicOperationDTO.setAccount(account);

        assertThat(basicOperationDTO).isNotNull()
                .satisfies(b -> assertEquals(id, b.getId()))
                .satisfies(b -> assertEquals(operationType, b.getOperationType()))
                .satisfies(b -> assertEquals(account, b.getAccount()));

        BasicOperationDTO other = new BasicOperationDTO(id, operationType, account);

        assertThat(other).isNotNull()
                .satisfies(b -> assertEquals(id, b.getId()))
                .satisfies(b -> assertEquals(operationType, b.getOperationType()))
                .satisfies(b -> assertEquals(account, b.getAccount()));
    }
}
