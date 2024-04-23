package tn.enicarthage.EniConnect.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.enicarthage.EniConnect.entities.AncienEtudiant;
import tn.enicarthage.EniConnect.entities.Article;
import tn.enicarthage.EniConnect.entities.Offre;
import tn.enicarthage.EniConnect.repositories.OffreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class IOffreServiceImpl implements IOffreService {

    private final OffreRepository offreRepository;
    private final IAncienEtudiantService ancienEtudiantService;

    @Autowired
    public IOffreServiceImpl(OffreRepository offreRepository, IAncienEtudiantService ancienEtudiantService) {
        this.offreRepository = offreRepository;
        this.ancienEtudiantService = ancienEtudiantService;
    }

    @Override
    public List<Offre> getAllPosts() {
        return offreRepository.findAllByOrderByCreatedDateDesc();
    }

    @Override
    public Optional<Offre> getPostById(Long id) {
        return offreRepository.findById(id);
    }

    @Override
    public Offre createPost(Offre offre) {
        return offreRepository.save(offre);
    }

    @Override
    public List<Offre> getOffresByAncienEtudiantI(String emailInstitutionnelle) {
        List<Offre> Offres=new ArrayList<Offre>();
        for(Offre offre:offreRepository.findByAncienEtudiantEmailInstitutionnelle(emailInstitutionnelle)) {
            Offres.add(offre);
        }

        return Offres;
    }

    @Override
    public void deletePostById(Long id) {
        offreRepository.deleteById(id);
    }

    @Override
    public Offre updatePostById(Long ancienId, Long postId, Offre updatedOffre) {
        AncienEtudiant ancienEtudiant = ancienEtudiantService.getAncienEtudiantById(ancienId);
        if (ancienEtudiant == null) {
            throw new IllegalArgumentException("Ancien Etudiant with ID " + ancienId + " not found.");
        }
        Offre offre = getPostById(postId).orElseThrow(() -> new IllegalArgumentException("Offre with ID " + postId + " not found."));
        if (!offre.getAncienEtudiant().equals(ancienEtudiant)) {
            throw new IllegalArgumentException("Ancien Etudiant with ID " + ancienId + " does not have permission to update offre with ID " + postId);
        }
        updatedOffre.setId(postId);
        return offreRepository.save(updatedOffre);
    }
}
