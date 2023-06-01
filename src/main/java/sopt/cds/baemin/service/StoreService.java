package sopt.cds.baemin.service;

import static sopt.cds.baemin.exception.Error.NOT_EXIST_STORE_EXCEPTION;
import static sopt.cds.baemin.exception.Error.NOT_EXIST_STORE_ID_EXCEPTION;
import static sopt.cds.baemin.exception.Error.NOT_EXIST_STORE_TYPE_ID_EXCEPTION;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sopt.cds.baemin.domain.Store;
import sopt.cds.baemin.domain.StoreImage;
import sopt.cds.baemin.domain.StoreType;
import sopt.cds.baemin.dto.food.FoodInfoDto;
import sopt.cds.baemin.dto.store.StoreDetailInfoDto;
import sopt.cds.baemin.dto.store.StoreInfoDto;
import sopt.cds.baemin.exception.model.NotFoundException;
import sopt.cds.baemin.repository.FoodRepository;
import sopt.cds.baemin.repository.StoreImageRepository;
import sopt.cds.baemin.repository.StoreRepository;
import sopt.cds.baemin.repository.StoreTypeRepository;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final StoreImageRepository storeImageRepository;
    private final StoreTypeRepository storeTypeRepository;
    private final FoodRepository foodRepository;

    public List<StoreInfoDto> findStores(final Optional<Long> storeTypeId) {
        return storeTypeId.map(this::getStoresWithStoreType).orElseGet(this::getStores);
    }

    public StoreDetailInfoDto findOneStore(final Long storeId) {
        Store store = findStoreById(storeId);
        StoreInfoDto storeInfo = StoreInfoDto.of(store, getStoreImage(store));
        List<FoodInfoDto> foodInfoDtos = getFoods(store);
        return StoreDetailInfoDto.of(storeInfo, foodInfoDtos);
    }

    private List<StoreInfoDto> getStores() {
        List<Store> stores = storeRepository.findAll();
        List<StoreInfoDto> storeInfoDtos = new ArrayList<>();
        addStoreInfo(stores, storeInfoDtos);
        return storeInfoDtos;
    }

    private List<StoreInfoDto> getStoresWithStoreType(final Long storeTypeId) {
        StoreType storeType = getStoreType(storeTypeId);
        List<Store> stores = getStores(storeType);

        List<StoreInfoDto> storeInfoDtos = new ArrayList<>();
        addStoreInfo(stores, storeInfoDtos);

        return storeInfoDtos;
    }

    private void addStoreInfo(final List<Store> stores, final List<StoreInfoDto> storeInfoDtos) {
        for (Store store : stores) {
            StoreImage storeImage = getStoreImage(store);

            storeInfoDtos.add(StoreInfoDto.of(store, storeImage));
        }
    }

    private StoreImage getStoreImage(final Store store) {
        return storeImageRepository.findStoreImageByStore(store).orElseThrow(
                () -> new NotFoundException(NOT_EXIST_STORE_EXCEPTION, NOT_EXIST_STORE_EXCEPTION.getMessage()));
    }

    private List<Store> getStores(final StoreType storeType) {
        return storeRepository.findByStoreType(storeType);
    }

    private StoreType getStoreType(final Long storeTypeId) {
        return storeTypeRepository.findById(storeTypeId).orElseThrow(
                () -> new NotFoundException(NOT_EXIST_STORE_TYPE_ID_EXCEPTION,
                        NOT_EXIST_STORE_ID_EXCEPTION.getMessage()));

    }

    private List<FoodInfoDto> getFoods(Store store) {
        return foodRepository.findAllByStore(store).stream().map(FoodInfoDto::of).collect(Collectors.toList());

    }

    private Store findStoreById(final Long storeId) {
        return storeRepository.findById(storeId).orElseThrow(
                () -> new NotFoundException(NOT_EXIST_STORE_ID_EXCEPTION, NOT_EXIST_STORE_ID_EXCEPTION.getMessage()));
    }
}
