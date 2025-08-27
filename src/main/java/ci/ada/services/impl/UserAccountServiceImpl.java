package ci.ada.services.impl;

import ci.ada.Repository.UserAccountRepository;
import ci.ada.models.entity.UserAccountEntity;
import ci.ada.models.enums.UserRole;
import ci.ada.services.UserAccountService;
import ci.ada.services.dto.UserAccountDTO;
import ci.ada.services.dto.UserResponseDTO;
import ci.ada.services.mapper.UserAccountMapper;
import ci.ada.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final UserAccountMapper userAccountMapper;
    private final ModelMapper modelMapper;


    @Override
    public UserResponseDTO saveAdminBank(UserAccountDTO userAccountDTO) {
        userAccountDTO.setRole(UserRole.ADMIN_BANK);
        userAccountDTO = save(userAccountDTO);
        //return userAccountMapper.dtoToData(userAccountDTO);
        return modelMapper.map(userAccountDTO,UserResponseDTO.class);

    }

    @Override
    public UserResponseDTO saveAdminBankWithSlug(UserAccountDTO userAccountDTO) {
        final String finalSlug = SlugifyUtils.generate("bankAdmin");
        userAccountDTO.setSlug(finalSlug);
        return saveAdminBank(userAccountDTO);
    }

    @Override
    public UserAccountDTO getBySlug(String slug) {
        return userAccountRepository.findBySlug(slug)
                .map(userAccountMapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("User account not found"));
    }

    @Override
    public List<UserAccountDTO> getAllById(Long id) {
        return List.of();
    }

    @Override
    public UserAccountDTO save(UserAccountDTO userAccountDTO) {
        System.out.println(" role : "+userAccountDTO.getRole());
        if (userAccountDTO.getRole() == null) {
            throw new IllegalArgumentException("Role must not be null");
        }

        UserAccountEntity userAccountEntity = userAccountMapper.toEntity(userAccountDTO);
        return userAccountMapper.toDTO(userAccountRepository.save(userAccountEntity));
    }

    @Override
    public UserAccountDTO saveWithSlug(UserAccountDTO userAccountDTO) {
        final String finalSlug = SlugifyUtils.generate("user");
        userAccountDTO.setSlug(finalSlug);
        return save(userAccountDTO);

    }

    @Override
    public UserAccountDTO update(UserAccountDTO userAccountDTO) {
        return save(userAccountDTO);
    }

    @Override
    public UserAccountDTO partialUpdate(UserAccountDTO userAccountDTO) {
        if (Objects.isNull(userAccountDTO.getId())) {
            throw new IllegalArgumentException("ID does not exist");
        }
        return userAccountRepository.findById(userAccountDTO.getId())
                .map(entity -> {
                    if (Objects.nonNull(userAccountDTO.getRole())) {
                        entity.setRole(userAccountDTO.getRole());
                    }
                    if (Objects.nonNull(userAccountDTO.getEmail())) {
                        entity.setEmail(userAccountDTO.getEmail());
                    }
                    entity = userAccountRepository.save(entity);
                    return userAccountMapper.toDTO(entity);
                })
                .orElseThrow(() -> new IllegalArgumentException("User account not found"));

    }

    @Override
    public List<UserAccountDTO> getAll() {
        return userAccountRepository.findAll()
                .stream()
                .map(userAccountMapper::toDTO)
                .toList();
    }


    @Override
    public UserAccountDTO getById(Long id) {

        return userAccountRepository.findById(id)
                .map(userAccountMapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("UserAccountDTO not found with id: " + id));

    }

    @Override
    public void delete(Long id) {
        userAccountRepository.deleteById(id);
    }

    @Override
    public void deleteAll(List<UserAccountDTO> d) {
        d.forEach(user -> userAccountRepository.deleteById(user.getId()));

    }
}
