package ci.ada.services;

import ci.ada.Repository.AccountRepository;
import ci.ada.entity.Account;
import ci.ada.entity.Client;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class AccountService {
    private AccountRepository accountRepository;
    private final EmailService emailService;

    private EntityManager entityManager;

    public void createAccount(Account account) {
        try {

            Client clientRef = entityManager.getReference(Client.class, account.getClient().getId());
            account.setClient(clientRef);
            accountRepository.save(account);
            account =  accountRepository.save(account);
            log.info("Account created");


            emailService.sendAccountCreationEmail(account.getClient().getEmail(),
                    account.getClient().getName(),
                    account.getNumber(),
                    account.getClient().getBank().getNom()
            );
        }catch (Exception e){
            throw  new RuntimeException("Error creating Account",e);
        }
    }

    public Account findAccountById(Long id) {
        try {
            return accountRepository.findById(id).orElse(null);
        }catch (Exception e){
            throw  new RuntimeException("Error getting Account by id",e);
        }
    }

    @Transactional
    public void updateAccountById(Long id, Account account) {
        accountRepository.findById(id)
                .map(existingAccount -> {
                    existingAccount.setBalance(account.getBalance());
                    existingAccount.setStatus(account.getStatus());
                    existingAccount.setTypeAccount(account.getTypeAccount());
                    existingAccount.setNumber(account.getNumber());
                    return accountRepository.save(existingAccount);
                }).orElseThrow(() -> new RuntimeException("Account not found with id " + id));
    }

    public void deleteAccountById(Long id) {
        try {
            accountRepository.deleteById(id);
        }catch (Exception e){
            throw  new RuntimeException("Error deleting Account by id",e);
        }
    }

    public List<Account> findAllAccounts() {
        try {
            return accountRepository.findAll();
        }catch (Exception e){
            throw  new RuntimeException("Error getting all Accounts",e);
        }
    }
}
