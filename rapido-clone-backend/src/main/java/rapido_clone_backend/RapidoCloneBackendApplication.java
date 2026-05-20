package rapido_clone_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = "com.rapido")
public class RapidoCloneBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(RapidoCloneBackendApplication.class, args);
	}
}