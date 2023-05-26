package sopt.cds.baemin.dto.cart;

import java.util.List;
import lombok.Getter;

@Getter
public class CartAddRequestDto {
    private Long clientId;
    private List<Long> foodIds;
    private List<Integer> foodCounts;
}
