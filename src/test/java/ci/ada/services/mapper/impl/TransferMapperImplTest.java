package ci.ada.services.mapper.impl;

import ci.ada.models.entity.AccountEntity;
import ci.ada.models.entity.TransferEntity;
import ci.ada.services.dto.AccountDTO;
import ci.ada.services.dto.TransferDTO;
import ci.ada.services.mapper.AccountMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransferMapperImplTest {

    private static final Long ID = 1L;
    private static final String reference = "REF-123";
    private static final BigDecimal amount = new BigDecimal("2500.00");

    private TransferDTO transferDTO;
    private TransferEntity transferEntity;
    private AccountDTO sourceDTO;
    private AccountDTO destinationDTO;
    private AccountEntity sourceEntity;
    private AccountEntity destinationEntity;

    private AccountMapper accountMapper;
    private TransferMapperImpl transferMapper;

    @BeforeEach
    void setUp() {
        accountMapper = mock(AccountMapper.class);
        transferMapper = new TransferMapperImpl(accountMapper);

        // Source Account
        sourceDTO = new AccountDTO();
        sourceDTO.setId(10L);
        sourceDTO.setNumberAccount("SRC123");

        sourceEntity = new AccountEntity();
        sourceEntity.setId(10L);
        sourceEntity.setNumberAccount("SRC123");

        // Destination Account
        destinationDTO = new AccountDTO();
        destinationDTO.setId(20L);
        destinationDTO.setNumberAccount("DST456");

        destinationEntity = new AccountEntity();
        destinationEntity.setId(20L);
        destinationEntity.setNumberAccount("DST456");

        // DTO
        transferDTO = new TransferDTO();
        transferDTO.setId(ID);
        transferDTO.setSource(sourceDTO);
        transferDTO.setDestination(destinationDTO);
        transferDTO.setReference(reference);
        transferDTO.setAmount(amount);

        // Entity
        transferEntity = new TransferEntity();
        transferEntity.setId(ID);
        transferEntity.setSource(sourceEntity);
        transferEntity.setDestination(destinationEntity);
        transferEntity.setReference(reference);
        transferEntity.setAmount(amount);
    }

    @Test
    void givenTransferDTO_whenToEntity_thenReturnTransferEntity() {
        when(accountMapper.toEntity(sourceDTO)).thenReturn(sourceEntity);
        when(accountMapper.toEntity(destinationDTO)).thenReturn(destinationEntity);

        TransferEntity result = transferMapper.toEntity(transferDTO);

        assertNotNull(result);
        assertEquals(transferEntity.getId(), result.getId());
        assertEquals(transferEntity.getReference(), result.getReference());
        assertEquals(transferEntity.getAmount(), result.getAmount());
        assertEquals(transferEntity.getSource().getId(), result.getSource().getId());
        assertEquals(transferEntity.getDestination().getId(), result.getDestination().getId());
    }

    @Test
    void givenTransferEntity_whenToDTO_thenReturnTransferDTO() {
        when(accountMapper.toDTO(sourceEntity)).thenReturn(sourceDTO);
        when(accountMapper.toDTO(destinationEntity)).thenReturn(destinationDTO);

        TransferDTO result = transferMapper.toDTO(transferEntity);

        assertNotNull(result);
        assertEquals(transferDTO.getId(), result.getId());
        assertEquals(transferDTO.getReference(), result.getReference());
        assertEquals(transferDTO.getAmount(), result.getAmount());
        assertEquals(transferDTO.getSource().getId(), result.getSource().getId());
        assertEquals(transferDTO.getDestination().getId(), result.getDestination().getId());
    }

    @Test
    void givenEntityList_whenToDTOs_thenReturnDTOList() {
        when(accountMapper.toDTO(sourceEntity)).thenReturn(sourceDTO);
        when(accountMapper.toDTO(destinationEntity)).thenReturn(destinationDTO);

        List<TransferDTO> result = transferMapper.toDTOs(List.of(transferEntity));

        assertNotNull(result);
        assertEquals(1, result.size());

        TransferDTO mapped = result.get(0);
        assertEquals(transferDTO.getId(), mapped.getId());
        assertEquals(transferDTO.getReference(), mapped.getReference());
        assertEquals(transferDTO.getAmount(), mapped.getAmount());
        assertEquals(transferDTO.getSource().getId(), mapped.getSource().getId());
        assertEquals(transferDTO.getDestination().getId(), mapped.getDestination().getId());
    }
}
