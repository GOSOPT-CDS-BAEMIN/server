package sopt.cds.baemin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sopt.cds.baemin.domain.Store;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findById(Long storeId);
}
