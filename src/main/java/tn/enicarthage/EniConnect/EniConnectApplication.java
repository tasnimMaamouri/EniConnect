package tn.enicarthage.EniConnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
@EnableJpaAuditing(auditorAwareRef="auditorAware")
@SpringBootApplication
public class EniConnectApplication {
	@Bean
	public AuditorAware<String> auditorAware() {
		return new AuditorAware<String>() {

			@Override
			public Optional<String> getCurrentAuditor() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(EniConnectApplication.class, args);
	}

}
