package systematicTraders.tdt.domain.portfolioItem;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import systematicTraders.tdt.domain.BaseTimeEntity;
import systematicTraders.tdt.domain.portfolio.Portfolio;
import systematicTraders.tdt.domain.stock.Stock;
import systematicTraders.tdt.domain.user.User;

@Getter
@NoArgsConstructor
@Entity
public class PortfolioItem extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Builder
    public PortfolioItem(Long id, Portfolio portfolio, User user, Stock stock) {
        this.id = id;
        this.portfolio = portfolio;
        this.user = user;
        this.stock = stock;
    }
}
