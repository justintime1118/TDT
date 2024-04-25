package systematicTraders.tdt.web.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import systematicTraders.tdt.domain.auth.SessionConst;
import systematicTraders.tdt.domain.user.UserService;
import systematicTraders.tdt.domain.user.User;
import systematicTraders.tdt.domain.user.dtos.UserRegisterDto;
import systematicTraders.tdt.domain.user.dtos.UserUpdateDto;
import systematicTraders.tdt.exception.customExceptions.InvalidValueException;
import systematicTraders.tdt.exception.customExceptions.NotAuthenticatedException;
import systematicTraders.tdt.web.ApiResponse;
import systematicTraders.tdt.argumentResolvers.Login;

import static systematicTraders.tdt.web.ApiResponse.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    /**
     * 회원가입
     */
    @PostMapping("/register")
    public ApiResponse register(@Valid @RequestBody UserRegisterDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidValueException();
        }
        Long newUserId = userService.register(dto);
        return success(newUserId);
    }

    /**
     * 회원정보수정
     */
    @PatchMapping("/update")
    public ApiResponse update(@Login User user,
                         @Valid @RequestBody UserUpdateDto dto, BindingResult bindingResult) {
        if (user == null) {
            throw new NotAuthenticatedException();
        }

        if (bindingResult.hasErrors()) {
            throw new InvalidValueException();
        }

        return success(userService.update(user.getId(), dto));
    }

    /**
     * 회원탈퇴
     */
    @DeleteMapping("/delete")
    public ApiResponse delete(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new NotAuthenticatedException();
        }
        User user = (User) session.getAttribute(SessionConst.LOGIN_USER);
        session.invalidate();

        return success(userService.delete(user.getId()));

    }
}
