package sopt.cds.baemin.dto.store;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import sopt.cds.baemin.domain.Store;
import sopt.cds.baemin.domain.StoreImage;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StoreInfoDto {
    private Long storeId;
    private Long storeTypeId;
    private String storeType;
    private String storeName;
    private String deliveryTime;
    private String description;
    private int minOrderPrice;
    private int deliveryFee;
    private float rate;
    private boolean isCouponExist;
    private boolean isNew;
    private String firstImageUrl;
    private String secondImageUrl;
    private String thirdImageUrl;

    public static StoreInfoDto of(Store store, StoreImage storeImage) {
        return StoreInfoDto.builder().storeId(store.getStoreId()).storeTypeId(store.getStoreType().getStoreTypeId())
                .storeType(store.getStoreType().getType()).storeName(store.getStoreName())
                .deliveryTime(getDeliveryTimeToString(store.getMinDeliveryTime(), store.getMaxDeliveryTime()))
                .description(store.getDescription()).minOrderPrice(store.getMinOrderPrice())
                .deliveryFee(store.getDeliveryFee()).rate(store.getRate()).isCouponExist(store.getIsCouponExist())
                .isNew(store.getIsNew())
                .firstImageUrl(storeImage.getFirstStoreImageUrl())
                .secondImageUrl(storeImage.getSecondStoreImageUrl())
                .thirdImageUrl(storeImage.getThirdStoreImageUrl())
                .build();
    }

    private static String getDeliveryTimeToString(int minDeliveryTime, int maxDeliveryTime) {
        return String.valueOf(minDeliveryTime) + "분~" + String.valueOf(maxDeliveryTime)+"분";
    }

}
