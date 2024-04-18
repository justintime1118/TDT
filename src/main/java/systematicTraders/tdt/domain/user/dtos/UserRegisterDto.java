package systematicTraders.tdt.domain.user.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import systematicTraders.tdt.domain.user.User;

@Getter @Setter
@NoArgsConstructor
public class UserRegisterDto {

    private String loginId;
    private String password;
    private String nickname;

    @Builder
    public UserRegisterDto(String loginId, String password, String nickname) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
    }
}
