package ci.ada.Repository;

import ci.ada.models.entity.TransferEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends CrudRepository<TransferEntity,Long> {
}
