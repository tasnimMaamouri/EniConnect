package tn.enicarthage.EniConnect.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.enicarthage.EniConnect.repositories.AncienEtudiantRepository;
import tn.enicarthage.EniConnect.entities.AncienEtudiant;
import tn.enicarthage.EniConnect.entities.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IAncienEtudiantServiceImpl implements IAncienEtudiantService {
    @Autowired
    private AncienEtudiantRepository ancienEtudiantR;
    @Override
    public AncienEtudiant addAncienEtudiant(AncienEtudiant ancienEtudiant) {
        return ancienEtudiantR.save(ancienEtudiant);
    }

    @Override
    public List<AncienEtudiant> getAllAncienEtudiant() {
        List<AncienEtudiant> ancienEtudiants =new ArrayList<AncienEtudiant>();
        for(AncienEtudiant ancienEtudiant:ancienEtudiantR.findAll()) {
            ancienEtudiants.add(ancienEtudiant);
        }

        return ancienEtudiants;     }

    @Override
    public AncienEtudiant getAncienEtudiantById(Long id) {
        if(ancienEtudiantR.findById(id).isPresent()) {
            return ancienEtudiantR.findById(id).get();
        }
        else {
            return null;
        }    }

    @Override
    public AncienEtudiant updateAncienEtudiant(AncienEtudiant ancienEtudiant, Long id) {
        ancienEtudiant.setId(id);

        return ancienEtudiantR.save(ancienEtudiant);    }

    @Override
    public void deleteAncienEtudiant(Long id) {
        AncienEtudiant ancienEtudiant = ancienEtudiantR.findById(id).get();
        for (Article article : ancienEtudiant.getArticles()) {
            article.setAncienEtudiant(null);
        }

        ancienEtudiantR.deleteById(id);
    }

    @Override
    public AncienEtudiant getAncienEtudiantByEmail(String email) {
        Optional<AncienEtudiant> resultat = Optional.ofNullable(ancienEtudiantR.findByEmailInstitutionnelle(email));
        if (resultat.isPresent()) {
            return resultat.get();
        }else {
            return null;
        }    }

    @Override
    public AncienEtudiant getAncienEtudiantByEmailAndNotId(String email, Long idAncienEtudiant) {
        Optional<AncienEtudiant> resultat = Optional.ofNullable(ancienEtudiantR.findByEmailInstitutionnelleAndIdNot(email,idAncienEtudiant));
        if (resultat.isPresent()) {
            return resultat.get();
        }else {
            return null;
        }    }
}
