package tn.enicarthage.EniConnect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.enicarthage.EniConnect.entities.Article;
import tn.enicarthage.EniConnect.entities.Post;
import tn.enicarthage.EniConnect.entities.Status;
import tn.enicarthage.EniConnect.services.IPostService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private IPostService postService;

    @GetMapping("AllPosts")
    public List<Post> getAllPosts() {
           try {
            if (postService.getAllPosts().isEmpty()) {
                return (List<Post>) new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return (List<Post>) new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
            }
        } catch (Exception e) {
            return (List<Post>) new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("PostById/{ID}")
    public ResponseEntity<Post> getPostByID(@PathVariable Long ID) {
        try {
            Post resultat = postService.getPostByID(ID);
            if (resultat != null) {
                return new ResponseEntity<>(resultat, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("PostByAncienEtd/{ID}")
    List<Post> getPostsByAncienEtudiant(@RequestBody String emailPersonnelle)
    {return postService.getPostsByAncienEtudiant(emailPersonnelle);}

    @PostMapping("AddPost")
    public Post savePost(@RequestBody Post post) {
        return postService.savePost(post);
    }


     @DeleteMapping("/DeleteAPost/{idPost}")

    public ResponseEntity<HttpStatus> deletePost(@PathVariable("idPost") Long idPost) {
        try {
            if (postService.getPostByID(idPost) != null) {
                postService.deletePost(idPost);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/UpdatePost/{idPost}")
    public ResponseEntity<Post> updatePost(@RequestBody Post post, @PathVariable("idPost") Long idPost) {
        try {
            if (postService.getPostByID(idPost) != null) {

                return new ResponseEntity<>(postService.updatePost(post , idPost) ,HttpStatus.OK);


            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/upload")
    public ResponseEntity<Post> uploadPost(@RequestParam("pdfFile") MultipartFile pdfFile,
                                           @RequestParam("ownerID") Long ownerID,
                                           @RequestParam("title") String title,
                                           @RequestParam("content") String content,
                                           @RequestParam("status") Status status) {
        try {
            Post uploadedPost = postService.uploadPost(pdfFile, ownerID, title, content, status);
            return ResponseEntity.ok(uploadedPost);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}


