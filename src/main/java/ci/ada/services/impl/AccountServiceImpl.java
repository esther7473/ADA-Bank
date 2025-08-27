package ci.ada.services.impl;

import ci.ada.Repository.AccountRepository;
import ci.ada.models.entity.AccountEntity;
import ci.ada.services.AccountService;
import ci.ada.services.dto.AccountDTO;
import ci.ada.services.mapper.AccountMapper;
import ci.ada.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountDTO save(AccountDTO accountDTO) {
        AccountEntity accountEntity = accountMapper.toEntity(accountDTO);
        accountEntity = accountRepository.save(accountEntity);
        return accountMapper.toDTO(accountEntity);
    }

    @Override
    public AccountDTO saveWithSlug(AccountDTO accountDTO) {
        final String finalSlug = SlugifyUtils.generate("account");
        accountDTO.setSlug(finalSlug);
        return save(accountDTO);
    }

    /*
    A REVOIR
     */
    @Override
    public AccountDTO update(AccountDTO accountDTO) {
        if (Objects.isNull(accountDTO.getSlug())) {
            throw new IllegalArgumentException("slug is required");
        }
        AccountDTO dto = getBySlug(accountDTO.getSlug());
        if (Objects.nonNull(dto)) {
            return save(accountDTO);
        }else  {
            throw new IllegalArgumentException("slug is required");
        }

    }

    /* A REVOIR

     */
    @Override
    public AccountDTO partialUpdate(AccountDTO accountDTO) {
        if (Objects.isNull(accountDTO.getId())) {
            throw new IllegalArgumentException("id is required");
        }


        return accountRepository.findById(accountDTO.getId())
                .map(entity->{
                    if (Objects.nonNull(accountDTO.getAccountStatus())){
                        entity.setAccountStatus(accountDTO.getAccountStatus());
                    }
                    if (Objects.nonNull(accountDTO.getAccountType())){
                        entity.setAccountType(accountDTO.getAccountType());
                    }
                    if (Objects.nonNull(accountDTO.getBalance())){
                        entity.setBalance(accountDTO.getBalance());
                    }
                    if (Objects.nonNull(accountDTO.getAccountType())){
                        entity.setAccountType(accountDTO.getAccountType());
                    }
                    entity = accountRepository.save(entity);
                    return accountMapper.toDTO(entity);

                }).orElseThrow(()->new IllegalArgumentException("MAJ account impossible"));
    }

    @Override
    public List<AccountDTO> getAll() {
        return  accountRepository.findAll().stream().map(accountMapper::toDTO).toList();
    }

    @Override
    public AccountDTO getById(Long id) {
        return accountRepository.findById(id)
                .map(accountMapper::toDTO)
                .orElseThrow(()->new IllegalArgumentException("account not found"));

    }

    @Override
    public AccountDTO getBySlug(String slug) {
        return accountRepository.findBySlug(slug).map(accountMapper::toDTO).orElseThrow(()->new IllegalArgumentException("account not found"));
    }

    @Override
    public List<AccountDTO> getAllById(Long id) {
        return List.of();
    }

    @Override
    public void delete(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public void deleteAll(List<AccountDTO> d) {
        accountRepository.deleteAll();
    }

    @Override
    public List<AccountDTO> getAllByCustomerSlug(String customerSlug) {
        return accountRepository.findAllByCustomerSlug(customerSlug);
    }
}
