package sopt.cds.baemin.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import sopt.cds.baemin.domain.Store;

@Repository
public interface StoreCustomRepository {
    List<Store> findStoresByStoreTypeId(final Long storeTypeId);
}
