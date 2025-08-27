package ci.ada.models.entity;

import ci.ada.models.enums.AccountStatus;
import ci.ada.models.enums.AccountType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class AccountEntityTest {
    private final String numberAccount = "username";
    private final BigDecimal balance = BigDecimal.valueOf(200);
    private final AccountType accountType = AccountType.COURANT;
    private final AccountStatus accountStatus = AccountStatus.ACTIF;
    private final CustomerEntity customer = new CustomerEntity();
    private final Long id = 2L;

    private AccountEntity accountEntity;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void assertThatFieldsAreEquals() {
        accountEntity = new AccountEntity();
        accountEntity.setId(id);
        accountEntity.setAccountStatus(accountStatus);
        accountEntity.setBalance(balance);
        accountEntity.setNumberAccount(numberAccount);
        accountEntity.setCustomer(customer);
        accountEntity.setAccountType(accountType);

        assertThat(accountEntity).isNotNull()
                .satisfies(accountEntity1 -> assertEquals(id, accountEntity1.getId()))
                .satisfies(accountEntity1 -> assertEquals(numberAccount, accountEntity1.getNumberAccount()))
                .satisfies(accountEntity1 -> assertEquals(balance, accountEntity1.getBalance()))
                .satisfies(accountEntity1 -> assertEquals(accountStatus, accountEntity1.getAccountStatus()))
                .satisfies(accountEntity1 -> assertEquals(customer, accountEntity1.getCustomer()))
                .satisfies(accountEntity1 -> assertEquals(accountType, accountEntity1.getAccountType()));

        accountEntity = new AccountEntity(id, numberAccount, balance, accountType, accountStatus, customer);

        assertThat(accountEntity).isNotNull()
                .satisfies(accountEntity1 -> assertEquals(id, accountEntity1.getId()))
                .satisfies(accountEntity1 -> assertEquals(numberAccount, accountEntity1.getNumberAccount()))
                .satisfies(accountEntity1 -> assertEquals(balance, accountEntity1.getBalance()))
                .satisfies(accountEntity1 -> assertEquals(accountStatus, accountEntity1.getAccountStatus()))
                .satisfies(accountEntity1 -> assertEquals(customer, accountEntity1.getCustomer()))
                .satisfies(accountEntity1 -> assertEquals(accountType, accountEntity1.getAccountType()));
    }


}