package sopt.cds.baemin.controller;

import static sopt.cds.baemin.exception.Success.STORES_GET_SUCCESS;
import static sopt.cds.baemin.exception.Success.STORE_GET_SUCCESS;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sopt.cds.baemin.common.dto.ApiResponse;
import sopt.cds.baemin.dto.store.StoreDetailInfoDto;
import sopt.cds.baemin.dto.store.StoreInfoDto;
import sopt.cds.baemin.service.StoreService;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @GetMapping
    public ApiResponse<List<StoreInfoDto>> findStores(@RequestParam(value = "type") final Optional<Long> storeTypeId) {
        return ApiResponse.success(STORES_GET_SUCCESS, storeService.findStores(storeTypeId));
    }

    @GetMapping("/{storeId}")
    public ApiResponse<StoreDetailInfoDto> findOneStore(@PathVariable final Long storeId) {
        StoreDetailInfoDto store = storeService.findOneStore(storeId);
        return ApiResponse.success(STORE_GET_SUCCESS, store);
    }
}
