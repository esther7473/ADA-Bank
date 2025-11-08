package ci.ada.services;
import ci.ada.services.dto.CustomerDTO;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService extends CrudService<CustomerDTO> {
    List<CustomerDTO> findAllByBankSlug(String bankSlug);
}
