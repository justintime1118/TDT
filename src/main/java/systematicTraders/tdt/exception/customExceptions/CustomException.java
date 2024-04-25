package systematicTraders.tdt.exception.customExceptions;

import lombok.Getter;
import systematicTraders.tdt.exception.ErrorCode;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;

    protected CustomException(ErrorCode errorCode) {
        super();
        this.errorCode = errorCode;
    }
}
