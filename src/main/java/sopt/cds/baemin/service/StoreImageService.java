package sopt.cds.baemin.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sopt.cds.baemin.domain.Store;
import sopt.cds.baemin.domain.StoreImage;
import sopt.cds.baemin.repository.StoreImageRepository;

@Service
@RequiredArgsConstructor
public class StoreImageService {
    private final StoreImageRepository storeImageRepository;

    public String[] getStoreImages(Store store) {
        Optional<StoreImage> storeImage = storeImageRepository.findStoreImageByStore(store);
        if (storeImage.isPresent()) {
            return storeImage.get().getImageUrls();
        }
        return new String[]{};
    }
}
