package tn.enicarthage.EniConnect.services;

import tn.enicarthage.EniConnect.entities.NouveauEtudiant;

import java.util.List;

public interface INouveauEtudiantService {
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
