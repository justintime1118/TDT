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

    @PostMapping
    public UserRegisterDto register(@RequestBody UserRegisterDto dto) {
        return userService.register(dto);
    }
}
