package sopt.cds.baemin.controller;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sopt.cds.baemin.common.dto.ApiResponse;
import sopt.cds.baemin.dto.cart.CartOrderRequestDto;
import sopt.cds.baemin.exception.Success;
import sopt.cds.baemin.service.CartService;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @DeleteMapping("/order")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse orderCartItems(@RequestBody final CartOrderRequestDto request) {
        cartService.orderCartItems(request);
        return ApiResponse.success(Success.ORDER_SUCCESS);
    }

    @GetMapping("/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse findClientCartList(@PathVariable final Long clientId) {
        return ApiResponse.success(Success.FIND_CART_ITEMS_SUCCESS, cartService.findClientCartData(clientId));
    }


}
