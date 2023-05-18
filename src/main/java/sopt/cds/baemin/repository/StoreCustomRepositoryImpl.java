package sopt.cds.baemin.repository;

import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sopt.cds.baemin.domain.Store;

@Repository
@RequiredArgsConstructor
public class StoreCustomRepositoryImpl implements StoreCustomRepository {
    private final EntityManager em;

    @Override
    public List<Store> findStoresByStoreTypeId(final Long storeTypeId) {
        String query = "select s from Store s join s.storeType t on t.storeTypeId =: storeTypeId";
        return em.createQuery(query, Store.class)
                .setParameter("storeTypeId", storeTypeId).getResultList();
    }
}
