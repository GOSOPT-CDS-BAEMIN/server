package sopt.cds.baemin.controller;

import static sopt.cds.baemin.exception.Success.STORES_GET_SUCCESS;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sopt.cds.baemin.common.dto.ApiResponse;
import sopt.cds.baemin.dto.store.StoreInfoDto;
import sopt.cds.baemin.service.StoreService;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @GetMapping
    public ApiResponse<List<StoreInfoDto>> findStores(@RequestParam(value = "type") @Nullable Long storeTypeId) {
        List<StoreInfoDto> stores = storeService.findStores(storeTypeId);
        return ApiResponse.success(STORES_GET_SUCCESS, stores);
    }
}
