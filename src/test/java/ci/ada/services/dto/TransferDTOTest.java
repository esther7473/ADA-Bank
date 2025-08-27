package ci.ada.services.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TransferDTOTest {

    private final Long id = 1L;
    private final AccountDTO source = new AccountDTO(1L, "SRC-001", new BigDecimal("1000.00"), null, null, null);
    private final AccountDTO destination = new AccountDTO(2L, "DST-001", new BigDecimal("500.00"), null, null, null);

    private TransferDTO transferDTO;

    @BeforeEach
    void setUp() {
        transferDTO = new TransferDTO();
    }

    @Test
    void assertThatFieldsAreEquals() {
        transferDTO.setId(id);
        transferDTO.setSource(source);
        transferDTO.setDestination(destination);

        assertThat(transferDTO).isNotNull()
                .satisfies(t -> assertEquals(id, t.getId()))
                .satisfies(t -> assertEquals(source, t.getSource()))
                .satisfies(t -> assertEquals(destination, t.getDestination()));

        TransferDTO other = new TransferDTO(id, source, destination);

        assertThat(other).isNotNull()
                .satisfies(t -> assertEquals(id, t.getId()))
                .satisfies(t -> assertEquals(source, t.getSource()))
                .satisfies(t -> assertEquals(destination, t.getDestination()));
    }
}
