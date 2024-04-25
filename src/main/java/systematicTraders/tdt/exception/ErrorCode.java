package systematicTraders.tdt.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INVALID_VALUE(BAD_REQUEST, "부적절한 요청값"),
    NOT_AUTHENTICATED(UNAUTHORIZED, "미인증 사용자 요청"),
    INVALID_LOGIN_INFO(NOT_FOUND, "아이디 또는 비밀번호 오류"),
    INTERNAL_PROBLEM(INTERNAL_SERVER_ERROR, "예상치 못한 오류 발생"),
    NOT_EXISITING_SESSION(NOT_FOUND, "존재하지 않는 세션(로그인 되어 있지 않음)"),
    DUPLICATE_LOGIN_ID(FORBIDDEN, "이미 존재하는 ID");

    private final HttpStatus status;
    private final String message;

}
