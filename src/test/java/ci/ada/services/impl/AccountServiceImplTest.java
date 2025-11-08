package ci.ada.services.impl;

import ci.ada.Repository.AccountRepository;
import ci.ada.models.entity.AccountEntity;
import ci.ada.models.entity.CustomerEntity;
import ci.ada.models.enums.AccountStatus;
import ci.ada.models.enums.AccountType;
import ci.ada.services.dto.AccountDTO;
import ci.ada.services.dto.CustomerDTO;
import ci.ada.services.mapper.impl.AccountMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountServiceImplTest {

    private static final Long ID = 3L;
    private static final String NUMBER_ACCOUNT = "hhdhdhd";
    private static final BigDecimal BALANCE = new BigDecimal("100");
    private static final AccountType TYPE = AccountType.COURANT;
    private static final AccountStatus STATUS = AccountStatus.ACTIF;

    private static AccountDTO accountDTO;
    private static AccountDTO accountDTOResponse;

    private static AccountEntity accountEntity;
    private static AccountEntity accountEntityResponse;

    private static final List<AccountDTO> DTO_LIST = new ArrayList<>();
    private static final List<AccountEntity> ENTITY_LIST = new ArrayList<>();

    private static CustomerDTO customerDTO;
    private static CustomerEntity customerEntity;

    private AccountRepository accountRepository;
    private AccountMapperImpl accountMapper;
    private static AccountServiceImpl accountService;

    @BeforeEach
    void setUp() {
        accountRepository = mock(AccountRepository.class);
        accountMapper = mock(AccountMapperImpl.class);
        accountService = new AccountServiceImpl(accountRepository, accountMapper);

        customerDTO = CustomerDTO.builder()
                .urlPicture("sjsjs")
                .id(6L)
                .build();

        customerEntity = CustomerEntity.builder()
                .urlPicture("sjsjs")
                .id(6L)
                .build();

        accountDTO = new AccountDTO();
        accountDTO.setAccountType(TYPE);
        accountDTO.setAccountStatus(STATUS);
        accountDTO.setBalance(BALANCE);
        accountDTO.setNumberAccount(NUMBER_ACCOUNT);
        accountDTO.setCustomer(customerDTO);

        accountEntity = new AccountEntity();
        accountEntity.setCustomer(customerEntity);
        accountEntity.setAccountType(TYPE);
        accountEntity.setAccountStatus(STATUS);
        accountEntity.setBalance(BALANCE);
        accountEntity.setNumberAccount(NUMBER_ACCOUNT);

        accountEntityResponse = new AccountEntity();
        accountEntityResponse.setId(ID);
        accountEntityResponse.setCustomer(customerEntity);
        accountEntityResponse.setAccountType(TYPE);
        accountEntityResponse.setAccountStatus(STATUS);
        accountEntityResponse.setBalance(BALANCE);
        accountEntityResponse.setNumberAccount(NUMBER_ACCOUNT);

        accountDTOResponse = new AccountDTO();
        accountDTOResponse.setId(ID);
        accountDTOResponse.setAccountType(TYPE);
        accountDTOResponse.setAccountStatus(STATUS);
        accountDTOResponse.setBalance(BALANCE);
        accountDTOResponse.setNumberAccount(NUMBER_ACCOUNT);
        accountDTOResponse.setCustomer(customerDTO);

        ENTITY_LIST.add(accountEntity);
        ENTITY_LIST.add(accountEntityResponse);

        DTO_LIST.add(accountDTO);
        DTO_LIST.add(accountDTOResponse);
    }

    @Test
    void givenAccountDTO_whenSave_thenReturnAccountDTO() {
        when(accountMapper.toEntity(accountDTO)).thenReturn(accountEntity);
        when(accountRepository.save(accountEntity)).thenReturn(accountEntityResponse);
        when(accountMapper.toDTO(accountEntityResponse)).thenReturn(accountDTOResponse);

        AccountDTO result = accountService.save(accountDTO);

        verify(accountMapper).toEntity(accountDTO);
        verify(accountRepository).save(accountEntity);
        verify(accountMapper).toDTO(accountEntityResponse);

        assertEquals(accountDTOResponse, result);
    }

    @Test
    void givenAccountDTO_whenPartialUpdate_thenReturnAccountDTO() {
        when(accountRepository.save(accountEntityResponse)).thenReturn(accountEntityResponse);
        when(accountRepository.findById(ID)).thenReturn(Optional.ofNullable(accountEntityResponse));
        when(accountMapper.toDTO(accountEntityResponse)).thenReturn(accountDTOResponse);

        AccountDTO result = accountService.partialUpdate(accountDTOResponse);

        verify(accountRepository).save(accountEntityResponse);
        verify(accountRepository).findById(ID);
        verify(accountMapper).toDTO(accountEntityResponse);

        assertEquals(accountDTOResponse, result);
    }

    @Test
    void givenId_whenDelete_thenReturnVoid() {
        doNothing().when(accountRepository).deleteById(ID);
        accountService.delete(ID);
        verify(accountRepository).deleteById(ID);
    }

    @Test
    void givenId_whenGetById_returnAccountDTO() {
        when(accountRepository.findById(ID)).thenReturn(Optional.ofNullable(accountEntityResponse));
        when(accountMapper.toDTO(accountEntityResponse)).thenReturn(accountDTOResponse);
        AccountDTO result = accountService.getById(ID);
        verify(accountRepository).findById(ID);
        assertEquals(accountDTOResponse, result);
    }

    @Test
    void whenGetAll_return_AccountDTOList() {
        when(accountRepository.findAll()).thenReturn(ENTITY_LIST);

        for (int i = 0; i < ENTITY_LIST.size(); i++) {
            when(accountMapper.toDTO(ENTITY_LIST.get(i))).thenReturn(DTO_LIST.get(i));
        }

        List<AccountDTO> result = accountService.getAll();

        verify(accountRepository).findAll();

        assertEquals(DTO_LIST, result);
        assertEquals(DTO_LIST.size(), result.size());
    }
}
