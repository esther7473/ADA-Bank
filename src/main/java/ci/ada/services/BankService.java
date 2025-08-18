package ci.ada.services;

import ci.ada.Repository.BankRepository;
import ci.ada.entity.Bank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class BankService {
    private BankRepository bankRepository;

    public Bank createBank(Bank bank) {
        try {
            bank =  bankRepository.save(bank);
            log.info("Bank created");
            return bank;
        }catch (Exception e){
            log.error("Error creating bank: {}", e.getMessage(), e);
            throw new RuntimeException("Error creating bank", e);        }

    }

    public void updateBankById(Long id, Bank bank) {
        try {
            Bank existingBank = bankRepository.findById(id).orElse(null);
            if (existingBank != null) {
                existingBank.setId(bank.getId());
                existingBank.setNom(bank.getNom());
                existingBank.setEmail(bank.getEmail());
                existingBank.setPhone(bank.getPhone());
                existingBank.setPays(bank.getPays());
                existingBank.setVille(bank.getVille());
                existingBank.setNumbreClient(bank.getNumbreClient());
                bankRepository.save(existingBank);
            }
        }catch (Exception e){
            throw new RuntimeException("Error updating bank");
        }
    }
    public Bank findBankById(Long id) {
        try {
            return bankRepository.findById(id).orElse(null);
        }catch (Exception e){
            throw new RuntimeException("Error finding bank");
        }
    }
    public List<Bank> findAllBanks() {
        try {
            return bankRepository.findAll();
        }catch (Exception e){
            throw new RuntimeException("Error finding banks");
        }
    }
    public void deleteBankById(Long id) {
        try {
            bankRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("Error deleting bank");
        }
    }
}
