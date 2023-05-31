package sopt.cds.baemin.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sopt.cds.baemin.common.dto.ApiResponse;
import sopt.cds.baemin.dto.review.ReviewResponseDto;
import sopt.cds.baemin.exception.Success;
import sopt.cds.baemin.service.ReviewService;

import java.util.List;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/{storeId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse findStoreReview(@PathVariable final Long storeId) {
        List<ReviewResponseDto> reviews = reviewService.findReviewByStoreId(storeId);
        return ApiResponse.success(Success.FIND_REVIEW_SUCCESS, reviews);
    }

}
