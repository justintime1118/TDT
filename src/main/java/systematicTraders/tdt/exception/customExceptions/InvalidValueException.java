package systematicTraders.tdt.exception.customExceptions;

import systematicTraders.tdt.exception.ErrorCode;

public class InvalidValueException extends CustomException {

    public InvalidValueException() {
        super(ErrorCode.INVALID_VALUE);
    }
}
