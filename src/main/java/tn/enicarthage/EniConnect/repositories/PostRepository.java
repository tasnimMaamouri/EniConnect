package tn.enicarthage.EniConnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.enicarthage.EniConnect.entities.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    static Post savePost(Post existingPost) {
        return existingPost;
    }

    List<Post> findByAncienEtudiantEmailPersonnelle(String emailAncienEtudiant);
    }
