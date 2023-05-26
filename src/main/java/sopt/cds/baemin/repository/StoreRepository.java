package sopt.cds.baemin.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sopt.cds.baemin.domain.Store;
import sopt.cds.baemin.domain.StoreType;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findById(Long storeId);

    List<Store> findByStoreType(StoreType storeType);
}
