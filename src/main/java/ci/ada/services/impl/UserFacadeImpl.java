package ci.ada.services.impl;

import ci.ada.services.BankService;
import ci.ada.services.CustomerService;
import ci.ada.services.UserAccountService;
import ci.ada.services.UserFacade;
import ci.ada.services.dto.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {

    private final UserAccountService userAccountService;
    private final BankService bankService;
    private final CustomerService customerService;
    private final ModelMapper modelMapper;

    @Override
    public BankDTO registerNewBank(BankDTO bankDTO) {
        UserAccountDTO admin = modelMapper.map(bankDTO.getAdmin(), UserAccountDTO.class);
        admin = userAccountService.saveAdminBankWithSlug(admin);
        bankDTO.setAdmin(admin);

        return bankService.saveWithSlug(bankDTO);
    }


    @Override
    public CustomerDTO registerNewCustomer(CustomerDTO customerDTO) {

        UserAccountDTO userAccountDTO = userAccountService.saveWithSlug(customerDTO.getUserAccountDTO());
        BankDTO bankDTO = bankService.getBySlug(customerDTO.getBank().getSlug());
        customerDTO.setBank(bankDTO);
        customerDTO.setUserAccountDTO(userAccountDTO);
        return customerService.saveWithSlug(customerDTO);
    }
}
