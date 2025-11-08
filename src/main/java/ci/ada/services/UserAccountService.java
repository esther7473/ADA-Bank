package ci.ada.services;

import ci.ada.services.dto.UserAccountDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserAccountService extends CrudService<UserAccountDTO> {

    UserAccountDTO saveAdminBank(UserAccountDTO userAccountDTO);
    UserAccountDTO saveAdminBankWithSlug(UserAccountDTO userAccountDTO);
}
