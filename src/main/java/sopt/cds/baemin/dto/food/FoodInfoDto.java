package sopt.cds.baemin.dto.food;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import sopt.cds.baemin.domain.Food;

@Getter
@Builder
@AllArgsConstructor
public class FoodInfoDto {
    private Long foodId;
    private String foodName;
    private int price;
    private boolean isBest;
    private String foodDescription;
    private String foodImageUrl;

    public static FoodInfoDto of(Food food) {
        return FoodInfoDto.builder()
                .foodId(food.getFoodId())
                .foodName(food.getFoodName())
                .price(food.getPrice())
                .isBest(food.getIsBest())
                .foodDescription(food.getFoodDescription())
                .foodImageUrl(food.getFoodImageUrl())
                .build();
    }
}
