package systematicTraders.tdt.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorDetail {

    private final String type;
    private final String message;

    public static ErrorDetail of(ErrorCode error) {
        return new ErrorDetail(error.name(), error.getMessage());
    }
}
