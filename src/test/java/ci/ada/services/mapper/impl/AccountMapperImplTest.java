package ci.ada.services.mapper.impl;

import ci.ada.models.entity.AccountEntity;
import ci.ada.models.entity.CustomerEntity;
import ci.ada.models.enums.AccountStatus;
import ci.ada.models.enums.AccountType;
import ci.ada.services.dto.AccountDTO;
import ci.ada.services.dto.CustomerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

class AccountMapperImplTest {

    private static final Long id = 1L;
    private static final String numberAccount = "ACC-12345";
    private static final BigDecimal balance = new BigDecimal("5000.00");
    private static final AccountType accountType = AccountType.COURANT;
    private static final AccountStatus accountStatus = AccountStatus.ACTIF;

    private static final Long customerId = 10L;
    private static final String customerUrlPicture = "pic.png";

    private AccountDTO accountDTO;
    private AccountEntity accountEntity;
    private CustomerDTO customerDTO;
    private CustomerEntity customerEntity;

    private static ModelMapper modelMapper;
    private static AccountMapperImpl accountMapper;

    private static final List<AccountEntity> accountEntityList = new ArrayList<>();
    private static final List<AccountDTO> accountDTOList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        modelMapper = mock(ModelMapper.class);
        accountMapper = new AccountMapperImpl(modelMapper);

        customerDTO = new CustomerDTO();
        customerDTO.setId(customerId);
        customerDTO.setUrlPicture(customerUrlPicture);

        customerEntity = new CustomerEntity();
        customerEntity.setId(customerId);
        customerEntity.setUrlPicture(customerUrlPicture);

        accountDTO = new AccountDTO();
        accountDTO.setId(id);
        accountDTO.setNumberAccount(numberAccount);
        accountDTO.setBalance(balance);
        accountDTO.setAccountType(accountType);
        accountDTO.setAccountStatus(accountStatus);
        accountDTO.setCustomer(customerDTO);

        accountEntity = new AccountEntity();
        accountEntity.setId(id);
        accountEntity.setNumberAccount(numberAccount);
        accountEntity.setBalance(balance);
        accountEntity.setAccountType(accountType);
        accountEntity.setAccountStatus(accountStatus);
        accountEntity.setCustomer(customerEntity);

        accountDTOList.add(accountDTO);
        accountEntityList.add(accountEntity);
    }

    @Test
    void givenAccountDTO_whenToEntity_thenReturnAccountEntity() {
        when(modelMapper.map(customerDTO, CustomerEntity.class)).thenReturn(customerEntity);

        AccountEntity result = accountMapper.toEntity(accountDTO);

        assertNotNull(result);
        assertEquals(accountEntity.getId(), result.getId());
        assertEquals(accountEntity.getNumberAccount(), result.getNumberAccount());
        assertEquals(accountEntity.getBalance(), result.getBalance());
        assertEquals(accountEntity.getAccountType(), result.getAccountType());
        assertEquals(accountEntity.getAccountStatus(), result.getAccountStatus());
        assertNotNull(result.getCustomer());
        assertEquals(accountEntity.getCustomer().getId(), result.getCustomer().getId());
        assertEquals(accountEntity.getCustomer().getUrlPicture(), result.getCustomer().getUrlPicture());
    }

    @Test
    void givenAccountEntity_whenToDTO_thenReturnAccountDTO() {
        when(modelMapper.map(customerEntity, CustomerDTO.class)).thenReturn(customerDTO);

        AccountDTO result = accountMapper.toDTO(accountEntity);

        assertNotNull(result);
        assertEquals(accountDTO.getId(), result.getId());
        assertEquals(accountDTO.getNumberAccount(), result.getNumberAccount());
        assertEquals(accountDTO.getBalance(), result.getBalance());
        assertEquals(accountDTO.getAccountType(), result.getAccountType());
        assertEquals(accountDTO.getAccountStatus(), result.getAccountStatus());
        assertNotNull(result.getCustomer());
        assertEquals(accountDTO.getCustomer().getId(), result.getCustomer().getId());
        assertEquals(accountDTO.getCustomer().getUrlPicture(), result.getCustomer().getUrlPicture());
    }

    @Test
    void givenAccountEntityList_whenToDTOs_thenReturnAccountDTOList() {
        when(modelMapper.map(any(CustomerEntity.class), eq(CustomerDTO.class))).thenReturn(customerDTO);

        List<AccountDTO> result = accountMapper.toDTOs(accountEntityList);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        for (AccountDTO acc : result) {
            assertEquals(accountDTO.getId(), acc.getId());
            assertEquals(accountDTO.getNumberAccount(), acc.getNumberAccount());
            assertEquals(accountDTO.getBalance(), acc.getBalance());
            assertEquals(accountDTO.getAccountType(), acc.getAccountType());
            assertEquals(accountDTO.getAccountStatus(), acc.getAccountStatus());
            assertNotNull(acc.getCustomer());
            assertEquals(accountDTO.getCustomer().getId(), acc.getCustomer().getId());
            assertEquals(accountDTO.getCustomer().getUrlPicture(), acc.getCustomer().getUrlPicture());
        }
    }
}
