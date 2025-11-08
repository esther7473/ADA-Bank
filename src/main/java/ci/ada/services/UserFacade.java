package ci.ada.services;

import ci.ada.services.dto.*;
import org.springframework.stereotype.Service;

@Service
public interface UserFacade {

    BankDTO registerNewBank(BankDTO bankDTO);
    CustomerDTO registerNewCustomer(CustomerDTO customerDTO);

}
