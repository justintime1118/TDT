package systematicTraders.tdt.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import systematicTraders.tdt.exception.ErrorCode;
import systematicTraders.tdt.exception.ErrorDetail;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private ErrorDetail error;

    public static <T>ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null);
    }

    public static <T> ApiResponse<T> fail(ErrorCode errorCode) {
        return new ApiResponse<>(false, null, ErrorDetail.of(errorCode));
    }
}