package tn.enicarthage.EniConnect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.enicarthage.EniConnect.entities.AncienEtudiant;
import tn.enicarthage.EniConnect.entities.Article;
import tn.enicarthage.EniConnect.entities.Post;
import tn.enicarthage.EniConnect.entities.Status;
import tn.enicarthage.EniConnect.services.IAncienEtudiantServiceImpl;
import tn.enicarthage.EniConnect.services.IPostService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PostController {

    private final IPostService postService;
    private final IAncienEtudiantServiceImpl ancienEtudiantService;

    @Autowired
    public PostController(IPostService postService, IAncienEtudiantServiceImpl ancienEtudiantService) {
        this.postService = postService;
        this.ancienEtudiantService = ancienEtudiantService;
    }

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable Long postId) {
        try {
            Post post = postService.getPostById(postId).orElseThrow(() -> new IllegalArgumentException("Post with ID " + postId + " not found."));
            return ResponseEntity.ok(post);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/ancien/{ancienId}/post/create")
    public ResponseEntity<Post> createPost(@PathVariable Long ancienId, @RequestBody Post post) {
        try {
            Optional<AncienEtudiant> ancienOptional = Optional.ofNullable(ancienEtudiantService.getAncienEtudiantById(ancienId));
            if (ancienOptional.isPresent()) {
                post.setAncienEtudiant(ancienOptional.get());
                Post createdPost = postService.createPost(post);
                return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
            } else {
                throw new IllegalArgumentException("Ancien Etudiant with id " + ancienId + " not found.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<Void> deletePostById(@PathVariable Long postId) {
        try {
            postService.deletePostById(postId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/ancien/{ancienId}/post/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable Long ancienId, @PathVariable Long postId, @RequestBody Post updatedPost) {
        try {
            Post updated = postService.updatePostById(ancienId, postId, updatedPost);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

}