package systematicTraders.tdt.exception.customExceptions;


import systematicTraders.tdt.exception.ErrorCode;


public class InvalidLoginInfoException extends CustomException {

    public InvalidLoginInfoException() {
        super(ErrorCode.INVALID_LOGIN_INFO);
    }
}
