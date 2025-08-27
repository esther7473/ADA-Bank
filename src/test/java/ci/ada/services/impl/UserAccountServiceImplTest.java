package ci.ada.services.impl;

import ci.ada.Repository.UserAccountRepository;
import ci.ada.models.entity.UserAccountEntity;
import ci.ada.models.enums.UserRole;
import ci.ada.services.dto.UserAccountDTO;
import ci.ada.services.mapper.UserAccountMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserAccountServiceImplTest {

    private static final Long id = 3L;
    private static final String email = "test@example.com";
    private static final UserRole role = UserRole.ADMIN_BANK;

    private static UserAccountDTO userAccountDTO;
    private static UserAccountDTO userAccountDTOResponse;

    private static UserAccountEntity userAccountEntity;
    private static UserAccountEntity userAccountEntityResponse;

    private static final List<UserAccountDTO> userAccountDTOList = new ArrayList<>();
    private static final List<UserAccountEntity> userAccountEntitiesList = new ArrayList<>();

    private UserAccountRepository userAccountRepository;
    private UserAccountMapper userAccountMapper;
    private ModelMapper modelMapper;

    private UserAccountServiceImpl userAccountService;

    @BeforeEach
    void setUp() {
        userAccountRepository = mock(UserAccountRepository.class);
        userAccountMapper = mock(UserAccountMapper.class);
        modelMapper = mock(ModelMapper.class);
        userAccountService = new UserAccountServiceImpl(userAccountRepository, userAccountMapper, modelMapper);

        userAccountDTO = new UserAccountDTO();
        userAccountDTO.setEmail(email);
        userAccountDTO.setRole(role);

        userAccountEntity = new UserAccountEntity();
        userAccountEntity.setEmail(email);
        userAccountEntity.setRole(role);

        userAccountEntityResponse = new UserAccountEntity();
        userAccountEntityResponse.setId(id);
        userAccountEntityResponse.setEmail(email);
        userAccountEntityResponse.setRole(role);

        userAccountDTOResponse = new UserAccountDTO();
        userAccountDTOResponse.setId(id);
        userAccountDTOResponse.setEmail(email);
        userAccountDTOResponse.setRole(role);

        userAccountEntitiesList.add(userAccountEntity);
        userAccountEntitiesList.add(userAccountEntityResponse);

        userAccountDTOList.add(userAccountDTO);
        userAccountDTOList.add(userAccountDTOResponse);
    }

    @Test
    void givenUserAccountDTO_whenSave_thenReturnUserAccountDTO() {
        when(userAccountMapper.toEntity(userAccountDTO)).thenReturn(userAccountEntity);
        when(userAccountRepository.save(userAccountEntity)).thenReturn(userAccountEntityResponse);
        when(userAccountMapper.toDTO(userAccountEntityResponse)).thenReturn(userAccountDTOResponse);

        UserAccountDTO result = userAccountService.save(userAccountDTO);

        verify(userAccountMapper).toEntity(userAccountDTO);
        verify(userAccountRepository).save(userAccountEntity);
        verify(userAccountMapper).toDTO(userAccountEntityResponse);

        assertEquals(userAccountDTOResponse, result);
    }

    @Test
    void givenUserAccountDTOWithNullRole_whenSave_thenThrowException() {
        UserAccountDTO invalidDTO = new UserAccountDTO();
        invalidDTO.setEmail(email);

        assertThrows(IllegalArgumentException.class, () -> {
            userAccountService.save(invalidDTO);
        });
    }

    @Test
    void givenId_whenDelete_thenReturnVoid() {
        doNothing().when(userAccountRepository).deleteById(id);

        userAccountService.delete(id);

        verify(userAccountRepository).deleteById(id);
    }

    @Test
    void givenId_whenGetById_returnUserAccountDTO() {
        when(userAccountRepository.findById(id)).thenReturn(Optional.of(userAccountEntityResponse));
        when(userAccountMapper.toDTO(userAccountEntityResponse)).thenReturn(userAccountDTOResponse);

        UserAccountDTO result = userAccountService.getById(id);

        verify(userAccountRepository).findById(id);
        assertEquals(userAccountDTOResponse, result);
    }

    @Test
    void whenGetAll_return_UserAccountDTOList() {
        when(userAccountRepository.findAll()).thenReturn(userAccountEntitiesList);

        for (int i = 0; i < userAccountEntitiesList.size(); i++) {
            when(userAccountMapper.toDTO(userAccountEntitiesList.get(i))).thenReturn(userAccountDTOList.get(i));
        }

        List<UserAccountDTO> result = userAccountService.getAll();

        verify(userAccountRepository).findAll();
        assertEquals(userAccountDTOList.size(), result.size());
    }

    @Test
    void givenNonExistentId_whenGetById_thenThrowException() {
        when(userAccountRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            userAccountService.getById(id);
        });

        verify(userAccountRepository).findById(id);
    }
}