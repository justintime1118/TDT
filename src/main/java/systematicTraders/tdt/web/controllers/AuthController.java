package systematicTraders.tdt.web.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import systematicTraders.tdt.domain.auth.LoginDto;
import systematicTraders.tdt.domain.auth.AuthService;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public Object login(@Validated @RequestBody LoginDto dto, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }
        boolean isValidUser = authService.login(dto.getLoginId(), dto.getPassword(), request.getSession());
        if (!isValidUser) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다");
            return bindingResult.getAllErrors();
        }

        return true;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        authService.logout(request.getSession(false));
        return "session terminated";
    }
}
