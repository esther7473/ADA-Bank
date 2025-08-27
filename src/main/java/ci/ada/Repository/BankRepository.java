package ci.ada.Repository;

import ci.ada.models.entity.AccountEntity;
import ci.ada.models.entity.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<BankEntity, Long> {

    Optional<BankEntity> findBySlug(String slug);

}
