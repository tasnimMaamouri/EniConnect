package tn.enicarthage.EniConnect.service;

import tn.enicarthage.EniConnect.entities.Candidature;

import java.util.List;

public interface CandidatureService {
    Candidature createNouvelleCandidature(Candidature candidature);

    List<Candidature> getAllCandidatures();

    Candidature getCAndidature(Long id);

    Candidature saveNouvelleCandidature(Candidature cd);

    Candidature updateCandidature(Candidature cd);

    void deleteCandidatureById(Long id);
}
