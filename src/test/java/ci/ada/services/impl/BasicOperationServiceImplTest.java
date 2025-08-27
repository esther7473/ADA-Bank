package ci.ada.services.impl;

import ci.ada.Repository.BasicOperationRepository;
import ci.ada.models.entity.BasicOperationEntity;
import ci.ada.services.dto.BasicOperationDTO;
import ci.ada.services.mapper.BasicOperationMapper;
import ci.ada.services.mapper.impl.BasicOperationMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BasicOperationServiceImplTest {

    private static final Long id = 3L;
    private static final String reference = "REF123";
    private static final BigDecimal amount = new BigDecimal("100.50");
    private static final String slug = "basic-operation-test";

    private static BasicOperationDTO basicOperationDTO;
    private static BasicOperationDTO basicOperationDTOResponse;
    private static BasicOperationDTO basicOperationDTOPartialUpdate;

    private static BasicOperationEntity basicOperationEntity;
    private static BasicOperationEntity basicOperationEntityResponse;
    private static BasicOperationEntity basicOperationEntityToSave;

    private static final List<BasicOperationDTO> basicOperationDTOList = new ArrayList<>();
    private static final List<BasicOperationEntity> basicOperationEntitiesList = new ArrayList<>();

    private BasicOperationRepository basicOperationRepository;
    private BasicOperationMapperImpl basicOperationMapper;
    private ModelMapper modelMapper;

    private BasicOperationServiceImpl basicOperationService;

    @BeforeEach
    void setUp() {
        basicOperationRepository = mock(BasicOperationRepository.class);
        basicOperationMapper = mock(BasicOperationMapperImpl.class);
        modelMapper = mock(ModelMapper.class);
        basicOperationService = new BasicOperationServiceImpl(basicOperationRepository, basicOperationMapper, modelMapper);

        basicOperationDTO = new BasicOperationDTO();
        basicOperationDTO.setReference(reference);
        basicOperationDTO.setAmount(amount);

        basicOperationDTOResponse = new BasicOperationDTO();
        basicOperationDTOResponse.setId(id);
        basicOperationDTOResponse.setReference(reference);
        basicOperationDTOResponse.setAmount(amount);
        basicOperationDTOResponse.setSlug(slug);

        basicOperationDTOPartialUpdate = new BasicOperationDTO();
        basicOperationDTOPartialUpdate.setId(id);
        basicOperationDTOPartialUpdate.setReference("UPDATED_REF");
        basicOperationDTOPartialUpdate.setAmount(new BigDecimal("200.00"));
        basicOperationDTOPartialUpdate.setSlug("updated-slug");

        basicOperationEntity = new BasicOperationEntity();
        basicOperationEntity.setReference(reference);
        basicOperationEntity.setAmount(amount);

        basicOperationEntityResponse = new BasicOperationEntity();
        basicOperationEntityResponse.setId(id);
        basicOperationEntityResponse.setReference(reference);
        basicOperationEntityResponse.setAmount(amount);
        basicOperationEntityResponse.setSlug(slug);

        basicOperationEntityToSave = new BasicOperationEntity();
        basicOperationEntityToSave.setId(id);
        basicOperationEntityToSave.setReference("UPDATED_REF");
        basicOperationEntityToSave.setAmount(new BigDecimal("200.00"));
        basicOperationEntityToSave.setSlug("updated-slug");

        basicOperationEntitiesList.add(basicOperationEntity);
        basicOperationEntitiesList.add(basicOperationEntityResponse);

        basicOperationDTOList.add(basicOperationDTO);
        basicOperationDTOList.add(basicOperationDTOResponse);
    }

    @Test
    void givenBasicOperationDTO_whenSave_thenReturnBasicOperationDTO() {
        when(basicOperationMapper.toEntity(basicOperationDTO)).thenReturn(basicOperationEntity);
        when(basicOperationRepository.save(basicOperationEntity)).thenReturn(basicOperationEntityResponse);
        when(basicOperationMapper.toDTO(basicOperationEntityResponse)).thenReturn(basicOperationDTOResponse);

        BasicOperationDTO result = basicOperationService.save(basicOperationDTO);

        verify(basicOperationMapper).toEntity(basicOperationDTO);
        verify(basicOperationRepository).save(basicOperationEntity);
        verify(basicOperationMapper).toDTO(basicOperationEntityResponse);

        assertEquals(basicOperationDTOResponse, result);
    }

    @Test
    void givenBasicOperationDTO_whenPartialUpdate_thenReturnBasicOperationDTO() {
        when(basicOperationRepository.findById(id)).thenReturn(Optional.of(basicOperationEntityResponse));

        when(basicOperationRepository.save(basicOperationEntityResponse)).thenReturn(basicOperationEntityToSave);
        when(basicOperationMapper.toDTO(basicOperationEntityToSave)).thenReturn(basicOperationDTOPartialUpdate);

        BasicOperationDTO result = basicOperationService.partialUpdate(basicOperationDTOPartialUpdate);

        verify(basicOperationRepository).findById(id);
        verify(basicOperationRepository).save(basicOperationEntityResponse);
        verify(basicOperationMapper).toDTO(basicOperationEntityToSave);

        assertEquals(basicOperationDTOPartialUpdate, result);
    }

    @Test
    void givenId_whenDelete_thenReturnVoid() {
        doNothing().when(basicOperationRepository).deleteById(id);

        basicOperationService.delete(id);

        verify(basicOperationRepository).deleteById(id);
    }

    @Test
    void givenId_whenGetById_returnBasicOperationDTO() {
        when(basicOperationRepository.findById(id)).thenReturn(Optional.of(basicOperationEntityResponse));
        when(basicOperationMapper.toDTO(basicOperationEntityResponse)).thenReturn(basicOperationDTOResponse);

        BasicOperationDTO result = basicOperationService.getById(id);

        verify(basicOperationRepository).findById(id);
        assertEquals(basicOperationDTOResponse, result);
    }

    @Test
    void whenGetAll_return_BasicOperationDTOList() {
        when(basicOperationRepository.findAll()).thenReturn(basicOperationEntitiesList);

        for (int i = 0; i < basicOperationEntitiesList.size(); i++) {
            when(basicOperationMapper.toDTO(basicOperationEntitiesList.get(i))).thenReturn(basicOperationDTOList.get(i));
        }

        List<BasicOperationDTO> result = basicOperationService.getAll();

        verify(basicOperationRepository).findAll();

        assertEquals(basicOperationDTOList.size(), result.size());
        for (int i = 0; i < basicOperationDTOList.size(); i++) {
            assertEquals(basicOperationDTOList.get(i), result.get(i));
        }
    }

    @Test
    void givenBasicOperationDTOWithNullId_whenPartialUpdate_thenThrowException() {
        BasicOperationDTO basicOperationDTOWithoutId = new BasicOperationDTO();
        basicOperationDTOWithoutId.setReference("Test without ID");

        assertThrows(IllegalArgumentException.class, () -> {
            basicOperationService.partialUpdate(basicOperationDTOWithoutId);
        });
    }

    @Test
    void givenNonExistentId_whenPartialUpdate_thenThrowException() {
        when(basicOperationRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            basicOperationService.partialUpdate(basicOperationDTOPartialUpdate);
        });

        verify(basicOperationRepository).findById(id);
        verify(basicOperationRepository, never()).save(any());
    }
}