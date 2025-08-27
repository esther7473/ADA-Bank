package ci.ada.services.mapper.impl;

import ci.ada.models.entity.UserAccountEntity;
import ci.ada.models.enums.UserRole;
import ci.ada.services.dto.UserAccountDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

class UserAccountMapperImplTest {

    private static final Long ID = 2L;

    private static final String LOGIN = "loff";
    private static final String PASSWORD = "843993";

    private static final String FIRSTNAME = "kevin";

    private static final String LASTNAME = "Auti";

    private static final String EMAIL = "eeee";

    private static final String PHONENUMBER = "222222";

    private static final UserRole ROLE = UserRole.ADMIN;

    private static final List<UserAccountDTO> userAccountDTOList = new ArrayList<>();
    private static final List<UserAccountEntity> userAccountEntityList= new ArrayList<>();

    private UserAccountDTO userAccountDTO;
    private UserAccountEntity userAccountEntity;
    private static  ModelMapper modelMapper;
    private static UserAccountMapperImpl userAccountMapper;

    @BeforeEach
    void setUp() {
        modelMapper = mock(ModelMapper.class);
        userAccountMapper = new UserAccountMapperImpl(modelMapper);

        userAccountDTO = new UserAccountDTO();
        userAccountDTO.setId(ID);
        userAccountDTO.setLogin(LOGIN);
        userAccountDTO.setPassword(PASSWORD);
        userAccountDTO.setFirstName(FIRSTNAME);
        userAccountDTO.setLastName(LASTNAME);
        userAccountDTO.setEmail(EMAIL);
        userAccountDTO.setPhoneNumber(PHONENUMBER);
        userAccountDTO.setRole(ROLE);


        userAccountEntity = new UserAccountEntity();
        userAccountEntity.setLogin(LOGIN);
        userAccountEntity.setPassword(PASSWORD);
        userAccountEntity.setFirstName(FIRSTNAME);
        userAccountEntity.setLastName(LASTNAME);
        userAccountEntity.setEmail(EMAIL);
        userAccountEntity.setPhoneNumber(PHONENUMBER);
        userAccountEntity.setRole(ROLE);
        userAccountEntity.setId(ID);

        userAccountDTOList.add(userAccountDTO);
        userAccountEntityList.add(userAccountEntity);

    }

    @Test
    void givernUseraccountDTO_whenToEntity_returnUserAccountEntity() {
        when(modelMapper.map(userAccountDTO, UserAccountEntity.class)).thenReturn(userAccountEntity);
        UserAccountEntity result = userAccountMapper.toEntity(userAccountDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(userAccountEntity.getId(), result.getId());
        Assertions.assertEquals(userAccountEntity.getLogin(), result.getLogin());
        Assertions.assertEquals(userAccountEntity.getPassword(), result.getPassword());
        Assertions.assertEquals(userAccountEntity.getFirstName(), result.getFirstName());
        Assertions.assertEquals(userAccountEntity.getLastName(), result.getLastName());
        Assertions.assertEquals(userAccountEntity.getEmail(), result.getEmail());
        Assertions.assertEquals(userAccountEntity.getPhoneNumber(), result.getPhoneNumber());
        Assertions.assertEquals(userAccountEntity.getRole(), result.getRole());


    }

    @Test
    void givenUseraccountEntity_whenToDTO_thenReturnUseraccountDTO(){
        when(modelMapper.map(userAccountEntity, UserAccountDTO.class)).thenReturn(userAccountDTO);

        UserAccountDTO result = userAccountMapper.toDTO(userAccountEntity);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(userAccountDTO.getId(), result.getId());
        Assertions.assertEquals(userAccountDTO.getLogin(), result.getLogin());
        Assertions.assertEquals(userAccountDTO.getPassword(), result.getPassword());
        Assertions.assertEquals(userAccountDTO.getFirstName(), result.getFirstName());
        Assertions.assertEquals(userAccountDTO.getLastName(), result.getLastName());
        Assertions.assertEquals(userAccountDTO.getEmail(), result.getEmail());
        Assertions.assertEquals(userAccountDTO.getPhoneNumber(), result.getPhoneNumber());
        Assertions.assertEquals(userAccountDTO.getRole(), result.getRole());
    }

    @Test
    void givenUseraccountEntityList_whenToDTOs_thenReturnUserAccountDTOList() {

        when(modelMapper.map(any(UserAccountEntity.class), eq(UserAccountDTO.class))).thenReturn(userAccountDTO);

         List<UserAccountDTO> result = userAccountMapper.toDTOs(userAccountEntityList);
         Assertions.assertNotNull(result);
         for (UserAccountDTO user : result) {
             Assertions.assertEquals(userAccountDTO.getId(), user.getId());
             Assertions.assertEquals(userAccountDTO.getLogin(), user.getLogin());
             Assertions.assertEquals(userAccountDTO.getPassword(), user.getPassword());
             Assertions.assertEquals(userAccountDTO.getFirstName(), user.getFirstName());
             Assertions.assertEquals(userAccountDTO.getLastName(), user.getLastName());
             Assertions.assertEquals(userAccountDTO.getEmail(), user.getEmail());
             Assertions.assertEquals(userAccountDTO.getPhoneNumber(), user.getPhoneNumber());
             Assertions.assertEquals(userAccountDTO.getRole(), user.getRole());
         }


    }
}