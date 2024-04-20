package ENIConnect.projetEnicar.repository;

import ENIConnect.projetEnicar.entities.Candidature;
import ENIConnect.projetEnicar.entities.Etat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidatureRepository extends JpaRepository<Candidature, Long> {
    List<Candidature> findByEtat(Etat etat);
}
