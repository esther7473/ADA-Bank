package ci.ada.services.impl;

import ci.ada.Repository.BasicOperationRepository;
import ci.ada.models.entity.BasicOperationEntity;
import ci.ada.services.BasicOperationService;
import ci.ada.services.dto.BasicOperationDTO;
import ci.ada.services.mapper.BasicOperationMapper;
import ci.ada.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class BasicOperationServiceImpl implements BasicOperationService {

    private final BasicOperationRepository basicOperationRepository;
    private final BasicOperationMapper basicOperationMapper;
    private final ModelMapper modelMapper;

    @Override
    public BasicOperationDTO save(BasicOperationDTO basicOperationDTO) {
        BasicOperationEntity entity = basicOperationRepository.save(basicOperationMapper.toEntity(basicOperationDTO));
        return basicOperationMapper.toDTO(entity);
    }

    @Override
    public BasicOperationDTO saveWithSlug(BasicOperationDTO basicOperationDTO) {
        final String finalSlug = SlugifyUtils.generate("basicOperation");
        basicOperationDTO.setSlug(finalSlug);
        return save(basicOperationDTO);

    }

    @Override
    public BasicOperationDTO update(BasicOperationDTO basicOperationDTO) {
        if (Objects.nonNull(basicOperationDTO.getId())) {
            throw new IllegalArgumentException("ID does not exist");
        }
        return  save(basicOperationDTO);
    }

    @Override
    public BasicOperationDTO partialUpdate(BasicOperationDTO basicOperationDTO) {
        if (Objects.isNull(basicOperationDTO.getId())) {
            throw new IllegalArgumentException("ID does not exist");
        }
        return basicOperationRepository.findById(basicOperationDTO.getId())
                .map(entity ->{
                    if(Objects.nonNull(basicOperationDTO.getSlug())) {
                        entity.setSlug(basicOperationDTO.getSlug());
                    }
                    if (Objects.nonNull(basicOperationDTO.getOperationType())){
                        entity.setOperationType(basicOperationDTO.getOperationType());
                    }
                    if(Objects.nonNull(basicOperationDTO.getAccount())){
                        entity.setAccount(modelMapper.map(basicOperationDTO,BasicOperationEntity.class).getAccount());
                    }
                    if (Objects.nonNull(basicOperationDTO.getAmount())){
                        entity.setAmount(basicOperationDTO.getAmount());
                    }
                    if (Objects.nonNull(basicOperationDTO.getReference())){
                        entity.setReference(basicOperationDTO.getReference());
                    }
                    entity = basicOperationRepository.save(entity);
                    return basicOperationMapper.toDTO(entity);

                })
                .orElseThrow(() -> new IllegalArgumentException("User account not found"));

    }

    @Override
    public List<BasicOperationDTO> getAll() {
        return  basicOperationRepository.findAll().stream().map(basicOperationMapper::toDTO).toList();
    }

    @Override
    public BasicOperationDTO getById(Long id) {
        return basicOperationRepository.findById(id).map(basicOperationMapper::toDTO).orElseThrow(RuntimeException::new);
    }

    @Override
    public BasicOperationDTO getBySlug(String slug) {
        return basicOperationRepository.findBySlug(slug).map(basicOperationMapper::toDTO).orElseThrow(IllegalAccessError::new);
    }

    @Override
    public List<BasicOperationDTO> getAllById(Long id) {
        return List.of();
    }

    @Override
    public void delete(Long id) {
        basicOperationRepository.deleteById(id);
    }

    @Override
    public void deleteAll(List<BasicOperationDTO> d) {
        basicOperationRepository.deleteAll();
    }
}
