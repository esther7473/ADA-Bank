package ci.ada.services.impl;

import ci.ada.Repository.BankRepository;
import ci.ada.models.entity.BankEntity;
import ci.ada.services.dto.BankDTO;
import ci.ada.services.mapper.BankMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BankServiceImplTest {

    private static final Long id = 3L;
    private static final String name = "Test Bank";
    private static final String updatedName = "Updated Test Bank";

    private static BankDTO bankDTO;
    private static BankDTO bankDTOResponse;
    private static BankDTO bankDTOPartialUpdate;

    private static BankEntity bankEntity;
    private static BankEntity bankEntityResponse;
    private static BankEntity bankEntityToSave;

    private static final List<BankDTO> bankDTOList = new ArrayList<>();
    private static final List<BankEntity> bankEntityList = new ArrayList<>();

    private BankRepository bankRepository;
    private BankMapper bankMapper;

    private BankServiceImpl bankService;

    @BeforeEach
    void setUp() {
        bankRepository = mock(BankRepository.class);
        bankMapper = mock(BankMapper.class);
        bankService = new BankServiceImpl(bankRepository, bankMapper);

        bankDTO = new BankDTO();
        bankDTO.setName(name);

        bankDTOResponse = new BankDTO();
        bankDTOResponse.setId(id);
        bankDTOResponse.setName(name);

        bankDTOPartialUpdate = new BankDTO();
        bankDTOPartialUpdate.setId(id);
        bankDTOPartialUpdate.setName(updatedName);

        bankEntity = new BankEntity();
        bankEntity.setName(name);

        bankEntityResponse = new BankEntity();
        bankEntityResponse.setId(id);
        bankEntityResponse.setName(name);

        bankEntityToSave = new BankEntity();
        bankEntityToSave.setId(id);
        bankEntityToSave.setName(updatedName);

        bankEntityList.add(bankEntity);
        bankEntityList.add(bankEntityResponse);

        bankDTOList.add(bankDTO);
        bankDTOList.add(bankDTOResponse);
    }

    @Test
    void givenBankDTO_whenSave_thenReturnBankDTO() {
        when(bankMapper.toEntity(bankDTO)).thenReturn(bankEntity);
        when(bankRepository.save(bankEntity)).thenReturn(bankEntityResponse);
        when(bankMapper.toDTO(bankEntityResponse)).thenReturn(bankDTOResponse);

        BankDTO result = bankService.save(bankDTO);

        verify(bankMapper).toEntity(bankDTO);
        verify(bankRepository).save(bankEntity);
        verify(bankMapper).toDTO(bankEntityResponse);

        assertEquals(bankDTOResponse, result);
    }

    @Test
    void givenBankDTO_whenPartialUpdate_thenReturnBankDTO() {
        when(bankRepository.findById(id)).thenReturn(Optional.of(bankEntityResponse));
        when(bankMapper.toDTO(bankEntityResponse)).thenReturn(bankDTOResponse);

        when(bankMapper.toEntity(bankDTOResponse)).thenReturn(bankEntityResponse);
        when(bankRepository.save(bankEntityResponse)).thenReturn(bankEntityToSave);
        when(bankMapper.toDTO(bankEntityToSave)).thenReturn(bankDTOPartialUpdate);

        BankDTO result = bankService.partialUpdate(bankDTOPartialUpdate);

        verify(bankRepository).findById(id);
        verify(bankRepository).save(bankEntityResponse);
        verify(bankMapper, times(2)).toDTO(any());
        verify(bankMapper).toEntity(bankDTOResponse);

        assertEquals(bankDTOPartialUpdate, result);
    }

    @Test
    void givenId_whenDelete_thenReturnVoid() {
        doNothing().when(bankRepository).deleteById(id);

        bankService.delete(id);

        verify(bankRepository).deleteById(id);
    }

    @Test
    void givenId_whenGetById_returnBankDTO() {
        when(bankRepository.findById(id)).thenReturn(Optional.of(bankEntityResponse));
        when(bankMapper.toDTO(bankEntityResponse)).thenReturn(bankDTOResponse);

        BankDTO result = bankService.getById(id);

        verify(bankRepository).findById(id);
        assertEquals(bankDTOResponse, result);
    }

    @Test
    void whenGetAll_returnBankDTOList() {
        when(bankRepository.findAll()).thenReturn(bankEntityList);

        for (int i = 0; i < bankEntityList.size(); i++) {
            when(bankMapper.toDTO(bankEntityList.get(i))).thenReturn(bankDTOList.get(i));
        }

        List<BankDTO> result = bankService.getAll();

        verify(bankRepository).findAll();

        assertEquals(bankDTOList, result);
        assertEquals(bankDTOList.size(), result.size());
    }

    @Test
    void givenBankDTOWithNullId_whenPartialUpdate_thenThrowException() {
        BankDTO bankDTOWithoutId = new BankDTO();
        bankDTOWithoutId.setName("Test without ID");

        assertThrows(IllegalArgumentException.class, () -> {
            bankService.partialUpdate(bankDTOWithoutId);
        });
    }
}
