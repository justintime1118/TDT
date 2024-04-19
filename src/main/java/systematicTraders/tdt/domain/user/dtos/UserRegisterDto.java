package systematicTraders.tdt.domain.user.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter @Setter
@NoArgsConstructor
public class UserRegisterDto {

    @NotBlank
    private String loginId;

    @NotBlank
    @Length(min = 8, max = 24, message = "비밀번호는 8~24자로 입력해주세요")
    private String password;

    private String nickname;

    @Builder
    public UserRegisterDto(String loginId, String password, String nickname) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
    }
}
