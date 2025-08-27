package ci.ada.services.impl;

import ci.ada.Repository.TransferRepository;
import ci.ada.models.entity.TransferEntity;
import ci.ada.services.TransferService;
import ci.ada.services.dto.TransferDTO;
import ci.ada.services.mapper.AccountMapper;
import ci.ada.services.mapper.TransferMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final TransferMapper transferMapper;
    private final TransferRepository transferRepository;
    private final AccountMapper accountMapper;

    @Override
    public TransferDTO save(TransferDTO transferDTO) {
        TransferEntity entity = transferMapper.toEntity(transferDTO);
        return transferMapper.toDTO(transferRepository.save(entity));
    }

    @Override
    public TransferDTO saveWithSlug(TransferDTO transferDTO) {
        return null;
    }

    @Override
    public TransferDTO update(TransferDTO transferDTO) {
        return null;
    }

    @Override
    public TransferDTO partialUpdate(TransferDTO transferDTO) {
        if (Objects.isNull(transferDTO.getId())) {
            throw new IllegalArgumentException("ID does not exist");
        }
        return transferRepository.findById(transferDTO.getId())
                .map(entity -> {
                    if (Objects.nonNull(transferDTO.getDestination())) {
                        entity.setDestination(accountMapper.toEntity(transferDTO.getDestination()));
                    }
                    if (Objects.nonNull(transferDTO.getSource())) {
                        entity.setSource(accountMapper.toEntity(transferDTO.getSource()));
                    }
                    entity = transferRepository.save(entity);
                    return transferMapper.toDTO(entity);
                })
                .orElseThrow(() -> new IllegalArgumentException("transfer not found"));
    }
    @Override
    public List<TransferDTO> getAll() {
        return StreamSupport.stream(transferRepository.findAll().spliterator(), false)
                .map(transferMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TransferDTO getById(Long id) {
        return transferMapper.toDTO(transferRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("transfer not found")));
    }

    @Override
    public TransferDTO getBySlug(String slug) {
        return null;
    }

    @Override
    public List<TransferDTO> getAllById(Long id) {
        return List.of();
    }

    @Override
    public void delete(Long id) {
        transferRepository.deleteById(id);
    }

    @Override
    public void deleteAll(List<TransferDTO> d) {

    }
}
