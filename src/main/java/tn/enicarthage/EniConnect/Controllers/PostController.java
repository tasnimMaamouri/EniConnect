package tn.enicarthage.EniConnect.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.enicarthage.EniConnect.Entities.Post;
import tn.enicarthage.EniConnect.Exceptions.ForbiddenOperationException;
import tn.enicarthage.EniConnect.Exceptions.ResourceNotFoundException;
import tn.enicarthage.EniConnect.Services.PostService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{ID}")
    public Post getPostByID(@PathVariable Long ID) {
        return postService.getPostByID(ID);
    }

    @PostMapping
    public Post savePost(@RequestBody Post post) {
        return postService.savePost(post);
    }

    @DeleteMapping("/{ID}")
    public void deletePost(@PathVariable Long ID) {
        postService.deletePost(ID);
    }

    @PutMapping("/{ID}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post newPost) {
        try {
            // Get current user details
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            // Update the post
            Post updatedPost = postService.updatePost(id, newPost, userDetails.getUsername());

            // Return the updated post
            return ResponseEntity.ok(updatedPost);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ForbiddenOperationException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }



    @PostMapping("/upload")
    public ResponseEntity<Post> addpdfPost(@RequestParam("pdfFile") MultipartFile pdfFile) {
        try {
            // Get current user details
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            // Add the post
            Post addedPost = postService.addpdfPost(pdfFile, userDetails.getUsername());

            // Return the added post
            return ResponseEntity.status(HttpStatus.CREATED).body(addedPost);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}


