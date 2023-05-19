package sopt.cds.baemin.controller;

import static sopt.cds.baemin.exception.Success.FOOD_GET_SUCCESS;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sopt.cds.baemin.common.dto.ApiResponse;
import sopt.cds.baemin.dto.food.FoodInfoDto;
import sopt.cds.baemin.service.FoodService;

@RestController
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;

    @GetMapping("/{foodId}")
    public ApiResponse<FoodInfoDto> findOneFood(@PathVariable final Long foodId) {
        FoodInfoDto food = foodService.findOneFood(foodId);
        return ApiResponse.success(FOOD_GET_SUCCESS, food);
    }
}
