package sopt.cds.baemin.service;

import static sopt.cds.baemin.exception.Error.NOT_EXIST_STORE_ID_EXCEPTION;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sopt.cds.baemin.domain.Store;
import sopt.cds.baemin.dto.food.FoodInfoDto;
import sopt.cds.baemin.dto.store.StoreDetailInfoDto;
import sopt.cds.baemin.dto.store.StoreInfoDto;
import sopt.cds.baemin.exception.model.NotFoundException;
import sopt.cds.baemin.repository.StoreImageRepository;
import sopt.cds.baemin.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final StoreImageRepository storeImageRepository;
    private final StoreImageService storeImageService;

    public List<StoreInfoDto> findStores(Long storeTypeId) {
        List<Store> stores =
                storeTypeId == null ? storeRepository.findAll() : storeRepository.findStoresByStoreTypeId(storeTypeId);
        return stores.stream().map(s -> StoreInfoDto.of(s, storeImageService.getStoreImages(s)))
                .collect(Collectors.toList());
    }

    public StoreDetailInfoDto findOneStore(final Long storeId) {
        Store store = findStoreById(storeId);
        StoreInfoDto storeInfo = StoreInfoDto.of(store, storeImageService.getStoreImages(store));
        List<FoodInfoDto> foodInfoDtos = storeRepository.findAllFoodWithStore(store);
        return StoreDetailInfoDto.of(storeInfo, foodInfoDtos);
    }

    private Store findStoreById(final Long storeId) {
        return storeRepository.findById(storeId).orElseThrow(
                () -> new NotFoundException(NOT_EXIST_STORE_ID_EXCEPTION, NOT_EXIST_STORE_ID_EXCEPTION.getMessage()));
    }
}
