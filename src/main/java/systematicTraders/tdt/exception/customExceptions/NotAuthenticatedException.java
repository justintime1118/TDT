package systematicTraders.tdt.exception.customExceptions;

import systematicTraders.tdt.exception.ErrorCode;

public class NotAuthenticatedException extends CustomException {
    public NotAuthenticatedException() {
        super(ErrorCode.NOT_AUTHENTICATED);
    }
}
