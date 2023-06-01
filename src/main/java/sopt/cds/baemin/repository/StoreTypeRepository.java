package sopt.cds.baemin.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import sopt.cds.baemin.domain.StoreType;

public interface StoreTypeRepository extends JpaRepository<StoreType, Long> {

    Optional<StoreType> findById(Long storeTypeId);
}
