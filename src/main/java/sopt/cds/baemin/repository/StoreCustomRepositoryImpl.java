package sopt.cds.baemin.repository;

import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sopt.cds.baemin.domain.Store;
import sopt.cds.baemin.dto.food.FoodInfoDto;

@Repository
@RequiredArgsConstructor
public class StoreCustomRepositoryImpl implements StoreCustomRepository {
    private final EntityManager em;

    @Override
    public List<Store> findStoresByStoreTypeId(final Long storeTypeId) {
        String query = "select s from Store s join s.storeType t on t.storeTypeId =: storeTypeId";
        return em.createQuery(query, Store.class).setParameter("storeTypeId", storeTypeId).getResultList();
    }

    @Override
    public List<FoodInfoDto> findAllFoodWithStore(final Store store) {
        return em.createQuery(
                        "select distinct new sopt.cds.baemin.dto.food.FoodInfoDto(f.foodId, f.foodName, f.price, f.isBest, f.foodDescription, f.foodImageUrl)"
                                + "from Food f where f.store=:store", FoodInfoDto.class)
                .setParameter("store", store).getResultList();
    }
}
