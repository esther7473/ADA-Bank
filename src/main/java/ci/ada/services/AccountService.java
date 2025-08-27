package ci.ada.services;

import ci.ada.services.dto.AccountDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService extends CrudService<AccountDTO> {

    List<AccountDTO> getAllByCustomerSlug(String customerSlug);
}