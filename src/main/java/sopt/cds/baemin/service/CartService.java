package sopt.cds.baemin.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sopt.cds.baemin.domain.Cart;
import sopt.cds.baemin.domain.Client;
import sopt.cds.baemin.domain.Food;
import sopt.cds.baemin.domain.Store;
import sopt.cds.baemin.dto.cart.CartOrderRequestDto;
import sopt.cds.baemin.dto.cart.StoreCartMenuDto;
import sopt.cds.baemin.dto.food.FoodCartDto;
import sopt.cds.baemin.exception.model.NotFoundException;
import sopt.cds.baemin.repository.CartRepository;
import sopt.cds.baemin.repository.ClientRepository;
import sopt.cds.baemin.repository.FoodRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static sopt.cds.baemin.exception.Error.*;

@Service
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CartService {

    private final CartRepository cartRepository;
    private final FoodRepository foodRepository;
    private final ClientRepository clientRepository;

    public void orderCartItems(CartOrderRequestDto request) {
        List<Long> foodIds = request.getFoodIds();

        for (Long foodId : foodIds) {
            deleteFoodOnCart(getFood(foodId));
        }
    }

    public List<StoreCartMenuDto> findClientCartData(Long clientId) {
        Client client = getClient(clientId);

        List<Cart> clientCartDatum = cartRepository.findByClient(client);

        Map<Store, List<FoodCartDto>> cartItems = generateCartItemMap(getDistinctStoresInCart(clientCartDatum));
        addItemsInCart(clientCartDatum, cartItems);

        return getStoreCartMenus(cartItems);
    }

    private Client getClient(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundException(NOT_EXIST_CLIENT_ID_EXCEPTION, NOT_EXIST_CLIENT_ID_EXCEPTION.getMessage()));
    }

    // 장바구니에 매장 별 아이템 추가
    private void addItemsInCart(List<Cart> clientCartDatum, Map<Store, List<FoodCartDto>> cartItems) {
        for (Cart cart : clientCartDatum) {
            Store store = cart.getStore();

            List<FoodCartDto> foods = cartItems.get(store);
            foods.add(FoodCartDto.from(cart));

            cartItems.replace(store, foods);
        }
    }

    // 매장 별 장바구니 아이템 목록 생성
    private Map<Store, List<FoodCartDto>> generateCartItemMap(List<Store> storesInCart) {
        Map<Store, List<FoodCartDto>> cartItems = new HashMap<>();
        for (Store store : storesInCart) {
            cartItems.put(store, new ArrayList<>());
        }
        return cartItems;
    }

    private List<StoreCartMenuDto> getStoreCartMenus(Map<Store, List<FoodCartDto>> cartItems) {
        List<StoreCartMenuDto> storeCartMenus = new ArrayList<>();
        for (Store store : cartItems.keySet()) {
            StoreCartMenuDto storeCartMenu = StoreCartMenuDto.of(store, cartItems.get(store));
            storeCartMenus.add(storeCartMenu);
        }
        
        return storeCartMenus;
    }

    // 장바구니에 있는 매장 목록 받기
    private List<Store> getDistinctStoresInCart(List<Cart> clientCartDatum) {
        return clientCartDatum.stream()
                .map(cart -> cart.getStore())
                .collect(Collectors.toList())
                .stream().distinct()
                .collect(Collectors.toList());
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
