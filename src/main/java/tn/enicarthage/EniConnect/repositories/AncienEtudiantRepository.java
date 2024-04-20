package tn.enicarthage.EniConnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.enicarthage.EniConnect.entities.AncienEtudiant;

public interface AncienEtudiantRepository extends JpaRepository<AncienEtudiant, Long> {
    AncienEtudiant findByEmailInstitutionnelle(String email);
    AncienEtudiant findByEmailInstitutionnelleAndIdNot(String email, Long idAncienEtudiant);
}
