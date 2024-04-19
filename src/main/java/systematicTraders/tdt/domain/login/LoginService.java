package systematicTraders.tdt.domain.login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import systematicTraders.tdt.domain.user.User;
import systematicTraders.tdt.domain.user.UserRepository;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginService {

    private final UserRepository userRepository;

    public boolean login(String loginId, String password) {
        User result = userRepository.findByLoginId(loginId);
        if (result == null)
            return false;

        return User.checkPassword(password, result.getEncryptedPassword());
    }
}
