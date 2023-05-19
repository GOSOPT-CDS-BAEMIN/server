package sopt.cds.baemin.service;

import static sopt.cds.baemin.exception.Error.NOT_EXIST_FOOD_ID_EXCEPTION;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sopt.cds.baemin.domain.Food;
import sopt.cds.baemin.dto.food.FoodInfoDto;
import sopt.cds.baemin.exception.model.NotFoundException;
import sopt.cds.baemin.repository.FoodRepository;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;

    public FoodInfoDto findOneFood(final Long foodId) {
        Food food = foodRepository.findById(foodId).orElseThrow(
                () -> new NotFoundException(NOT_EXIST_FOOD_ID_EXCEPTION, NOT_EXIST_FOOD_ID_EXCEPTION.getMessage()));
        return FoodInfoDto.of(food);
    }
}
