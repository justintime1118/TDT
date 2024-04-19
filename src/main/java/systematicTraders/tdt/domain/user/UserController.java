package systematicTraders.tdt.domain.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import systematicTraders.tdt.domain.user.dtos.UserRegisterDto;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 회원가입
     */
    @PostMapping
    public Object register(@Validated @RequestBody UserRegisterDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }
        return userService.register(dto);
    }

    /**
     * 로그인
     */

    /**
     * 회원탈퇴
     */
    @PostMapping("/{userId}")
    public Long delete(@PathVariable("userId") Long userId) {
        return userService.delete(userId);
    }

}
