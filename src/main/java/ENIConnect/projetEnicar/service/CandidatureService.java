package ENIConnect.projetEnicar.service;

import ENIConnect.projetEnicar.entities.Candidature;
import ENIConnect.projetEnicar.entities.NouveauEtudiant;

import java.util.List;

public interface CandidatureService {
    Candidature createNouvelleCandidature(Candidature candidature);

    List<Candidature> getAllCandidatures();

    Candidature getCAndidature(Long id);

    Candidature saveNouvelleCandidature(Candidature cd);

    Candidature updateCandidature(Candidature cd);

    void deleteCandidatureById(Long id);
}
