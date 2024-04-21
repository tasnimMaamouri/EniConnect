package tn.enicarthage.EniConnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.enicarthage.EniConnect.entities.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {


    List<Post> findByAncienEtudiantEmailPersonnelle(String emailAncienEtudiant);
    }
