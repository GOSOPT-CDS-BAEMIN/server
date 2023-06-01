package sopt.cds.baemin.dto.food;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import sopt.cds.baemin.domain.Cart;
import sopt.cds.baemin.domain.Food;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FoodCartDto {

    private Long foodId;
    private String foodName;
    private int price;
    private String foodImageUrl;
    private String foodDescription;
    private int foodCount;

    public static FoodCartDto from(Cart cart) {
        Food food = cart.getFood();
        return FoodCartDto.builder()
                .foodId(food.getFoodId())
                .foodName(food.getFoodName())
                .price(food.getPrice())
                .foodDescription(food.getFoodDescription())
                .foodImageUrl(food.getFoodImageUrl())
                .foodCount(cart.getFoodCount())
                .build();
    }
}
