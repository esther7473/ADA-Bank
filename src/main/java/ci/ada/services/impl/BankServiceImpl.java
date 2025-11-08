package ci.ada.services.impl;

import ci.ada.Repository.BankRepository;
import ci.ada.models.entity.BankEntity;
import ci.ada.services.BankService;
import ci.ada.services.dto.BankDTO;
import ci.ada.services.mapper.BankMapper;
import ci.ada.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;
    private final BankMapper bankMapper;

    @Override
    public BankDTO save(BankDTO bankDTO) {
        BankEntity bankEntity = bankMapper.toEntity(bankDTO);
        return bankMapper.toDTO(bankRepository.save(bankEntity));
    }

    @Override
    public BankDTO saveWithSlug(BankDTO bankDTO) {
        final String finalSlug = SlugifyUtils.generate("bankAdmin");
        bankDTO.setSlug(finalSlug);
        return save(bankDTO);
    }

    @Override
    public BankDTO update(BankDTO bankDTO) {
        if (Objects.nonNull(bankDTO.getId())) {
            throw new IllegalArgumentException("ID does not exist");
        }
        return save(bankDTO);
    }

    @Override
    public BankDTO partialUpdate(BankDTO bankDTO) {
        if (Objects.isNull(bankDTO.getId())) {
            throw new IllegalArgumentException("id is null");
        }
        BankDTO bankRecovery = getById(bankDTO.getId());

        if (Objects.nonNull(bankRecovery.getName())) {
            bankRecovery.setName(bankDTO.getName());
        }
        if (Objects.nonNull(bankDTO.getCustomers())) {
            bankRecovery.setCustomers(bankDTO.getCustomers());
        }
        if (Objects.nonNull(bankDTO.getAdmin())) {
            bankRecovery.setAdmin(bankDTO.getAdmin());
        }
        return save(bankRecovery);

    }

    @Override
    public List<BankDTO> getAll() {
        return bankRepository.findAll().stream().map(bankMapper::toDTO).toList();
    }

    @Override
    public BankDTO getById(Long id) {
        return bankMapper.toDTO(bankRepository.findById(id).orElseThrow(()-> new RuntimeException("bank not found")));
    }

    @Override
    public BankDTO getBySlug(String slug) {
        return bankRepository.findBySlug(slug)
                .map(bankMapper::toDTO)
                .orElseThrow(()-> new RuntimeException("bank not found"));
    }

    @Override
    public List<BankDTO> getAllById(Long id) {
        return List.of();
    }

    @Override
    public void delete(Long id) {
        bankRepository.deleteById(id);
    }

    @Override
    public void deleteAll(List<BankDTO> d) {
        bankRepository.deleteAll();
    }
}
