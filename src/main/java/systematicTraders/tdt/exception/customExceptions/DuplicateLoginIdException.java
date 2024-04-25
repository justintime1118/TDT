package systematicTraders.tdt.exception.customExceptions;

import systematicTraders.tdt.exception.ErrorCode;

public class DuplicateLoginIdException extends CustomException {

    public DuplicateLoginIdException() {
        super(ErrorCode.DUPLICATE_LOGIN_ID);
    }
}
