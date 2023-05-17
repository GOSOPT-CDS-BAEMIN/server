package sopt.cds.baemin.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sopt.cds.baemin.exception.Error;
import sopt.cds.baemin.exception.Success;
import sopt.cds.baemin.exception.SuccessStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {

    private final int status;
    private final boolean success;
    private final String message;
    private T data;

    public static ApiResponse success(Success success) {
        return new ApiResponse<>(success.getHttpStatusCode(), success.isSuccess(), success.getMessage());
    }

    public static <T> ApiResponse<T> success(Success success, T data) {
        return new ApiResponse<T>(success.getHttpStatusCode(), success.isSuccess(), success.getMessage(), data);
    }

    public static ApiResponse error(Error error) {
        return new ApiResponse<>(error.getHttpStatusCode(), error.isSuccess(), error.getMessage());
    }

    public static ApiResponse error(Error error, String message) {
        return new ApiResponse<>(error.getHttpStatusCode(), error.isSuccess(), message);
    }
}
