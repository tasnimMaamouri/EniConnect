package tn.enicarthage.EniConnect.services;

import tn.enicarthage.EniConnect.entities.Article;
import tn.enicarthage.EniConnect.entities.Offre;

import java.util.List;
import java.util.Optional;

public interface IOffreService {

    List<Offre> getAllPosts();

    Optional<Offre> getPostById(Long id);

    Offre createPost(Offre offre);
    List<Offre> getOffresByAncienEtudiantI(String emailInstitutionnelle);
    void deletePostById(Long id);

    Offre updatePostById(Long ancienId, Long postId, Offre updatedOffre);
}