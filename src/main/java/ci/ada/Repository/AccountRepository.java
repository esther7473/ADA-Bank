package ci.ada.Repository;

import ci.ada.models.entity.AccountEntity;
import ci.ada.services.dto.AccountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    Optional<AccountEntity> findBySlug(String slug);

    List<AccountDTO> findAllByCustomerSlug(String customerSlug);
}
