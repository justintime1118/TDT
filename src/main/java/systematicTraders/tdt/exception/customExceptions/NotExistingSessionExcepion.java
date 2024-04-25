package systematicTraders.tdt.exception.customExceptions;

import systematicTraders.tdt.exception.ErrorCode;

public class NotExistingSessionExcepion extends CustomException {
    public NotExistingSessionExcepion() {
        super(ErrorCode.NOT_EXISITING_SESSION);
    }
}
