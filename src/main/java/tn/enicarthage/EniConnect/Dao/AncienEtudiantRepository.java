package tn.enicarthage.EniConnect.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.enicarthage.EniConnect.Entity.AncienEtudiant;
import tn.enicarthage.EniConnect.Entity.Article;

public interface AncienEtudiantRepository extends JpaRepository<AncienEtudiant, Long> {
    AncienEtudiant findByEmailInstitutionnelle(String email);
    AncienEtudiant findByEmailInstitutionnelleAndIdNot(String email, Long idAncienEtudiant);
}
