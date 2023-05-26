package sopt.cds.baemin.dto.cart;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import sopt.cds.baemin.domain.Store;
import sopt.cds.baemin.dto.food.FoodCartDto;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StoreCartMenuDto {

    private Long storeId;
    private String storeName;
    private int deliveryFee;
    private int minOrderPrice;
    private List<FoodCartDto> foods;

    public static StoreCartMenuDto of(Store store, List<FoodCartDto> foods) {
        return StoreCartMenuDto.builder()
                .storeId(store.getStoreId())
                .storeName(store.getStoreName())
                .deliveryFee(store.getDeliveryFee())
                .minOrderPrice(store.getMinOrderPrice())
                .foods(foods)
                .build();
    }
}
