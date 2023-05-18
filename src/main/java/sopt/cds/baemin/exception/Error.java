package sopt.cds.baemin.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Error {

    /**
     * 400 BAD REQUEST
     */
    REQUEST_VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 요청입니다", false),

    /**
     * 404 NOT FOUND
     */
    NOT_EXIST_STORE_ID_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 매장번호입니다", false),
    NOT_EXIST_FOOD_ID_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 음식번호입니다", false),
    NOT_EXIST_FOOD_IN_CART_EXCEPTION(HttpStatus.NOT_FOUND, "장바구니 내에 존재하지 않는 음식입니다", false),
    NOT_EXIST_STORE_IMAGE_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 매장사진입니다", false),

    /**
     * 409 CONFLICT
     */


    /**
     * 500 INTERNAL SERVER ERROR
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류", false),
    ;

    private final HttpStatus httpStatus;
    private final String message;
    private final boolean isSuccess;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}