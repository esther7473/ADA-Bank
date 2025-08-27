package ci.ada.services;

import ci.ada.services.dto.UserAccountDTO;
import ci.ada.services.dto.UserResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserAccountService extends CrudService<UserAccountDTO> {

    UserResponseDTO saveAdminBank(UserAccountDTO userAccountDTO);
    UserResponseDTO saveAdminBankWithSlug(UserAccountDTO userAccountDTO);
}
