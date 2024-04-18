package systematicTraders.tdt.domain.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import systematicTraders.tdt.domain.user.dtos.UserRegisterDto;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    /**
     * 회원가입
     */
    @PostMapping
    public Long register(@RequestBody UserRegisterDto dto) {
        return userService.register(dto);
    }

    /**
     * 로그인
     */

    /**
     * 회원탈퇴
     */
    @PostMapping("/{userId}")
    public Long delete(@RequestBody UserRegisterDto dto, @PathVariable("userId") Long userId) {
        return userService.delete(userId);
    }

}
