package systematicTraders.tdt.domain.user.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import systematicTraders.tdt.domain.user.User;

@Getter
@NoArgsConstructor
public class UserRegisterDto {

    private String username;
    private String password;
    private String nickname;

    @Builder
    public UserRegisterDto(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    public User toEntity() {
        return User.builder()
                .username(username)
                .encryptedPassword(password)
                .nickname(nickname)
                .build();
    }
}
