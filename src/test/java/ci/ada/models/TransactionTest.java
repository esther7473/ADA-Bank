package ci.ada.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionTest {

    private static class TransactionImpl extends Transaction {}

    private final Long id = 100L;
    private final String reference = "TRANS-001";
    private final BigDecimal amount = new BigDecimal("1500.75");

    private TransactionImpl transaction;

    @BeforeEach
    void setUp() {
        transaction = new TransactionImpl();
    }

    @Test
    void assertThatFieldsAreEquals() {
        transaction.setId(id);
        transaction.setReference(reference);
        transaction.setAmount(amount);

        assertThat(transaction).isNotNull()
                .satisfies(t -> assertEquals(id, t.getId()))
                .satisfies(t -> assertEquals(reference, t.getReference()))
                .satisfies(t -> assertEquals(amount, t.getAmount()));
    }
}
