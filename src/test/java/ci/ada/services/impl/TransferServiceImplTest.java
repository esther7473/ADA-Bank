package ci.ada.services.impl;

import ci.ada.Repository.TransferRepository;
import ci.ada.models.entity.TransferEntity;
import ci.ada.services.dto.TransferDTO;
import ci.ada.services.mapper.AccountMapper;
import ci.ada.services.mapper.TransferMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransferServiceImplTest {

    private static final Long id = 3L;
    private static final String reference = "TRANSFER_REF";

    private static TransferDTO transferDTO;
    private static TransferDTO transferDTOResponse;

    private static TransferEntity transferEntity;
    private static TransferEntity transferEntityResponse;

    private static final List<TransferDTO> transferDTOList = new ArrayList<>();
    private static final List<TransferEntity> transferEntitiesList = new ArrayList<>();

    private TransferRepository transferRepository;
    private TransferMapper transferMapper;
    private AccountMapper accountMapper;

    private TransferServiceImpl transferService;

    @BeforeEach
    void setUp() {
        transferRepository = mock(TransferRepository.class);
        transferMapper = mock(TransferMapper.class);
        accountMapper = mock(AccountMapper.class);
        transferService = new TransferServiceImpl(transferMapper, transferRepository, accountMapper);

        transferDTO = new TransferDTO();
        transferDTO.setReference(reference);

        transferEntity = new TransferEntity();
        transferEntity.setReference(reference);

        transferEntityResponse = new TransferEntity();
        transferEntityResponse.setId(id);
        transferEntityResponse.setReference(reference);

        transferDTOResponse = new TransferDTO();
        transferDTOResponse.setId(id);
        transferDTOResponse.setReference(reference);

        transferEntitiesList.add(transferEntity);
        transferEntitiesList.add(transferEntityResponse);

        transferDTOList.add(transferDTO);
        transferDTOList.add(transferDTOResponse);
    }

    @Test
    void givenTransferDTO_whenSave_thenReturnTransferDTO() {
        when(transferMapper.toEntity(transferDTO)).thenReturn(transferEntity);
        when(transferRepository.save(transferEntity)).thenReturn(transferEntityResponse);
        when(transferMapper.toDTO(transferEntityResponse)).thenReturn(transferDTOResponse);

        TransferDTO result = transferService.save(transferDTO);

        verify(transferMapper).toEntity(transferDTO);
        verify(transferRepository).save(transferEntity);
        verify(transferMapper).toDTO(transferEntityResponse);

        assertEquals(transferDTOResponse, result);
    }

    @Test
    void givenId_whenDelete_thenReturnVoid() {
        doNothing().when(transferRepository).deleteById(id);

        transferService.delete(id);

        verify(transferRepository).deleteById(id);
    }

    @Test
    void givenId_whenGetById_returnTransferDTO() {
        when(transferRepository.findById(id)).thenReturn(Optional.of(transferEntityResponse));
        when(transferMapper.toDTO(transferEntityResponse)).thenReturn(transferDTOResponse);

        TransferDTO result = transferService.getById(id);

        verify(transferRepository).findById(id);
        assertEquals(transferDTOResponse, result);
    }

    @Test
    void whenGetAll_return_TransferDTOList() {
        when(transferRepository.findAll()).thenReturn(transferEntitiesList);

        for (int i = 0; i < transferEntitiesList.size(); i++) {
            when(transferMapper.toDTO(transferEntitiesList.get(i))).thenReturn(transferDTOList.get(i));
        }

        List<TransferDTO> result = transferService.getAll();

        verify(transferRepository).findAll();
        assertEquals(transferDTOList.size(), result.size());
    }

    @Test
    void givenNonExistentId_whenGetById_thenThrowException() {
        when(transferRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            transferService.getById(id);
        });

        verify(transferRepository).findById(id);
    }
}
