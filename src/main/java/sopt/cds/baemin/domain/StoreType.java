package sopt.cds.baemin.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreType {

    @Id
    @Column(name = "store_type_id")
    private Long storeTypeId;

    @Column
    private String type;

    // 정적 팩토리 메서드
    public static StoreType of(Long storeTypeId, String type) {
        return new StoreType(storeTypeId, type);
    }
}
