package sopt.cds.baemin.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sopt.cds.baemin.domain.Review;
import sopt.cds.baemin.domain.Store;
import sopt.cds.baemin.dto.review.ReviewResponseDto;
import sopt.cds.baemin.exception.Error;
import sopt.cds.baemin.exception.model.NotFoundException;
import sopt.cds.baemin.repository.ReviewRepository;
import sopt.cds.baemin.repository.StoreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    public List<ReviewResponseDto> findReviewByStoreId(Long storeId) {
        Store store = getStore(storeId);
        List<ReviewResponseDto> reviews = getReviews(store);

        return reviews;
    }

    private List<ReviewResponseDto> getReviews(Store store) {
        List<Review> reviews = reviewRepository.findByStore(store);
        List<ReviewResponseDto> reviewResponseDtos = reviews.stream()
                .map(Review::toDto)
                .collect(Collectors.toList());
        return reviewResponseDtos;
    }

    private Store getStore(Long storeId) {
        return storeRepository.findById(storeId).orElseThrow(() ->
                new NotFoundException(Error.NOT_EXIST_STORE_ID_EXCEPTION, Error.NOT_EXIST_STORE_ID_EXCEPTION.getMessage()));
    }

}
