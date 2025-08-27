package ci.ada.services.dto;

import ci.ada.models.enums.AccountStatus;
import ci.ada.models.enums.AccountType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountDTOTest {

    private final Long id = 1L;
    private final String numberAccount = "ACC-123";
    private final BigDecimal balance = new BigDecimal("1500.50");
    private final AccountType accountType = AccountType.COURANT;
    private final AccountStatus accountStatus = AccountStatus.ACTIF;
    private final CustomerDTO customer = new CustomerDTO();

    private AccountDTO accountDTO;

    @BeforeEach
    void setUp() {
        accountDTO = new AccountDTO();
    }

    @Test
    void assertThatFieldsAreEquals() {
        // ✅ Via setters
        accountDTO.setId(id);
        accountDTO.setNumberAccount(numberAccount);
        accountDTO.setBalance(balance);
        accountDTO.setAccountType(accountType);
        accountDTO.setAccountStatus(accountStatus);
        accountDTO.setCustomer(customer);

        assertThat(accountDTO).isNotNull()
                .satisfies(a -> assertEquals(id, a.getId()))
                .satisfies(a -> assertEquals(numberAccount, a.getNumberAccount()))
                .satisfies(a -> assertEquals(balance, a.getBalance()))
                .satisfies(a -> assertEquals(accountType, a.getAccountType()))
                .satisfies(a -> assertEquals(accountStatus, a.getAccountStatus()))
                .satisfies(a -> assertEquals(customer, a.getCustomer()));

        // ✅ Via constructeur all-args
        AccountDTO other = new AccountDTO(id, numberAccount, balance, accountType, accountStatus, customer);

        assertThat(other).isNotNull()
                .satisfies(a -> assertEquals(id, a.getId()))
                .satisfies(a -> assertEquals(numberAccount, a.getNumberAccount()))
                .satisfies(a -> assertEquals(balance, a.getBalance()))
                .satisfies(a -> assertEquals(accountType, a.getAccountType()))
                .satisfies(a -> assertEquals(accountStatus, a.getAccountStatus()))
                .satisfies(a -> assertEquals(customer, a.getCustomer()));
    }
}
