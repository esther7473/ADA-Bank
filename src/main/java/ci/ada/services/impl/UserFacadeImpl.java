package ci.ada.services.impl;

import ci.ada.services.BankService;
import ci.ada.services.CustomerService;
import ci.ada.services.UserAccountService;
import ci.ada.services.UserFacade;
import ci.ada.services.dto.*;
import ci.ada.services.mapper.UserAccountMapper;
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
    private final UserAccountMapper userAccountMapper;

    @Override
    public BankDTO registerNewBank(BankToRegisterDto bankToRegisterDto, UserToregisterDTO user) {
        UserAccountDTO admin = modelMapper.map(user, UserAccountDTO.class);
        admin = modelMapper.map(userAccountService.saveAdminBankWithSlug(admin), UserAccountDTO.class);

        BankDTO bankDTO = modelMapper.map(bankToRegisterDto,BankDTO.class);
        bankDTO.setAdmin(admin);
        bankDTO = bankService.saveWithSlug(bankDTO);
        return bankDTO;
    }


    @Override
    public CustomerDTO registerNewCustomer(CustomerDTO customerDTO) {
        //bank
        //account
        //userAccount
        UserAccountDTO userAccountDTO = userAccountService.saveWithSlug(customerDTO.getUserAccountDTO());
        BankDTO bankDTO = bankService.getBySlug(customerDTO.getBank().getSlug());
        customerDTO.setBank(bankDTO);
        customerDTO.setUserAccountDTO(userAccountDTO);
        CustomerDTO customerDTO1 = customerService.saveWithSlug(customerDTO);
        return customerDTO1;
    }
}
