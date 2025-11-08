package ci.ada.services.impl;

import ci.ada.Repository.BasicOperationRepository;
import ci.ada.models.entity.BasicOperationEntity;
import ci.ada.services.dto.BasicOperationDTO;
import ci.ada.services.mapper.BasicOperationMapper;
import ci.ada.utils.SlugifyUtils;
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

    private static final Long basicOperationId= 3L;
    private static final String reference = "REF123";
    private static final BigDecimal amount = new BigDecimal("100.50");
    private static final String slug = "basic-operation-test";

    private BasicOperationDTO basicOperationDTO;
    private BasicOperationDTO basicOperationDTOResponse;
    private BasicOperationDTO basicOperationDTOPartialUpdate;

    private BasicOperationEntity basicOperationEntity;
    private BasicOperationEntity basicOperationEntityResponse;
    private BasicOperationEntity basicOperationEntityToSave;

    private final List<BasicOperationDTO> basicOperationDTOList = new ArrayList<>();
    private final List<BasicOperationEntity> basicOperationEntitiesList = new ArrayList<>();

    private BasicOperationRepository basicOperationRepository;
    private BasicOperationMapper basicOperationMapper;
    private ModelMapper modelMapper;

    private BasicOperationServiceImpl basicOperationService;

    @BeforeEach
    void setUp() {
        basicOperationRepository = mock(BasicOperationRepository.class);
        basicOperationMapper = mock(BasicOperationMapper.class);
        modelMapper = mock(ModelMapper.class);
        basicOperationService = new BasicOperationServiceImpl(basicOperationRepository, basicOperationMapper, modelMapper);

        basicOperationDTO = new BasicOperationDTO();
        basicOperationDTO.setReference(reference);
        basicOperationDTO.setAmount(amount);

        basicOperationDTOResponse = new BasicOperationDTO();
        basicOperationDTOResponse.setId(basicOperationId);
        basicOperationDTOResponse.setReference(reference);
        basicOperationDTOResponse.setAmount(amount);
        basicOperationDTOResponse.setSlug(slug);

        basicOperationDTOPartialUpdate = new BasicOperationDTO();
        basicOperationDTOPartialUpdate.setId(basicOperationId);
        basicOperationDTOPartialUpdate.setReference("UPDATED_REF");
        basicOperationDTOPartialUpdate.setAmount(new BigDecimal("200.00"));
        basicOperationDTOPartialUpdate.setSlug("updated-slug");

        basicOperationEntity = new BasicOperationEntity();
        basicOperationEntity.setReference(reference);
        basicOperationEntity.setAmount(amount);

        basicOperationEntityResponse = new BasicOperationEntity();
        basicOperationEntityResponse.setId(basicOperationId);
        basicOperationEntityResponse.setReference(reference);
        basicOperationEntityResponse.setAmount(amount);
        basicOperationEntityResponse.setSlug(slug);

        basicOperationEntityToSave = new BasicOperationEntity();
        basicOperationEntityToSave.setId(basicOperationId);
        basicOperationEntityToSave.setReference("UPDATED_REF");
        basicOperationEntityToSave.setAmount(new BigDecimal("200.00"));
        basicOperationEntityToSave.setSlug("updated-slug");

        basicOperationEntitiesList.add(basicOperationEntity);
        basicOperationEntitiesList.add(basicOperationEntityResponse);

        basicOperationDTOList.add(basicOperationDTO);
        basicOperationDTOList.add(basicOperationDTOResponse);
    }

    @Test
    void givenBasicOperationDTO_whenSave_thenReturnDTO() {
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
    void givenBasicOperationDTO_whenPartialUpdate_thenReturnDTO() {
        when(basicOperationRepository.findById(basicOperationId)).thenReturn(Optional.of(basicOperationEntityResponse));
        when(basicOperationRepository.save(basicOperationEntityResponse)).thenReturn(basicOperationEntityToSave);
        when(basicOperationMapper.toDTO(basicOperationEntityToSave)).thenReturn(basicOperationDTOPartialUpdate);

        BasicOperationDTO result = basicOperationService.partialUpdate(basicOperationDTOPartialUpdate);

        verify(basicOperationRepository).findById(basicOperationId);
        verify(basicOperationRepository).save(basicOperationEntityResponse);
        verify(basicOperationMapper).toDTO(basicOperationEntityToSave);

        assertEquals(basicOperationDTOPartialUpdate, result);
    }

    @Test
    void givenId_whenDelete_thenRepositoryDeleteCalled() {
        doNothing().when(basicOperationRepository).deleteById(basicOperationId);
        basicOperationService.delete(basicOperationId);
        verify(basicOperationRepository).deleteById(basicOperationId);
    }

    @Test
    void givenId_whenGetById_thenReturnDTO() {
        when(basicOperationRepository.findById(basicOperationId)).thenReturn(Optional.of(basicOperationEntityResponse));
        when(basicOperationMapper.toDTO(basicOperationEntityResponse)).thenReturn(basicOperationDTOResponse);

        BasicOperationDTO result = basicOperationService.getById(basicOperationId);

        verify(basicOperationRepository).findById(basicOperationId);
        assertEquals(basicOperationDTOResponse, result);
    }

    @Test
    void whenGetAll_thenReturnDTOList() {
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
    void givenDTOWithNullId_whenPartialUpdate_thenThrowException() {
        BasicOperationDTO dtoWithoutId = new BasicOperationDTO();
        assertThrows(IllegalArgumentException.class, () -> basicOperationService.partialUpdate(dtoWithoutId));
    }

    @Test
    void givenNonExistentId_whenPartialUpdate_thenThrowException() {
        when(basicOperationRepository.findById(basicOperationId)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> basicOperationService.partialUpdate(basicOperationDTOPartialUpdate));
        verify(basicOperationRepository).findById(basicOperationId);
        verify(basicOperationRepository, never()).save(any());
    }

    @Test
    void givenBasicOperationDTO_whenSaveWithSlug_thenSlugIsSetAndSaveCalled() {
        BasicOperationDTO dto = new BasicOperationDTO();

        // Mock du slug généré
        String generatedSlug = "basicOperation-slug";
        mockStatic(SlugifyUtils.class);
        when(SlugifyUtils.generate("basicOperation")).thenReturn(generatedSlug);

        BasicOperationDTO savedDto = new BasicOperationDTO();
        savedDto.setSlug(generatedSlug);

        // Mock du save pour retourner le DTO attendu
        when(basicOperationService.save(dto)).thenReturn(savedDto);

        BasicOperationDTO result = basicOperationService.saveWithSlug(dto);

        // Vérifications
        assertNotNull(result);
        assertEquals(generatedSlug, result.getSlug());
        verify(basicOperationService).save(dto);
    }

    @Test
    void givenBasicOperationDTOWithId_whenUpdate_thenThrowException() {
        BasicOperationDTO dto = new BasicOperationDTO();
        dto.setId(1L);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            basicOperationService.update(dto);
        });

        assertEquals("ID does not exist", exception.getMessage());
    }

    @Test
    void givenBasicOperationDTOWithoutId_whenUpdate_thenSaveIsCalled() {
        BasicOperationDTO dto = new BasicOperationDTO();
        dto.setId(null);

        BasicOperationDTO savedDto = new BasicOperationDTO();
        when(basicOperationService.save(dto)).thenReturn(savedDto);

        BasicOperationDTO result = basicOperationService.update(dto);

        assertNotNull(result);
        verify(basicOperationService).save(dto);
    }


}
