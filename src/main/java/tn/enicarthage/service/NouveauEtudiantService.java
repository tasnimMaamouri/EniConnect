package tn.enicarthage.service;

import tn.enicarthage.entities.NouveauEtudiant;

import java.util.List;

public interface NouveauEtudiantService {
    void AffecterCandidatureAEtudiant(Long IdC,Long IdE);
    byte[] getCVByEtudiantId(Long etudiantId);
    NouveauEtudiant createNouveauEtudiant(NouveauEtudiant Etd);

    NouveauEtudiant getEtudiantById(Long etudiantId);

    void updateEtudiant(NouveauEtudiant a, Long etudiantId);

    List<NouveauEtudiant> getAllEtudiants();

    NouveauEtudiant getNouveauEtudiant(Long id);

    NouveauEtudiant saveNouveauEtudiant(NouveauEtudiant etd);

    NouveauEtudiant updateNouveauEtudiant(NouveauEtudiant etd);

    void deleteNouveauEtudiantById(Long id);
}
