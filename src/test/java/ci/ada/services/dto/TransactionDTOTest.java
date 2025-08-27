package ci.ada.services.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionDTOTest {

    private final String reference = "TXN-001";
    private final BigDecimal amount = new BigDecimal("1500.75");

    private TransactionDTO transactionDTO;

    @BeforeEach
    void setUp() {
        transactionDTO = new TransactionDTO();
    }

    @Test
    void assertThatFieldsAreEquals() {
        transactionDTO.setReference(reference);
        transactionDTO.setAmount(amount);

        assertThat(transactionDTO).isNotNull()
                .satisfies(t -> assertEquals(reference, t.getReference()))
                .satisfies(t -> assertEquals(amount, t.getAmount()));

        TransactionDTO other = new TransactionDTO(reference, amount);

        assertThat(other).isNotNull()
                .satisfies(t -> assertEquals(reference, t.getReference()))
                .satisfies(t -> assertEquals(amount, t.getAmount()));
    }
}
