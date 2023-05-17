package sopt.cds.baemin.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access =  AccessLevel.PRIVATE)
public enum Success {

    /**
     * 200 OK
     */
    SIGNUP_SUCCESS(HttpStatus.OK, "리뷰 조회에 성공했습니다.", true),
    ;

    private final HttpStatus httpStatus;
    private final String message;
    private final boolean isSuccess;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}