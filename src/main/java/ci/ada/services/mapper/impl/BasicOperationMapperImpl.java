package ci.ada.services.mapper.impl;

import ci.ada.models.entity.AccountEntity;
import ci.ada.models.entity.BasicOperationEntity;
import ci.ada.services.dto.AccountDTO;
import ci.ada.services.dto.BasicOperationDTO;
import ci.ada.services.mapper.AccountMapper;
import ci.ada.services.mapper.BasicOperationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BasicOperationMapperImpl implements BasicOperationMapper {
    private final AccountMapper accountMapper;

    @Override
    public BasicOperationEntity toEntity(BasicOperationDTO d) {
        AccountEntity accountEntity = accountMapper.toEntity(d.getAccount());

        BasicOperationEntity  entity = new BasicOperationEntity();
        entity.setId(d.getId());
        entity.setOperationType(d.getOperationType());
        entity.setAccount(accountEntity);
        entity.setReference(d.getReference());
        entity.setAmount(d.getAmount());
        return entity;
    }

    @Override
    public BasicOperationDTO toDTO(BasicOperationEntity d) {
        AccountDTO accountDTO = accountMapper.toDTO(d.getAccount());

        BasicOperationDTO  dto = new BasicOperationDTO();
        dto.setId(d.getId());
        dto.setOperationType(d.getOperationType());
        dto.setAccount(accountDTO);
        dto.setReference(d.getReference());
        dto.setAmount(d.getAmount());
        return dto;
    }

    @Override
    public List<BasicOperationDTO> toDTOs(List<BasicOperationEntity> basicOperationEntities) {
        return basicOperationEntities.stream().map(this::toDTO).toList();

    }
}
