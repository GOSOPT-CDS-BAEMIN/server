package sopt.cds.baemin.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long storeId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeTypeId")
    private StoreType storeType;

    @Column(name = "store_name", nullable = false)
    private String storeName;

    @Column(name = "min_delivery_time", nullable = false)
    private int minDeliveryTime;

    @Column(name = "max_delivery_time", nullable = false)
    private int maxDeliveryTime;

    @Column(name = "store_description", nullable = false)
    private String description;

    @Column(name = "min_order_price", nullable = false)
    private int minOrderPrice;

    @Column(name = "delivery_fee", nullable = false)
    private int deliveryFee;

    @Column(name = "rate", nullable = false)
    private float rate;

    @Column(name = "is_coupon_exist", nullable = false)
    private boolean isCouponExist;

    @Column(name = "is_new", nullable = false)
    private boolean isNew;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeId")
    private List<Food> foods = new ArrayList<>();

    public boolean getIsCouponExist() {
        return this.isCouponExist;
    }

    public boolean getIsNew() {
        return this.isNew;
    }
}
