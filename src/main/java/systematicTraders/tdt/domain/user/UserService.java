package systematicTraders.tdt.domain.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import systematicTraders.tdt.domain.user.dtos.UserRegisterDto;
import systematicTraders.tdt.domain.user.dtos.UserUpdateDto;
import systematicTraders.tdt.exception.customExceptions.DuplicateLoginIdException;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * 회원가입
     * @return 가입된 User의 id
     */
    @Transactional
    public Long register(UserRegisterDto dto) {
        //loginId 중복체크
        User preExisitingUser = userRepository.findByLoginId(dto.getLoginId());
        if (preExisitingUser != null) {
            throw new DuplicateLoginIdException();
        }

        String encryptedPassword = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
        User newUser = User.builder()
                .loginId(dto.getLoginId())
                .encryptedPassword(encryptedPassword)
                .nickname(dto.getNickname())
                .build();

        userRepository.save(newUser);

        return newUser.getId();
    }

    /**
     * 회원정보변경
     * @return 수정된 User
     */
    @Transactional
    public Long update(Long id, UserUpdateDto dto) {
        Optional<User> result = userRepository.findById(id);
        if (result.isEmpty()) {
            return null;
        }

        User foundUser = result.get();
        foundUser.update(dto);
        return foundUser.getId();
    }


    /**
     * 회원탈퇴
     * @return 삭제된 userId
     */
    @Transactional
    public Long delete(Long userId) {
        userRepository.deleteById(userId);
        return userId;
    }


}
