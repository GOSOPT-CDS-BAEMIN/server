package sopt.cds.baemin.dto.review;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewResponseDto {

    private final Long reviewId;
    private final String nickname;
    private final float reviewRate;
    private final String content;
    private final String reviewImageUrl;


}
