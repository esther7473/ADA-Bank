package ci.ada.services.mapper.impl;

import ci.ada.models.entity.BankEntity;
import ci.ada.models.entity.CustomerEntity;
import ci.ada.models.entity.UserAccountEntity;
import ci.ada.services.dto.BankDTO;
import ci.ada.services.dto.CustomerDTO;
import ci.ada.services.dto.UserAccountDTO;
import ci.ada.services.mapper.AccountMapper;
import ci.ada.services.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class CustomerMapperImpl implements CustomerMapper {

    private final ModelMapper mapper;
    @Override
    public CustomerEntity toEntity(CustomerDTO d) {

        final BankEntity bank = mapper.map(d.getBank(), BankEntity.class);
        CustomerEntity entity = new CustomerEntity();
        entity.setId(d.getId());
        entity.setUrlPicture(d.getUrlPicture());
        entity.setBankEntity(bank);

        entity.setUserAccountEntity(mapper.map(d.getUserAccountDTO(), UserAccountEntity.class));
        return entity;
    }

    @Override
    public CustomerDTO toDTO(CustomerEntity customerEntity) {
        final BankDTO bankDTO = mapper.map(customerEntity.getBankEntity(), BankDTO.class);
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customerEntity.getId());
        dto.setUrlPicture(customerEntity.getUrlPicture());
        dto.setBank(bankDTO);
        dto.setUserAccountDTO(mapper.map(customerEntity.getUserAccountEntity(), UserAccountDTO.class));

        return dto;
    }

    @Override
    public List<CustomerDTO> toDTOs(List<CustomerEntity> customerEntities) {
        return customerEntities.stream().map(this::toDTO).toList();

    }
}
