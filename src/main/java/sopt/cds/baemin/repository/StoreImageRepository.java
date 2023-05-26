package sopt.cds.baemin.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sopt.cds.baemin.domain.Store;
import sopt.cds.baemin.domain.StoreImage;

public interface StoreImageRepository extends JpaRepository<StoreImage, Long> {
    Optional<StoreImage> findStoreImageByStore(Store store);
}
