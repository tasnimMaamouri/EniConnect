package tn.enicarthage.EniConnect.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.enicarthage.EniConnect.Entities.Post;

public interface PostRepo extends JpaRepository<Post, Long> {
}
