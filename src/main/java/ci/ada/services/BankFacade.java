package ci.ada.services;

import ci.ada.services.dto.AccountDataDTO;
import ci.ada.services.dto.BankDataDto;
import ci.ada.services.dto.CustomerDataDTO;
import ci.ada.services.dto.UserDataDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BankFacade {

    List<CustomerDataDTO> getCustomers(String slug);

    List<AccountDataDTO> getAccounts(String slug);

    BankDataDto getBankData(String slug);



}
