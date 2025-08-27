package ci.ada.services.mapper.impl;

import ci.ada.models.entity.AccountEntity;
import ci.ada.models.entity.BasicOperationEntity;
import ci.ada.models.enums.OperationType;
import ci.ada.services.dto.AccountDTO;
import ci.ada.services.dto.BasicOperationDTO;
import ci.ada.services.mapper.AccountMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class BasicOperationMapperImplTest {

    private static final Long OPERATION_ID = 1L;
    private static final String REFERENCE = "OP-123";
    private static final BigDecimal amount = new BigDecimal("250.00");
    private static final OperationType operationType = OperationType.DEPOSIT;

    private BasicOperationDTO basicOperationDTO;
    private BasicOperationEntity basicOperationEntity;
    private AccountDTO accountDTO;
    private AccountEntity accountEntity;

    private AccountMapper accountMapper;
    private BasicOperationMapperImpl basicOperationMapper;

    @BeforeEach
    void setUp() {
        accountMapper = mock(AccountMapper.class);
        basicOperationMapper = new BasicOperationMapperImpl(accountMapper);

        // Mock Account
        accountDTO = new AccountDTO();
        accountDTO.setId(10L);
        accountDTO.setNumberAccount("ACC-001");

        accountEntity = new AccountEntity();
        accountEntity.setId(10L);
        accountEntity.setNumberAccount("ACC-001");

        // Mock BasicOperation DTO
        basicOperationDTO = new BasicOperationDTO();
        basicOperationDTO.setId(OPERATION_ID);
        basicOperationDTO.setOperationType(operationType);
        basicOperationDTO.setReference(REFERENCE);
        basicOperationDTO.setAmount(amount);
        basicOperationDTO.setAccount(accountDTO);

        // Mock BasicOperation Entity
        basicOperationEntity = new BasicOperationEntity();
        basicOperationEntity.setId(OPERATION_ID);
        basicOperationEntity.setOperationType(operationType);
        basicOperationEntity.setReference(REFERENCE);
        basicOperationEntity.setAmount(amount);
        basicOperationEntity.setAccount(accountEntity);
    }

    @Test
    void givenBasicOperationDTO_whenToEntity_thenReturnBasicOperationEntity() {
        when(accountMapper.toEntity(accountDTO)).thenReturn(accountEntity);

        BasicOperationEntity result = basicOperationMapper.toEntity(basicOperationDTO);

        assertNotNull(result);
        assertEquals(basicOperationEntity.getId(), result.getId());
        assertEquals(basicOperationEntity.getOperationType(), result.getOperationType());
        assertEquals(basicOperationEntity.getReference(), result.getReference());
        assertEquals(basicOperationEntity.getAmount(), result.getAmount());
        assertNotNull(result.getAccount());
        assertEquals(basicOperationEntity.getAccount().getId(), result.getAccount().getId());
        assertEquals(basicOperationEntity.getAccount().getNumberAccount(), result.getAccount().getNumberAccount());
    }

    @Test
    void givenBasicOperationEntity_whenToDTO_thenReturnBasicOperationDTO() {
        when(accountMapper.toDTO(accountEntity)).thenReturn(accountDTO);

        BasicOperationDTO result = basicOperationMapper.toDTO(basicOperationEntity);

        assertNotNull(result);
        assertEquals(basicOperationDTO.getId(), result.getId());
        assertEquals(basicOperationDTO.getOperationType(), result.getOperationType());
        assertEquals(basicOperationDTO.getReference(), result.getReference());
        assertEquals(basicOperationDTO.getAmount(), result.getAmount());
        assertNotNull(result.getAccount());
        assertEquals(basicOperationDTO.getAccount().getId(), result.getAccount().getId());
        assertEquals(basicOperationDTO.getAccount().getNumberAccount(), result.getAccount().getNumberAccount());
    }

    @Test
    void givenEntityList_whenToDTOs_thenReturnDTOList() {
        when(accountMapper.toDTO(accountEntity)).thenReturn(accountDTO);

        List<BasicOperationDTO> result = basicOperationMapper.toDTOs(List.of(basicOperationEntity));

        assertNotNull(result);
        assertEquals(1, result.size());

        BasicOperationDTO mapped = result.get(0);
        assertEquals(basicOperationDTO.getId(), mapped.getId());
        assertEquals(basicOperationDTO.getOperationType(), mapped.getOperationType());
        assertEquals(basicOperationDTO.getReference(), mapped.getReference());
        assertEquals(basicOperationDTO.getAmount(), mapped.getAmount());
        assertNotNull(mapped.getAccount());
        assertEquals(basicOperationDTO.getAccount().getId(), mapped.getAccount().getId());
        assertEquals(basicOperationDTO.getAccount().getNumberAccount(), mapped.getAccount().getNumberAccount());
    }
}
