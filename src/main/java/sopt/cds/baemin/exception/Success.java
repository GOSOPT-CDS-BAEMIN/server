package sopt.cds.baemin.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Success {

    /**
     * 200 OK
     */
    FIND_REVIEW_SUCCESS(HttpStatus.OK, "리뷰 조회에 성공했습니다.", true),
    FIND_CART_ITEMS_SUCCESS(HttpStatus.OK, "장바구니 목록 조회에 성공했습니다.", true),
    ORDER_SUCCESS(HttpStatus.OK, "주문 또는 삭제 성공.", true),
    STORES_GET_SUCCESS(HttpStatus.OK, "가게 목록 조회에 성공했습니다.", true),
    FOOD_GET_SUCCESS(HttpStatus.OK, "음식 조회에 성공했습니다.", true),
    STORE_GET_SUCCESS(HttpStatus.OK, "가게 조회에 성공했습니다.", true),
    ;

    private final HttpStatus httpStatus;
    private final String message;
    private final boolean isSuccess;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}