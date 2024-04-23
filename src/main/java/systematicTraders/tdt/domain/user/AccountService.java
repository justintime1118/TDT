package systematicTraders.tdt.domain.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import systematicTraders.tdt.domain.user.dtos.UserRegisterDto;
import systematicTraders.tdt.domain.user.dtos.UserUpdateDto;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final UserRepository userRepository;

    /**
     * 회원가입
     * @param dto
     * @return 가입된 User
     */
    @Transactional
    public User register(UserRegisterDto dto) {
        String encryptedPassword = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
        User newUser = User.builder()
                .loginId(dto.getLoginId())
                .encryptedPassword(encryptedPassword)
                .nickname(dto.getNickname())
                .build();

        return userRepository.save(newUser);
    }

    /**
     * 회원정보변경
     *
     * @param id
     * @param dto
     * @return 수정된 User
     */
    @Transactional
    public User update(Long id, UserUpdateDto dto) {
        Optional<User> result = userRepository.findById(id);
        if (result.isEmpty()) {
            return null;
        }

        User foundUser = result.get();
        foundUser.update(dto);
        return foundUser;
    }


    /**
     * 회원탈퇴
     * @param userId
     * @return 삭제된 userId
     */
    @Transactional
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }


}
