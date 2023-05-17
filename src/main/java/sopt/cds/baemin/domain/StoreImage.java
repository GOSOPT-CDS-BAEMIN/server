package sopt.cds.baemin.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_image_id")
    private Long storeImageId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeId")
    private Store store;

    @Column(name = "first_store_image_url")
    private String firstStoreImageUrl;

    @Column(name = "second_store_image_url")
    private String secondStoreImageUrl;

    @Column(name = "third_store_image_url")
    private String thirdStoreImageUrl;

    // 정적 팩토리 메서드
    public static StoreImage of(Store store, String firstStoreImageUrl, String secondStoreImageUrl, String thirdStoreImageUrl) {
        return builder()
                .store(store)
                .firstStoreImageUrl(firstStoreImageUrl)
                .secondStoreImageUrl(secondStoreImageUrl)
                .thirdStoreImageUrl(thirdStoreImageUrl)
                .build();
    }
}
