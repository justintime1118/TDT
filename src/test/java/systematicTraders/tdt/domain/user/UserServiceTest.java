package systematicTraders.tdt.domain.user;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import systematicTraders.tdt.domain.user.dtos.UserRegisterDto;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void clear() {
        userRepository.deleteAll();
    }

    @Test
    void 회원가입() {
        //given
        UserRegisterDto userRegisterDto = new UserRegisterDto("userA", "testPassword", "testNickname");

        //when
        Long newUserId = userService.register(userRegisterDto);

        //then
        User savedUser = userRepository.findById(newUserId).get();
        log.info("encryptedPassword={}", savedUser.getEncryptedPassword());
        assertThat(savedUser.getEncryptedPassword()).isNotEqualTo(userRegisterDto.getPassword());
    }

    @Test
    void 회원탈퇴() {
        //given
        UserRegisterDto userRegisterDto = new UserRegisterDto("userA", "testPassword", "testNickname");
        Long userId = userService.register(userRegisterDto);

        //when
        userService.delete(userId);

        //then
        assertThat(userRepository.findById(userId)).isEmpty();
    }
}