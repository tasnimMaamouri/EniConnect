package tn.enicarthage.EniConnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.enicarthage.EniConnect.entities.Candidature;
import tn.enicarthage.EniConnect.entities.Etat;

import java.util.List;

public interface CandidatureRepository extends JpaRepository<Candidature, Long> {
    List<Candidature> findByEtat(Etat etat);
}
