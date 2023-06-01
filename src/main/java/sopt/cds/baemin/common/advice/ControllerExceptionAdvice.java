package sopt.cds.baemin.common.advice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sopt.cds.baemin.common.dto.ApiResponse;
import sopt.cds.baemin.exception.Error;
import sopt.cds.baemin.exception.model.CustomException;
import sopt.cds.baemin.tools.slack.SlackUtil;

import java.io.IOException;
import java.util.Objects;

@RestControllerAdvice
@Component
@RequiredArgsConstructor
public class ControllerExceptionAdvice {
    private final SlackUtil slackUtil;

    /**
     * 400 BAD_REQUEST
     */

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ApiResponse handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        FieldError fieldError = Objects.requireNonNull(e.getFieldError());

        return ApiResponse.error(Error.REQUEST_VALIDATION_EXCEPTION, String.format("%s. (%s)", fieldError.getDefaultMessage(), fieldError.getField()));
    }

    /**
     * 500 Internal Server
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    protected ApiResponse<Object> handleException(final Exception error) throws IOException {
        slackUtil.sendAlert(error);
        return ApiResponse.error(Error.INTERNAL_SERVER_ERROR);
    }

    /**
     * Custom custom error
     */
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ApiResponse> handleSoptException(CustomException error) throws IOException {
        slackUtil.sendAlert(error);
        return ResponseEntity.status(error.getHttpStatus())
                .body(ApiResponse.error(error.getError(), error.getMessage()));
    }
}
