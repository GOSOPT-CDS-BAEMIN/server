package sopt.cds.baemin.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private Long foodId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeId")
    private Store store;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeTypeId")
    private StoreType storeType;

    @Column(name = "food_name", nullable = false)
    private String foodName;

    @Column(nullable = false)
    private int price;

    @Column(name = "is_best", nullable = false)
    private String isBest;

    @Column(name = "food_description", nullable = false)
    private String foodDescription;

    @Column(name = "food_image_url", nullable = false)
    private String foodImageUrl;

}
