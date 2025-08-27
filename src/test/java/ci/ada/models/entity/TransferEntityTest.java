package ci.ada.models.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TransferEntityTest {

    private final Long id = 1L;
    private final String reference = "REF-001";
    private final BigDecimal amount = BigDecimal.valueOf(500);

    private final AccountEntity source = new AccountEntity();
    private final AccountEntity destination = new AccountEntity();

    private TransferEntity transferEntity;

    @BeforeEach
    void setUp() {
        transferEntity = new TransferEntity();
    }

    @Test
    void assertThatFieldsAreEquals() {
        transferEntity.setId(id);
        transferEntity.setReference(reference);
        transferEntity.setAmount(amount);
        transferEntity.setSource(source);
        transferEntity.setDestination(destination);

        assertThat(transferEntity).isNotNull()
                .satisfies(t -> assertEquals(id, t.getId()))
                .satisfies(t -> assertEquals(reference, t.getReference()))
                .satisfies(t -> assertEquals(amount, t.getAmount()))
                .satisfies(t -> assertEquals(source, t.getSource()))
                .satisfies(t -> assertEquals(destination, t.getDestination()));

        TransferEntity other = new TransferEntity();
        other.setId(id);
        other.setReference(reference);
        other.setAmount(amount);
        other.setSource(source);
        other.setDestination(destination);

        assertThat(other).isNotNull()
                .satisfies(t -> assertEquals(id, t.getId()))
                .satisfies(t -> assertEquals(reference, t.getReference()))
                .satisfies(t -> assertEquals(amount, t.getAmount()))
                .satisfies(t -> assertEquals(source, t.getSource()))
                .satisfies(t -> assertEquals(destination, t.getDestination()));
    }
}
