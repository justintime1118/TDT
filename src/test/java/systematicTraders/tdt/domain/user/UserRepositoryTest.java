package systematicTraders.tdt.domain.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import systematicTraders.tdt.domain.user.dtos.UserUpdateDto;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired UserRepository userRepository;

    @Test
    void saveAndFind() {
        //given
        User user = User.builder()
                .loginId("testId")
                .encryptedPassword("testPw")
                .nickname("testNickname")
                .build();

        //when
        User savedUser = userRepository.save(user);
        User foundUser = userRepository.findByLoginId("testId");

        //then
        assertThat(foundUser).isSameAs(savedUser);

    }

    @Test
    void update() {
        //given
        User user = User.builder()
                .loginId("testId")
                .encryptedPassword("testPw")
                .nickname("testNickname")
                .build();
        userRepository.save(user);


        //when
        user.update(UserUpdateDto.builder()
                    .nickname("updatedNickname")
                    .build()
        );

        User foundUser = userRepository.findByLoginId("testId");

        //then
        assertThat(foundUser.getNickname()).isEqualTo(user.getNickname());
    }

    @Test
    void delete() {
        //given
        User user = User.builder()
                .loginId("testId")
                .encryptedPassword("testPw")
                .nickname("testNickname")
                .build();
        userRepository.save(user);

        //when
        userRepository.deleteById(user.getId());
        User deletedUser = userRepository.findByLoginId("testId");

        //then
        assertThat(deletedUser).isNull();

    }
}