package sopt.cds.baemin.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import sopt.cds.baemin.domain.Store;
import sopt.cds.baemin.dto.food.FoodInfoDto;

@Repository
public interface StoreCustomRepository {
    List<Store> findStoresByStoreTypeId(final Long storeTypeId);

    List<FoodInfoDto> findAllFoodWithStore(final Store store);
}
