package systematicTraders.tdt.domain.user;

import jakarta.persistence.*;
import lombok.*;
import org.mindrot.jbcrypt.BCrypt;
import systematicTraders.tdt.domain.BaseTimeEntity;

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
}
