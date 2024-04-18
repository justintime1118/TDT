package systematicTraders.tdt.domain.portfolio;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import systematicTraders.tdt.domain.BaseTimeEntity;
import systematicTraders.tdt.domain.user.User;

@Getter
@NoArgsConstructor
@Entity
public class Portfolio extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Portfolio(Long id, User user) {
        this.id = id;
        this.user = user;
    }
}
