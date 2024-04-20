package tn.enicarthage.EniConnect.services;

import tn.enicarthage.EniConnect.entities.AncienEtudiant;

import java.util.List;

public interface IAncienEtudiant {
    AncienEtudiant addAncienEtudiant(AncienEtudiant ancienEtudiant);
    List<AncienEtudiant> getAllAncienEtudiant();
    AncienEtudiant getAncienEtudiantById(Long id);
    AncienEtudiant updateAncienEtudiant(AncienEtudiant ancienEtudiant, Long id);
    void deleteAncienEtudiant(Long id);
    AncienEtudiant getAncienEtudiantByEmail(String email);
    AncienEtudiant getAncienEtudiantByEmailAndNotId(String email, Long idAncienEtudiant);
}
