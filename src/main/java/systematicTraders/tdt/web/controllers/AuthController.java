package systematicTraders.tdt.web.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import systematicTraders.tdt.domain.auth.LoginDto;
import systematicTraders.tdt.domain.auth.AuthService;
import systematicTraders.tdt.exception.customExceptions.InvalidLoginInfoException;
import systematicTraders.tdt.exception.customExceptions.InvalidValueException;
import systematicTraders.tdt.exception.customExceptions.NotExistingSessionExcepion;
import systematicTraders.tdt.web.ApiResponse;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse login(@Valid @RequestBody LoginDto dto, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            throw new InvalidValueException();
        }
        boolean isValidUser = authService.login(dto.getLoginId(), dto.getPassword(), request.getSession());
        if (!isValidUser) {
            throw new InvalidLoginInfoException();
        }

        return ApiResponse.success(isValidUser);
    }

    @PostMapping("/logout")
    public ApiResponse logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new NotExistingSessionExcepion();
        }

        session.invalidate();
        return ApiResponse.success(true);
    }
}
