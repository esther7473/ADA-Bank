package ci.ada.services.impl;

import ci.ada.Repository.AccountRepository;
import ci.ada.models.entity.AccountEntity;
import ci.ada.models.entity.CustomerEntity;
import ci.ada.models.entity.UserAccountEntity;
import ci.ada.models.enums.AccountStatus;
import ci.ada.models.enums.AccountType;
import ci.ada.services.dto.AccountDTO;
import ci.ada.services.dto.CustomerDTO;
import ci.ada.services.dto.UserAccountDTO;
import ci.ada.services.mapper.impl.AccountMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class AccountServiceImplTest {


    private static final Long id = 3L;

    private static final String numberAccount = "hhdhdhd";

    private static final BigDecimal balance =  new BigDecimal("100");

    private static final AccountType accountType = AccountType.COURANT;

    private static final AccountStatus accountStatus = AccountStatus.ACTIF;

    private static  AccountDTO  accountDTO;
    private static  AccountDTO  accountDTOResponse;

    private static AccountEntity   accountEntity;
    private static AccountEntity   accountEntityResponse;

    private static final List<AccountDTO> accountDTOList = new ArrayList<>();
    
    private static final List<AccountEntity> accountEntitiesList = new ArrayList<>();

    private static  CustomerDTO customerDTO ;

    private static  CustomerEntity customerEntity ;

    private   AccountRepository accountRepository;

    private   AccountMapperImpl accountMapper;

    private static AccountServiceImpl accountService;

    @BeforeEach
    void setUp() {
        accountRepository = mock(AccountRepository.class);
        accountMapper = mock(AccountMapperImpl.class);
        accountService = new AccountServiceImpl(accountRepository, accountMapper);


        accountDTO = new AccountDTO();
        //accountDTO.setId(id);
        accountDTO.setAccountType(accountType);
        accountDTO.setAccountStatus(accountStatus);
        accountDTO.setBalance(balance);
        accountDTO.setNumberAccount(numberAccount);
        accountDTO.setCustomer(customerDTO);

        accountEntity = new AccountEntity();
        //accountEntity.setId(id);
        accountEntity.setCustomer(customerEntity);
        accountEntity.setAccountType(accountType);
        accountEntity.setAccountStatus(accountStatus);
        accountEntity.setBalance(balance);
        accountEntity.setNumberAccount(numberAccount);

        accountEntityResponse = new AccountEntity();
        accountEntityResponse.setId(id);
        accountEntityResponse.setCustomer(customerEntity);
        accountEntityResponse.setAccountType(accountType);
        accountEntityResponse.setAccountStatus(accountStatus);
        accountEntityResponse.setBalance(balance);
        accountEntityResponse.setNumberAccount(numberAccount);

        accountDTOResponse = new AccountDTO();
        accountDTOResponse.setId(id);
        accountDTOResponse.setAccountType(accountType);
        accountDTOResponse.setAccountStatus(accountStatus);
        accountDTOResponse.setBalance(balance);
        accountDTOResponse.setNumberAccount(numberAccount);
        accountDTOResponse.setCustomer(customerDTO);

        customerDTO = CustomerDTO
                .builder()
                .urlPicture("sjsjs")
                .id(6L)
                .build();

        customerEntity = CustomerEntity.builder()
                .urlPicture("sjsjs")
                .id(6L)
                .build();


        accountEntitiesList.add(accountEntity);
        accountEntitiesList.add(accountEntityResponse);

        accountDTOList.add(accountDTO);
        accountDTOList.add(accountDTOResponse);
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
        when(accountRepository.findById(id)).thenReturn(Optional.ofNullable(accountEntityResponse));
        when(accountMapper.toDTO(accountEntityResponse)).thenReturn(accountDTOResponse);

        AccountDTO result = accountService.partialUpdate(accountDTOResponse);

        verify(accountRepository).save(accountEntityResponse);
        verify(accountRepository).findById(id);
        verify(accountMapper).toDTO(accountEntityResponse);

        assertEquals(accountDTOResponse, result);
    }

    @Test
    void givenId_whenDelete_thenReturnVoid() {

        doNothing().when(accountRepository).deleteById(id);

        accountService.delete(id);

        verify(accountRepository).deleteById(id);
    }

    @Test
    void givenId_whenGetById_returnAccountDTO() {
        when(accountRepository.findById(id)).thenReturn(Optional.ofNullable(accountEntityResponse));
        when(accountMapper.toDTO(accountEntityResponse)).thenReturn(accountDTOResponse);
        AccountDTO result = accountService.getById(id);
        verify(accountRepository).findById(id);
        assertEquals(accountDTOResponse, result);
    }

    @Test
    void whenGetAll_return_AccountDTOList() {
        when(accountRepository.findAll()).thenReturn(accountEntitiesList);

        for (int i = 0; i < accountEntitiesList.size(); i++) {
            when(accountMapper.toDTO(accountEntitiesList.get(i))).thenReturn(accountDTOList.get(i));
        }

        List<AccountDTO> result = accountService.getAll();

        verify(accountRepository).findAll();

        assertEquals(accountDTOList, result);
        assertEquals(accountDTOList.size(), result.size());
    }
}