package systematicTraders.tdt.domain.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import systematicTraders.tdt.domain.user.dtos.UserRegisterDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserRegisterDto register(UserRegisterDto dto) {
        User user = dto.toEntity();
        String password = dto.getPassword();

        //패스워드 암호화
        user.setEncryptedPassword("암호화된 패스워드");
        userRepository.save(user);

        return dto;
    }
}
