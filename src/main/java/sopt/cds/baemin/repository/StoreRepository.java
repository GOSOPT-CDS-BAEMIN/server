package sopt.cds.baemin.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sopt.cds.baemin.domain.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long>, StoreCustomRepository {
    Optional<Store> findById(Long storeId);
}
