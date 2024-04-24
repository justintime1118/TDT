package systematicTraders.tdt.web.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import systematicTraders.tdt.domain.auth.SessionConst;
import systematicTraders.tdt.domain.user.AccountService;
import systematicTraders.tdt.domain.user.User;
import systematicTraders.tdt.domain.user.dtos.UserRegisterDto;
import systematicTraders.tdt.domain.user.dtos.UserUpdateDto;
import systematicTraders.tdt.web.argumentResolvers.Login;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/accounts")
@RestController
public class AccountController {

    private final AccountService accountService;

    /**
     * 회원가입
     */
    @PostMapping("/register")
    public Object register(@Valid @RequestBody UserRegisterDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }
        return accountService.register(dto);
    }

    /**
     * 회원정보수정
     */

    @PatchMapping("/update")
    public Object update(@Login User user,
                         @Valid @RequestBody UserUpdateDto dto, BindingResult bindingResult) {
        if (user == null) {
            bindingResult.reject("unauthenticatedUser");
            return bindingResult.getAllErrors();
        }

        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }

        return accountService.update(user.getId(), dto);
    }

    /**
     * 회원탈퇴
     */
    @DeleteMapping("/delete")
    public void delete(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        User user = (User) session.getAttribute(SessionConst.LOGIN_USER);
        session.invalidate();

        accountService.delete(user.getId());
    }

}
