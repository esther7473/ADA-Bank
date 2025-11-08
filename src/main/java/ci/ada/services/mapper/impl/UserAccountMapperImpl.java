package ci.ada.services.mapper.impl;

import ci.ada.models.entity.UserAccountEntity;
import ci.ada.services.dto.UserAccountDTO;
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
        return modelMapper.map(d, UserAccountEntity.class);
    }

    @Override
    public UserAccountDTO toDTO(UserAccountEntity userAccountEntity) {

        return modelMapper.map(userAccountEntity, UserAccountDTO.class);
    }

    @Override
    public List<UserAccountDTO> toDTOs(List<UserAccountEntity> userAccountEntities) {
        return userAccountEntities.stream().map(u->modelMapper.map(u,UserAccountDTO.class)).toList();
    }


}
