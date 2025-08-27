package ci.ada.services.impl;

import ci.ada.services.AccountService;
import ci.ada.services.BankFacade;
import ci.ada.services.BankService;
import ci.ada.services.CustomerService;
import ci.ada.services.dto.AccountDataDTO;
import ci.ada.services.dto.BankDataDto;
import ci.ada.services.dto.CustomerDataDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankFacadeImpl implements BankFacade {

    private final BankService bankService;
    private final AccountService accountService;
    private final CustomerService customerService;
    private final ModelMapper modelMapper;


    @Override
    public List<CustomerDataDTO> getCustomers(String slug) {

        return customerService.findAllByBankSlug(slug)
                .stream()
                .map(customerDTO -> modelMapper
                                .map(customerDTO,CustomerDataDTO.class)).toList();
    }

    @Override
    public List<AccountDataDTO> getAccounts(String slug) {
        return accountService.getAllByCustomerSlug(slug)
                .stream()
                .map(a -> modelMapper.map(a,AccountDataDTO.class)).toList();
    }

    @Override
    public BankDataDto getBankData(String slug) {
        BankDataDto bankDataDto = modelMapper.map(bankService.getBySlug(slug), BankDataDto.class);
        bankDataDto.setCustomers(getCustomers(slug)
                .stream()
                .peek(c->{c.setAccounts(getAccounts(c.getSlug()));
                        modelMapper.map(c,AccountDataDTO.class);

                }).toList()

        );

        return bankDataDto;
    }
}
