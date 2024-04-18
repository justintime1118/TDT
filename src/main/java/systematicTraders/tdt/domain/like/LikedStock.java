package systematicTraders.tdt.domain.like;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import systematicTraders.tdt.domain.BaseTimeEntity;
import systematicTraders.tdt.domain.stock.Stock;
import systematicTraders.tdt.domain.user.User;


@Getter
@NoArgsConstructor
@Entity
public class LikedStock extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Builder
    public LikedStock(Long id, User user, Stock stock) {
        this.id = id;
        this.user = user;
        this.stock = stock;
    }
}
