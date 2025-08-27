package ci.ada.services.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BankDTOTest {

    private final Long id = 1L;
    private final String name = "Banque Centrale";
    private final Integer customers = 100;
    private final UserAccountDTO admin = new UserAccountDTO();

    private BankDTO bankDTO;

    @BeforeEach
    void setUp() {
        bankDTO = new BankDTO();
    }

    @Test
    void assertThatFieldsAreEquals() {
        bankDTO.setId(id);
        bankDTO.setName(name);
        bankDTO.setCustomers(customers);
        bankDTO.setAdmin(admin);

        assertThat(bankDTO).isNotNull()
                .satisfies(b -> assertEquals(id, b.getId()))
                .satisfies(b -> assertEquals(name, b.getName()))
                .satisfies(b -> assertEquals(customers, b.getCustomers()))
                .satisfies(b -> assertEquals(admin, b.getAdmin()));

        BankDTO other = new BankDTO(id, name, customers, admin);

        assertThat(other).isNotNull()
                .satisfies(b -> assertEquals(id, b.getId()))
                .satisfies(b -> assertEquals(name, b.getName()))
                .satisfies(b -> assertEquals(customers, b.getCustomers()))
                .satisfies(b -> assertEquals(admin, b.getAdmin()));
    }
}
