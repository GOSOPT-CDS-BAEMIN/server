package sopt.cds.baemin.dto.cart;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartOrderRequestDto {

    private List<Long> foodIds;
}
