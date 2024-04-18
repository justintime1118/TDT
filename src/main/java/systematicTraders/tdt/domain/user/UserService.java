package systematicTraders.tdt.domain.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import systematicTraders.tdt.domain.user.dtos.UserRegisterDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * 회원가입
     */
    @Transactional
    public Long register(UserRegisterDto dto) {
        String encryptedPassword = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
        User newUser = User.builder()
                .loginId(dto.getLoginId())
                .encryptedPassword(encryptedPassword)
                .nickname(dto.getNickname())
                .build();

        User savedUser = userRepository.save(newUser);

        return savedUser.getId();
    }

    public boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    public Long delete(Long userId) {
        userRepository.deleteById(userId);
        return userId;
    }
}
