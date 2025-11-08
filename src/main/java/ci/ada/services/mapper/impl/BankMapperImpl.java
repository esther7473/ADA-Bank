package ci.ada.services.mapper.impl;

import ci.ada.models.entity.BankEntity;
import ci.ada.models.entity.UserAccountEntity;
import ci.ada.services.dto.BankDTO;
import ci.ada.services.dto.UserAccountDTO;
import ci.ada.services.mapper.BankMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BankMapperImpl implements BankMapper {
    private final ModelMapper modelMapper;

    @Override
    public BankEntity toEntity(BankDTO d) {

        final UserAccountEntity userAccountEntity = modelMapper.map(d.getAdmin(), UserAccountEntity.class);
        BankEntity entity = new BankEntity();
        entity.setId(d.getId());
        entity.setName(d.getName());
        entity.setAdmin(userAccountEntity);
        entity.setCustomers(d.getCustomers());
        return entity;
    }

    @Override
    public BankDTO toDTO(BankEntity bankEntity) {
        final UserAccountDTO userAccountDTO = modelMapper.map(bankEntity.getAdmin(), UserAccountDTO.class);
        BankDTO dto = new BankDTO();
        dto.setId(bankEntity.getId());
        dto.setName(bankEntity.getName());
        dto.setAdmin(userAccountDTO);
        dto.setCustomers(bankEntity.getCustomers());
        return dto;
    }

    @Override
    public List<BankDTO> toDTOs(List<BankEntity> bankEntities) {
        return bankEntities.stream().map(this::toDTO).toList();

    }
}
