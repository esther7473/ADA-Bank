package ci.ada.services.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerDTOTest {

    private final Long id = 1L;
    private final String urlPicture = "url/image.png";
    private final BankDTO bank = new BankDTO(1L, "Banque Centrale", 100, new UserAccountDTO());
    private final List<AccountDTO> accounts = new ArrayList<>();
    private final UserAccountDTO userAccountDTO = new UserAccountDTO();

    private CustomerDTO customerDTO;

    @BeforeEach
    void setUp() {
        customerDTO = new CustomerDTO();
    }

    @Test
    void assertThatFieldsAreEquals() {
        customerDTO.setId(id);
        customerDTO.setUrlPicture(urlPicture);
        customerDTO.setBank(bank);
        customerDTO.setAccounts(accounts);
        customerDTO.setUserAccountDTO(userAccountDTO);

        assertThat(customerDTO).isNotNull()
                .satisfies(c -> assertEquals(id, c.getId()))
                .satisfies(c -> assertEquals(urlPicture, c.getUrlPicture()))
                .satisfies(c -> assertEquals(bank, c.getBank()))
                .satisfies(c -> assertEquals(accounts, c.getAccounts()))
                .satisfies(c -> assertEquals(userAccountDTO, c.getUserAccountDTO()));

        CustomerDTO other = new CustomerDTO(id, urlPicture, bank, accounts, userAccountDTO);

        assertThat(other).isNotNull()
                .satisfies(c -> assertEquals(id, c.getId()))
                .satisfies(c -> assertEquals(urlPicture, c.getUrlPicture()))
                .satisfies(c -> assertEquals(bank, c.getBank()))
                .satisfies(c -> assertEquals(accounts, c.getAccounts()))
                .satisfies(c -> assertEquals(userAccountDTO, c.getUserAccountDTO()));
    }
}
