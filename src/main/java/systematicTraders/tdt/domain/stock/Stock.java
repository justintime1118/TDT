package systematicTraders.tdt.domain.stock;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import systematicTraders.tdt.domain.BaseTimeEntity;

@Getter
@NoArgsConstructor
@Entity
public class Stock extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Builder
    public Stock(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}

