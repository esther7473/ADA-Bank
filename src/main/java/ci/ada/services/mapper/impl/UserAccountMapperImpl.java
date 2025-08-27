package ci.ada.services.mapper.impl;

import ci.ada.models.entity.UserAccountEntity;
import ci.ada.services.dto.UserAccountDTO;
import ci.ada.services.dto.UserResponseDTO;
import ci.ada.services.mapper.UserAccountMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAccountMapperImpl implements UserAccountMapper {

    private final ModelMapper modelMapper;

    @Override
    public UserAccountEntity toEntity(UserAccountDTO d) {

        /*UserAccountEntity userAccountEntity = new UserAccountEntity();
        userAccountEntity.setId(d.getId());
        userAccountEntity.setEmail(d.getEmail());
        userAccountEntity.setFirstName(d.getFirstName());
        userAccountEntity.setLastName(d.getLastName());
        userAccountEntity.setLogin(d.getLogin());
        userAccountEntity.setRole(userAccountEntity.getRole());
        userAccountEntity.setPhoneNumber(String.valueOf(d.getPhoneNumber()));
        userAccountEntity.setPassword(d.getPassword());

         */
        return modelMapper.map(d, UserAccountEntity.class);
    }

    @Override
    public UserAccountDTO toDTO(UserAccountEntity userAccountEntity) {
       /* UserAccountDTO userAccountDTO = new UserAccountDTO();
        userAccountDTO.setId(userAccountEntity.getId());
        userAccountDTO.setEmail(userAccountEntity.getEmail());
        userAccountDTO.setFirstName(userAccountEntity.getFirstName());
        userAccountDTO.setLastName(userAccountEntity.getLastName());
        userAccountDTO.setLogin(userAccountEntity.getLogin());
        userAccountDTO.setPhoneNumber(userAccountEntity.getPhoneNumber());
        userAccountDTO.setPassword(userAccountEntity.getPassword());
        userAccountDTO.setRole(userAccountEntity.getRole());
        return userAccountDTO;

        */
        return modelMapper.map(userAccountEntity, UserAccountDTO.class);
    }

    @Override
    public List<UserAccountDTO> toDTOs(List<UserAccountEntity> userAccountEntities) {
        return userAccountEntities.stream().map(u->modelMapper.map(u,UserAccountDTO.class)).toList();
    }

       /*

    @Override
    public UserResponseDTO entityToData(UserAccountEntity userAccountEntity) {
        UserResponseDTO dataDTO = new UserResponseDTO();

        dataDTO.setId(userAccountEntity.getId());
        dataDTO.setEmail(userAccountEntity.getEmail());
        dataDTO.setFirstName(userAccountEntity.getFirstName());
        dataDTO.setLastName(userAccountEntity.getLastName());
        dataDTO.setPhoneNumber(userAccountEntity.getPhoneNumber());
        return dataDTO;
    }


    @Override
    public UserResponseDTO dtoToData(UserAccountDTO userAccountdto) {
        UserResponseDTO dataDTO = new UserResponseDTO();
        dataDTO.setId(userAccountdto.getId());

        dataDTO.setEmail(userAccountdto.getEmail());
        dataDTO.setFirstName(userAccountdto.getFirstName());
        dataDTO.setLastName(userAccountdto.getLastName());
        dataDTO.setPhoneNumber(userAccountdto.getPhoneNumber());
        return dataDTO;
    }

     */
}
