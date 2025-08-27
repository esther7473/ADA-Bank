package ci.ada.services.mapper.impl;

import ci.ada.models.entity.AccountEntity;
import ci.ada.models.entity.CustomerEntity;
import ci.ada.services.dto.AccountDTO;
import ci.ada.services.dto.CustomerDTO;
import ci.ada.services.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountMapperImpl implements AccountMapper {
    private final ModelMapper modelMapper;


    @Override
    public List<AccountDTO> toDTOs(List<AccountEntity> accountEntities) {
        return accountEntities.stream().map(this::toDTO).toList();
    }

    @Override
    public AccountEntity toEntity(AccountDTO dto) {
        final CustomerEntity finalCustomerEntity = modelMapper.map(dto.getCustomer(), CustomerEntity.class);

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(dto.getId());
        accountEntity.setBalance(dto.getBalance());
        accountEntity.setAccountType(dto.getAccountType());
        accountEntity.setAccountStatus(dto.getAccountStatus());
        accountEntity.setCustomer(finalCustomerEntity);
        accountEntity.setNumberAccount(dto.getNumberAccount());

        return accountEntity;
    }

    @Override
    public AccountDTO toDTO(AccountEntity entity) {
        final CustomerDTO finalCustomerDTO = modelMapper.map(entity.getCustomer(), CustomerDTO.class);

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(entity.getId());
        accountDTO.setBalance(entity.getBalance());
        accountDTO.setAccountType(entity.getAccountType());
        accountDTO.setAccountStatus(entity.getAccountStatus());
        accountDTO.setCustomer(finalCustomerDTO);
        accountDTO.setNumberAccount(entity.getNumberAccount());

        return accountDTO;
    }



}


