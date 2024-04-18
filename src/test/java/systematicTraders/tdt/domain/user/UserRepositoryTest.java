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

    @AfterEach
    void clear() {
        userRepository.deleteAll();
    }

    @Test
    void 회원저장_및_딘건조회() {
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
    void 회원정보수정() {
    }

    @Test
    void 다건조회() {
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
