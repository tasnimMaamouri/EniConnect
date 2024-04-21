package tn.enicarthage.EniConnect.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.enicarthage.EniConnect.entities.AncienEtudiant;
import tn.enicarthage.EniConnect.entities.Post;
import tn.enicarthage.EniConnect.entities.Status;
import tn.enicarthage.EniConnect.repositories.AncienEtudiantRepository;
import tn.enicarthage.EniConnect.repositories.PostRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class IPostServiceImpl implements IPostService {

    private final PostRepository postRepository;
    private final IAncienEtudiantService ancienEtudiantService;

    @Autowired
    public IPostServiceImpl(PostRepository postRepository, IAncienEtudiantService ancienEtudiantService) {
        this.postRepository = postRepository;
        this.ancienEtudiantService = ancienEtudiantService;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Post updatePostById(Long ancienId, Long postId, Post updatedPost) {
        AncienEtudiant ancienEtudiant = ancienEtudiantService.getAncienEtudiantById(ancienId);
        if (ancienEtudiant == null) {
            throw new IllegalArgumentException("Ancien Etudiant with ID " + ancienId + " not found.");
        }
        Post post = getPostById(postId).orElseThrow(() -> new IllegalArgumentException("Post with ID " + postId + " not found."));
        if (!post.getAncienEtudiant().equals(ancienEtudiant)) {
            throw new IllegalArgumentException("Ancien Etudiant with ID " + ancienId + " does not have permission to update post with ID " + postId);
        }
        updatedPost.setId(postId);
        return postRepository.save(updatedPost);
    }
}