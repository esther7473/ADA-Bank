package ci.ada.services.mapper.impl;
import ci.ada.models.entity.BankEntity;
import ci.ada.models.entity.UserAccountEntity;
import ci.ada.services.dto.BankDTO;
import ci.ada.services.dto.UserAccountDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

class BankMapperImplTest {

    private static final Long bankId = 1L;
    private static final String bankName = "MyBank";

    private static final Long adminId = 10L;
    private static final Integer customers = 1;
    private static final String adminLogin = "adminUser";


    private BankDTO bankDTO;
    private BankEntity bankEntity;
    private UserAccountDTO adminDTO;
    private UserAccountEntity adminEntity;

    private static ModelMapper modelMapper;
    private static BankMapperImpl bankMapper;

    private static final List<BankEntity> bankEntityList = new ArrayList<>();
    private static final List<BankDTO> bankDTOList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        modelMapper = mock(ModelMapper.class);
        bankMapper = new BankMapperImpl(modelMapper);

        // Mock admin objects
        adminDTO = new UserAccountDTO();
        adminDTO.setId(adminId);
        adminDTO.setLogin(adminLogin);

        adminEntity = new UserAccountEntity();
        adminEntity.setId(adminId);
        adminEntity.setLogin(adminLogin);

        // Mock bank objects
        bankDTO = new BankDTO();
        bankDTO.setId(bankId);
        bankDTO.setName(bankName);
        bankDTO.setAdmin(adminDTO);
        bankDTO.setCustomers(1);

        bankEntity = new BankEntity();
        bankEntity.setId(bankId);
        bankEntity.setName(bankName);
        bankEntity.setAdmin(adminEntity);
        bankEntity.setCustomers(1);

        bankDTOList.add(bankDTO);
        bankEntityList.add(bankEntity);
    }

    @Test
    void givenBankDTO_whenToEntity_thenReturnBankEntity() {
        when(modelMapper.map(adminDTO, UserAccountEntity.class)).thenReturn(adminEntity);

        BankEntity result = bankMapper.toEntity(bankDTO);

        assertNotNull(result);
        assertEquals(bankEntity.getId(), result.getId());
        assertEquals(bankEntity.getName(), result.getName());
        assertNotNull(result.getAdmin());
        assertEquals(bankEntity.getAdmin().getId(), result.getAdmin().getId());
        assertEquals(bankEntity.getAdmin().getLogin(), result.getAdmin().getLogin());
        assertNotNull(result.getCustomers());
        assertEquals(bankEntity.getCustomers(), result.getCustomers());
    }

    @Test
    void givenBankEntity_whenToDTO_thenReturnBankDTO() {
        when(modelMapper.map(adminEntity, UserAccountDTO.class)).thenReturn(adminDTO);

        BankDTO result = bankMapper.toDTO(bankEntity);

        assertNotNull(result);
        assertEquals(bankDTO.getId(), result.getId());
        assertEquals(bankDTO.getName(), result.getName());
        assertNotNull(result.getAdmin());
        assertEquals(bankDTO.getAdmin().getId(), result.getAdmin().getId());
        assertEquals(bankDTO.getAdmin().getLogin(), result.getAdmin().getLogin());
        assertNotNull(result.getCustomers());
        assertEquals(bankDTO.getCustomers(), result.getCustomers());
    }

    @Test
    void givenBankEntityList_whenToDTOs_thenReturnBankDTOList() {
        when(modelMapper.map(any(UserAccountEntity.class), eq(UserAccountDTO.class))).thenReturn(adminDTO);

        List<BankDTO> result = bankMapper.toDTOs(bankEntityList);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        for (BankDTO bank : result) {
            assertEquals(bankDTO.getId(), bank.getId());
            assertEquals(bankDTO.getName(), bank.getName());
            assertNotNull(bank.getAdmin());
            assertEquals(bankDTO.getAdmin().getId(), bank.getAdmin().getId());
            assertEquals(bankDTO.getAdmin().getLogin(), bank.getAdmin().getLogin());
            assertNotNull(bank.getCustomers());
            assertEquals(bankDTO.getCustomers(), bank.getCustomers());
        }
    }
}
