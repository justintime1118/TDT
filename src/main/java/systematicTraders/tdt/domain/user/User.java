package systematicTraders.tdt.domain.user;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.mindrot.jbcrypt.BCrypt;
import systematicTraders.tdt.domain.BaseTimeEntity;
import systematicTraders.tdt.domain.user.dtos.UserUpdateDto;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String loginId;

    @Column(nullable = false)
    private String encryptedPassword;

    @Length(max = 20)
    private String nickname;
//    private String profilePhoto;


    @Builder
    public User(Long id, String loginId, String encryptedPassword, String nickname) {
        this.id = id;
        this.loginId = loginId;
        this.encryptedPassword = encryptedPassword;
        this.nickname = nickname;
    }

    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    public void update(UserUpdateDto dto) {
        String encryptedPassword = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
        this.encryptedPassword = encryptedPassword;

        this.nickname = dto.getNickname();
    }
}
