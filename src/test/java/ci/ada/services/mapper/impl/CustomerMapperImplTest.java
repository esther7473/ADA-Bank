package ci.ada.services.mapper.impl;

import ci.ada.models.entity.BankEntity;
import ci.ada.models.entity.CustomerEntity;
import ci.ada.models.entity.UserAccountEntity;
import ci.ada.services.dto.BankDTO;
import ci.ada.services.dto.CustomerDTO;
import ci.ada.services.dto.UserAccountDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerMapperImplTest {

    private static final Long ID = 1L;
    private static final String URL_PICTURE = "http://img.com/pic.png";

    private CustomerDTO customerDTO;
    private CustomerEntity customerEntity;
    private BankDTO bankDTO;
    private BankEntity bankEntity;
    private UserAccountDTO userAccountDTO;
    private UserAccountEntity userAccountEntity;

    private ModelMapper modelMapper;
    private CustomerMapperImpl customerMapper;

    @BeforeEach
    void setUp() {
        modelMapper = mock(ModelMapper.class);
        customerMapper = new CustomerMapperImpl(modelMapper);

        // Bank
        bankDTO = new BankDTO();
        bankDTO.setId(10L);
        bankDTO.setName("BankTest");

        bankEntity = new BankEntity();
        bankEntity.setId(10L);
        bankEntity.setName("BankTest");

        // UserAccount
        userAccountDTO = new UserAccountDTO();
        userAccountDTO.setId(20L);
        userAccountDTO.setLogin("testLogin");

        userAccountEntity = new UserAccountEntity();
        userAccountEntity.setId(20L);
        userAccountEntity.setLogin("testLogin");

        // DTO
        customerDTO = new CustomerDTO();
        customerDTO.setId(ID);
        customerDTO.setUrlPicture(URL_PICTURE);
        customerDTO.setBank(bankDTO);
        customerDTO.setUserAccountDTO(userAccountDTO);

        // Entity
        customerEntity = new CustomerEntity();
        customerEntity.setId(ID);
        customerEntity.setUrlPicture(URL_PICTURE);
        customerEntity.setBankEntity(bankEntity);
        customerEntity.setUserAccountEntity(userAccountEntity);
    }

    @Test
    void givenCustomerDTO_whenToEntity_thenReturnCustomerEntity() {
        when(modelMapper.map(bankDTO, BankEntity.class)).thenReturn(bankEntity);
        when(modelMapper.map(userAccountDTO, UserAccountEntity.class)).thenReturn(userAccountEntity);

        CustomerEntity result = customerMapper.toEntity(customerDTO);

        assertNotNull(result);
        assertEquals(customerEntity.getId(), result.getId());
        assertEquals(customerEntity.getUrlPicture(), result.getUrlPicture());
        assertEquals(customerEntity.getBankEntity().getId(), result.getBankEntity().getId());
        assertEquals(customerEntity.getUserAccountEntity().getId(), result.getUserAccountEntity().getId());
    }

    @Test
    void givenCustomerEntity_whenToDTO_thenReturnCustomerDTO() {
        when(modelMapper.map(bankEntity, BankDTO.class)).thenReturn(bankDTO);
        when(modelMapper.map(userAccountEntity, UserAccountDTO.class)).thenReturn(userAccountDTO);

        CustomerDTO result = customerMapper.toDTO(customerEntity);

        assertNotNull(result);
        assertEquals(customerDTO.getId(), result.getId());
        assertEquals(customerDTO.getUrlPicture(), result.getUrlPicture());
        assertEquals(customerDTO.getBank().getId(), result.getBank().getId());
        assertEquals(customerDTO.getUserAccountDTO().getId(), result.getUserAccountDTO().getId());
    }

    @Test
    void givenEntityList_whenToDTOs_thenReturnDTOList() {
        when(modelMapper.map(bankEntity, BankDTO.class)).thenReturn(bankDTO);
        when(modelMapper.map(userAccountEntity, UserAccountDTO.class)).thenReturn(userAccountDTO);

        List<CustomerDTO> result = customerMapper.toDTOs(List.of(customerEntity));

        assertNotNull(result);
        assertEquals(1, result.size());

        CustomerDTO mapped = result.get(0);
        assertEquals(customerDTO.getId(), mapped.getId());
        assertEquals(customerDTO.getUrlPicture(), mapped.getUrlPicture());
        assertEquals(customerDTO.getBank().getId(), mapped.getBank().getId());
        assertEquals(customerDTO.getUserAccountDTO().getId(), mapped.getUserAccountDTO().getId());
    }
}