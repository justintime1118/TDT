package systematicTraders.tdt.domain.auth;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import systematicTraders.tdt.domain.user.User;
import systematicTraders.tdt.domain.user.UserRepository;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;

    public boolean login(String loginId, String password, HttpSession session) {

        User foundUser = userRepository.findByLoginId(loginId);

        if (foundUser == null)
            return false;
        if (!User.checkPassword(password, foundUser.getEncryptedPassword())) {
            return false;
        }

        session.setAttribute(SessionConst.LOGIN_USER, foundUser);
        return true;
    }

}
