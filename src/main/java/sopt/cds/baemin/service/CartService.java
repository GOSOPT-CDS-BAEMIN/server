package sopt.cds.baemin.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sopt.cds.baemin.domain.Food;
import sopt.cds.baemin.dto.cart.CartOrderRequestDto;
import sopt.cds.baemin.exception.model.NotFoundException;
import sopt.cds.baemin.repository.CartRepository;
import sopt.cds.baemin.repository.FoodRepository;

import java.util.List;

import static sopt.cds.baemin.exception.Error.NOT_EXIST_FOOD_ID_EXCEPTION;
import static sopt.cds.baemin.exception.Error.NOT_EXIST_FOOD_IN_CART_EXCEPTION;

@Service
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CartService {

    private final CartRepository cartRepository;
    private final FoodRepository foodRepository;

    public void orderCartItems(CartOrderRequestDto request) {
        List<Long> foodIds = request.getFoodIds();

        for (Long foodId : foodIds) {
            deleteFoodOnCart(getFood(foodId));
        }
    }

    private void deleteFoodOnCart(Food food) {
        try {
            cartRepository.deleteByFood(food);
        } catch (Exception e) {
            throw new NotFoundException(NOT_EXIST_FOOD_IN_CART_EXCEPTION, NOT_EXIST_FOOD_IN_CART_EXCEPTION.getMessage());
        }
    }

    private Food getFood(Long foodId) {
        return foodRepository.findById(foodId).orElseThrow(() ->
                new NotFoundException(NOT_EXIST_FOOD_ID_EXCEPTION, NOT_EXIST_FOOD_ID_EXCEPTION.getMessage()));
    }
}
