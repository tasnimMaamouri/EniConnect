package tn.enicarthage.repository;

import tn.enicarthage.entities.Candidature;
import tn.enicarthage.entities.Etat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidatureRepository extends JpaRepository<Candidature, Long> {
    List<Candidature> findByEtat(Etat etat);
}
