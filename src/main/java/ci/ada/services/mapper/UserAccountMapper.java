package ci.ada.services.mapper;

import ci.ada.models.entity.UserAccountEntity;
import ci.ada.services.dto.UserAccountDTO;
import ci.ada.services.dto.UserResponseDTO;

public interface UserAccountMapper extends MapperInterface<UserAccountDTO, UserAccountEntity> {
    //UserResponseDTO entityToData(UserAccountEntity userAccountEntity);
    //UserResponseDTO dtoToData(UserAccountDTO userAccountdto);
}
