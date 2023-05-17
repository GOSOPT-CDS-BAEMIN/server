package sopt.cds.baemin.domain;

import lombok.*;

import javax.persistence.*;

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
}
