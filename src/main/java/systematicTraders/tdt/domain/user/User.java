package systematicTraders.tdt.domain.user;

import jakarta.persistence.*;
import lombok.*;
import systematicTraders.tdt.domain.BaseTimeEntity;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TDT_USERS")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String encryptedPassword;
    private String nickname;
//    private String profilePhoto;

    @Builder
    public User(String username, String encryptedPassword, String nickname) {
        this.username = username;
        this.encryptedPassword = encryptedPassword;
        this.nickname = nickname;
    }
}
