package systematicTraders.tdt.exception.exceptionHandlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import systematicTraders.tdt.exception.customExceptions.CustomException;
import systematicTraders.tdt.web.ApiResponse;
import systematicTraders.tdt.exception.ErrorCode;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApiResponse> handleCustomAPIException(CustomException e) {
        ErrorCode errorCode = e.getErrorCode();

        log.error("Custom Exception", e);

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ApiResponse.fail(errorCode));
    }

    @ExceptionHandler
    public ResponseEntity<ApiResponse> handleException(Exception e) {

        log.error("Uncaught Exception", e);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.fail(ErrorCode.INTERNAL_PROBLEM));
    }
}
