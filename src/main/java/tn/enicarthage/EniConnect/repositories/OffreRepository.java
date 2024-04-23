package tn.enicarthage.EniConnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.enicarthage.EniConnect.entities.Article;
import tn.enicarthage.EniConnect.entities.Offre;

import java.util.List;

@Repository
public interface OffreRepository extends JpaRepository<Offre, Long> {
    List<Offre> findByAncienEtudiantEmailInstitutionnelle(String emailAncienEtudiant);
    List<Offre> findByAncienEtudiantEmailPersonnelle(String emailAncienEtudiant);
    List<Offre> findAllByOrderByCreatedDateDesc();
}
