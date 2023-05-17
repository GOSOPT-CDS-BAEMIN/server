package sopt.cds.baemin.domain;

import lombok.*;
import sopt.cds.baemin.dto.review.ReviewResponseDto;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foodId")
    private Food food;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeId")
    private Store store;

    @Column(nullable = false)
    private String nickname;

    @Column(name = "review_rate", nullable = false)
    private float reviewRate;

    @Column(nullable = false)
    private String content;

    @Column(name = "review_image_url", nullable = false)
    private String reviewImageUrl;


    public ReviewResponseDto toDto() {
        return ReviewResponseDto.builder()
                .reviewId(reviewId)
                .nickname(nickname)
                .reviewRate(reviewRate)
                .content(content)
                .reviewImageUrl(reviewImageUrl)
                .build();
    }
}
