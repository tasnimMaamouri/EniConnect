package tn.enicarthage.EniConnect;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.enicarthage.EniConnect.entities.Candidature;
import tn.enicarthage.EniConnect.entities.Etat;
import tn.enicarthage.EniConnect.entities.NouveauEtudiant;
import tn.enicarthage.EniConnect.repositories.CandidatureRepository;
import tn.enicarthage.EniConnect.repositories.NouveauEtudiantRepository;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class EniConnectApplicationTests {
	@Autowired
	private CandidatureRepository cdrepo;
	@Autowired
	private NouveauEtudiantRepository etdrepo;
	@Test
	void contextLoads() {
	}


	@Test
	public void testcreateNouveauEtudiant() {
		LocalDate date = LocalDate.of(2001, 4, 1);
		NouveauEtudiant etd = new NouveauEtudiant("BenAissa", "manel", date, "Tunis", "Informatique", "manel@gmail.com", "benaissa.manel@enicar.ucar.tn");
		etdrepo.save(etd);
	}
	@Test
	public void testcreateCandidature(){
		LocalDate aujourdhui = LocalDate.now();

		Candidature cd = new Candidature(aujourdhui, Etat.EnAttente);
		cdrepo.save(cd);
	}
	@Test
	public void testfindCandidatureById(){
		Candidature candidature = cdrepo.findById(1L).orElse(null);
		if (candidature != null) {
			NouveauEtudiant etudiant = candidature.getNouveauEtudiant();
			if (etudiant != null) {
				System.out.println("Candidature de l'étudiant : " + etudiant.getNom() + " " + etudiant.getPrenom());


			} else {
				System.out.println("Aucun étudiant associé à cette candidature.");
			}
		} else {
			System.out.println("Candidature non trouvée.");
		}
	}
	@Test
	public void testFindEtudiantById() {
		NouveauEtudiant etudiant = etdrepo.findById(2L).orElse(null);
		if (etudiant != null) {
			System.out.println("Étudiant trouvé : " + etudiant.getNom() + " " + etudiant.getPrenom());
		} else {
			System.out.println("Aucun étudiant trouvé avec cet ID.");
		}
	}

	

}
