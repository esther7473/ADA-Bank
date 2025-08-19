package ci.ada.Repository;

import ci.ada.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

    List<Bank> findBanksByVille(String ville);

    List<Bank> findAllByNumbreClient(Integer numbreClient);

    List<Bank> findAllByPays(String pays);

    List<Bank> findByNomContainingIgnoreCase(String nom);

    List<Bank> findByVilleContainingIgnoreCase(String ville);

    List<Bank> findByPaysContainingIgnoreCase(String pays);

    List<Bank> findByNomContainingIgnoreCaseOrVilleContainingIgnoreCaseOrPaysContainingIgnoreCase(
            String nom, String ville, String pays
    );
}
