package tn.enicarthage.EniConnect.services;

import org.springframework.web.multipart.MultipartFile;
import tn.enicarthage.EniConnect.entities.Article;
import tn.enicarthage.EniConnect.entities.Post;
import tn.enicarthage.EniConnect.entities.Status;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IPostService {

    List<Post> getAllPosts();

    Optional<Post> getPostById(Long id);

    Post createPost(Post post);

    void deletePostById(Long id);

    Post updatePostById(Long ancienId, Long postId, Post updatedPost);
}