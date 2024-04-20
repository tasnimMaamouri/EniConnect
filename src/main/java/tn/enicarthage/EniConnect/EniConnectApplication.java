package tn.enicarthage.EniConnect;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tn.enicarthage.EniConnect.Entities.Admin;
import tn.enicarthage.EniConnect.Entities.Post;
import tn.enicarthage.EniConnect.Entities.Status;
import tn.enicarthage.EniConnect.Repositories.AdminRepo;
import tn.enicarthage.EniConnect.Services.PostService;

import java.time.LocalDate;
@SpringBootApplication
public class EniConnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(EniConnectApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AdminRepo adminRepository, PostService postService) {
		return args -> {
			// Create an Admin
			Admin admin = new Admin();
			admin.setId(1L);
			admin.setFirstname("Jihen");
			admin.setLastname("BOUKHADHRA");
			LocalDate dateOfBirth = LocalDate.of(2001, 1, 10);
			admin.setDateOfBirth(dateOfBirth);
			admin.setEmail("jihen@gmail.com");
			admin.setPassword("jiji");
			adminRepository.save(admin);

			// Create a new Post
			Post post = new Post();
			post.setTitle("Opportunité d'embauche");
			post.setStatus(Status.Draft);


		};
	}
}

