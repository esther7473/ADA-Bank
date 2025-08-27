package ci.ada.services.mapper.impl;

import ci.ada.models.entity.AccountEntity;
import ci.ada.models.entity.TransferEntity;
import ci.ada.services.dto.AccountDTO;
import ci.ada.services.dto.TransferDTO;
import ci.ada.services.mapper.AccountMapper;
import ci.ada.services.mapper.TransferMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransferMapperImpl implements TransferMapper {

    private final AccountMapper accountMapper;
    @Override
    public TransferEntity toEntity(TransferDTO d) {
        final AccountEntity sourceAccountENtity = accountMapper.toEntity(d.getSource());
        final AccountEntity destinationAccountENtity = accountMapper.toEntity(d.getDestination());
        TransferEntity entity = new TransferEntity();
        entity.setId(d.getId());
        entity.setDestination(destinationAccountENtity);
        entity.setSource(sourceAccountENtity);
        entity.setReference(d.getReference());
        entity.setAmount(d.getAmount());
        return entity;
    }

    @Override
    public TransferDTO toDTO(TransferEntity transferEntity) {
        final AccountDTO sourcedto = accountMapper.toDTO(transferEntity.getSource());
        final AccountDTO destinatioondto = accountMapper.toDTO(transferEntity.getDestination());

        TransferDTO dto = new TransferDTO();
        dto.setId(transferEntity.getId());
        dto.setDestination(destinatioondto);
        dto.setSource(sourcedto);
        dto.setReference(transferEntity.getReference());
        dto.setAmount(transferEntity.getAmount());
        return dto;
    }

    @Override
    public List<TransferDTO> toDTOs(List<TransferEntity> transferEntities) {
        return transferEntities.stream().map(this::toDTO).toList();

    }
}
