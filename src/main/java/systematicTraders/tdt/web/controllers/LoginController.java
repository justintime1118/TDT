package systematicTraders.tdt.web.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import systematicTraders.tdt.domain.login.LoginDto;
import systematicTraders.tdt.domain.login.LoginService;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/login")
@RestController
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public Object login(@Validated @RequestBody LoginDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }
        return loginService.login(dto.getLoginId(), dto.getPassword());
    }
}
