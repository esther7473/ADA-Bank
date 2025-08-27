package ci.ada.Repository;

import ci.ada.models.entity.CustomerEntity;
import ci.ada.models.entity.UserAccountEntity;
import ci.ada.services.dto.CustomerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findBySlug(String slug);

    List<CustomerEntity> findAllByBankEntitySlug(String bankEntitySlug);
}
