package systematicTraders.tdt.domain.login;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import systematicTraders.tdt.domain.user.UserService;
import systematicTraders.tdt.domain.user.dtos.UserRegisterDto;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@Transactional
class LoginServiceTest {

    @Autowired LoginService loginService;
    @Autowired UserService userService;

    @Test
    void 로그인() {
        //given
        UserRegisterDto user = UserRegisterDto.builder()
                .loginId("testId")
                .password("testPw")
                .build();

        userService.register(user);

        //when
        boolean valid = loginService.login(user.getLoginId(), user.getPassword());
        boolean invalidId = loginService.login("invalidId", "testPw");
        boolean invalidPw = loginService.login("testId", "invalidPw");

        //then
        assertThat(valid).isTrue();
        assertThat(invalidId).isFalse();
        assertThat(invalidPw).isFalse();

    }
}