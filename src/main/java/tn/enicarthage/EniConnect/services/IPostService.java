package tn.enicarthage.EniConnect.services;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import tn.enicarthage.EniConnect.entities.Post;
import tn.enicarthage.EniConnect.entities.Status;

import java.util.List;
import java.util.Optional;


public interface IPostService {

    List<Post> getAllPosts();

    Optional<Post> getPostById(Long id);

    Post createPost(Post post);

    void deletePostById(Long id);

    Post updatePostById(Long ancienId, Long postId, Post updatedPost);
}