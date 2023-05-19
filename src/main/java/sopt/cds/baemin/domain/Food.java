package sopt.cds.baemin.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
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
    private boolean isBest;

    @Column(name = "food_description", nullable = false)
    private String foodDescription;

    @Column(name = "food_image_url", nullable = false)
    private String foodImageUrl;

    public boolean getIsBest() {
        return this.isBest;
    }
}
