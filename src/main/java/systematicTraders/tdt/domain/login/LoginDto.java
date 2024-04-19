package systematicTraders.tdt.domain.login;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter @Setter
@NoArgsConstructor
public class LoginDto {

    @NotBlank
    private String loginId;

    @NotBlank
    @Length(min = 8, max = 24, message = "비밀번호는 8~24자로 입력해주세요")
    private String password;

    @Builder
    public LoginDto(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
