package systematicTraders.tdt.domain.user;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    void save_findById() {
        //given
        User user = User.builder()
                .loginId("test id")
                .encryptedPassword("test pw")
                .nickname("test nickname")
                .build();

        //when
        User savedUser = userRepository.save(user);
        User foundUser = userRepository.findById(savedUser.getId()).get();

        //then
        assertThat(foundUser).isSameAs(savedUser);
        log.info("createdAt: {}", foundUser.getCreatedDate());

    }

    @Test
    void findByLoginId() {
        //given
        User user = User.builder()
                .loginId("testId")
                .encryptedPassword("testPw")
                .nickname("testNickname")
                .build();

        //when
        User savedUser = userRepository.save(user);
        User validUser = userRepository.findByLoginId(savedUser.getLoginId());
        User invalidUser = userRepository.findByLoginId("존재하지않는ID");

        //then
        assertThat(validUser).isSameAs(savedUser);
        assertThat(invalidUser).isNull();

    }

    @Test
    void findAll() {
        //given

        for (int i = 0; i < 5; i++) {
            User user = User.builder()
                    .loginId("test loginId" + " " + i)
                    .encryptedPassword("test pw" + " " + i)
                    .nickname("test nickname" + " " + i)
                    .build();
            userRepository.save(user);
        }

        //when
        List<User> users = userRepository.findAll();

        //then
        assertThat(users.size()).isEqualTo(5);
    }
}
