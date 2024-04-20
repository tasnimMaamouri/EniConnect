package tn.enicarthage.EniConnect.repositories;

import tn.enicarthage.EniConnect.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ArticleRepository extends JpaRepository<Article, Long>{
    List<Article> findByAncienEtudiantEmailInstitutionnelle(String emailAncienEtudiant);
    List<Article> findByAncienEtudiantEmailPersonnelle(String emailAncienEtudiant);
}
