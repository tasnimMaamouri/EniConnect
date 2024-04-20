package tn.enicarthage.EniConnect.services;

import org.springframework.web.multipart.MultipartFile;
import tn.enicarthage.EniConnect.entities.Article;
import tn.enicarthage.EniConnect.entities.Post;
import tn.enicarthage.EniConnect.entities.Status;

import java.io.IOException;
import java.util.List;

public interface IPostService {

    List<Post> getPostsByAncienEtudiant(String emailPersonnelle);

    Post getPostByID(Long id);

    List<Post> getAllPosts();

    Post savePost(Post post);

    void deletePost(Long id);

    Post updatePost(Post post, Long id);
    Post uploadPost(MultipartFile pdfFile, Long ownerID, String title, String content, Status status) throws IOException;
}
