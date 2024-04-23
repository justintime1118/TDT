package systematicTraders.tdt.web.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import systematicTraders.tdt.domain.user.AccountService;
import systematicTraders.tdt.domain.user.dtos.UserRegisterDto;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/accounts")
@RestController
public class AccountController {

    private final AccountService accountService;

    /**
     * 회원가입
     */
    @PostMapping
    public Object register(@Validated @RequestBody UserRegisterDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }
        return accountService.register(dto);
    }

    /**
     * 회원탈퇴
     */
    @DeleteMapping("/{loginId}")
    public Object delete(HttpServletRequest request, @PathVariable("loginId") Long loginId) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return false;
        }
        session.invalidate();

        return accountService.delete(loginId);

    }

}
