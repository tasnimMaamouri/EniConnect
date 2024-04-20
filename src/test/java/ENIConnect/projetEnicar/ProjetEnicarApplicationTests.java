package ENIConnect.projetEnicar;

import ENIConnect.projetEnicar.entities.Candidature;
import ENIConnect.projetEnicar.entities.NouveauEtudiant;
import ENIConnect.projetEnicar.repository.CandidatureRepository;
import ENIConnect.projetEnicar.repository.NouveauEtudiantRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static ENIConnect.projetEnicar.entities.Etat.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ProjetEnicarApplicationTests {
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

		Candidature cd = new Candidature(aujourdhui, EnAttente);
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
	@Test
	public void testUpdateCandidature() {
		NouveauEtudiant etudiant = etdrepo.findById(1L).orElse(null);
		if (etudiant != null) {
			for (Candidature candidature : etudiant.getCandidatures()) {
				// Accessing the candidature within the active session
				candidature.setEtat(EnAttente);
				cdrepo.save(candidature);
			}
			System.out.println(etudiant);
		} else {
			System.out.println("Étudiant non trouvé.");
		}
	}
	@Test
	public void testdeleteEtudiant(){
		etdrepo.deleteById(7L);
	}
	@Test
	public void testfindByEtat(){
		List <Candidature> cds = cdrepo.findByEtat(EnAttente);
		for (Candidature cd:cds)
			System.out.println(cd);
	}






}
