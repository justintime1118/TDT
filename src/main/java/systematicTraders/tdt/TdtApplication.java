package systematicTraders.tdt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import systematicTraders.tdt.domain.user.UserRepository;

@EnableJpaAuditing
@SpringBootApplication
public class TdtApplication {

	public static void main(String[] args) {
		SpringApplication.run(TdtApplication.class, args);
	}

}
