package ci.ada.services;

import ci.ada.services.dto.*;
import org.springframework.stereotype.Service;

@Service
public interface UserFacade {

    BankDTO registerNewBank(BankToRegisterDto bankToRegisterDto, UserToregisterDTO admin);
    CustomerDTO registerNewCustomer(CustomerDTO customerDTO);

}
