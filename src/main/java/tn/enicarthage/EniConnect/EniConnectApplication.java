package tn.enicarthage.EniConnect;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.enicarthage.EniConnect.controllers.AncienEtudiantController;
import tn.enicarthage.EniConnect.entities.AncienEtudiant;

@SpringBootApplication
public class EniConnectApplication {

	public static void main(String[] args) {
		PropertyConfigurator.configure("src/main/resources/log4j.properties");

		ConfigurableApplicationContext context = SpringApplication.run(EniConnectApplication.class, args);

		// Création d'un nouvel ancien étudiant
		AncienEtudiant ancienEtudiant = new AncienEtudiant();
		ancienEtudiant.setNomPrenom("Ahmed MOHAMMED");
		ancienEtudiant.setEmailInstitutionnelle("ahmed.mohammed@example.com");

		AncienEtudiantController ancienEtudiantController = context.getBean(AncienEtudiantController.class);
		ResponseEntity<AncienEtudiant> response = ancienEtudiantController.addAncienEtudiant(ancienEtudiant);

		// Vérification de la réponse
		if (response.getStatusCode() == HttpStatus.CREATED) {
			AncienEtudiant newAncienEtudiant = response.getBody();
			System.out.println("Nouvel ancien étudiant ajouté avec succès : " + newAncienEtudiant);
		} else {
			System.out.println("Erreur lors de l'ajout de l'ancien étudiant : " + response.getStatusCodeValue());
		}
	}
}
