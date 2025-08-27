package ci.ada.Repository;

import ci.ada.models.entity.BasicOperationEntity;
import ci.ada.models.entity.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasicOperationRepository extends JpaRepository<BasicOperationEntity, Long> {
    Optional<BasicOperationEntity> findBySlug(String slug);

}
