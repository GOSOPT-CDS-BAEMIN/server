package sopt.cds.baemin.dto.store;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import sopt.cds.baemin.dto.food.FoodInfoDto;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StoreDetailInfoDto {
    private StoreInfoDto storeInfo;
    private List<FoodInfoDto> foods;

    public static StoreDetailInfoDto of(StoreInfoDto storeInfo, List<FoodInfoDto> foods) {
        return StoreDetailInfoDto.builder().storeInfo(storeInfo).foods(foods).build();
    }
}
