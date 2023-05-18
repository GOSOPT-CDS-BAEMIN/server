package sopt.cds.baemin.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sopt.cds.baemin.domain.Store;
import sopt.cds.baemin.dto.store.StoreInfoDto;
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
        return stores
                .stream()
                .map(s -> StoreInfoDto.of(s, storeImageService.getStoreImages(s)))
                .collect(Collectors.toList());
    }
}
