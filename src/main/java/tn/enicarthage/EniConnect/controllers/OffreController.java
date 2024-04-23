package tn.enicarthage.EniConnect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.EniConnect.entities.AncienEtudiant;
import tn.enicarthage.EniConnect.entities.Article;
import tn.enicarthage.EniConnect.entities.Offre;
import tn.enicarthage.EniConnect.services.IAncienEtudiantServiceImpl;
import tn.enicarthage.EniConnect.services.IOffreService;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/GOffre")
public class OffreController {

    private final IOffreService postService;
    private final IAncienEtudiantServiceImpl ancienEtudiantService;

    @Autowired
    public OffreController(IOffreService postService, IAncienEtudiantServiceImpl ancienEtudiantService) {
        this.postService = postService;
        this.ancienEtudiantService = ancienEtudiantService;
    }

    @GetMapping("/AllOffre")
    public List<Offre> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/Offre/{postId}")
    public ResponseEntity<Offre> getPostById(@PathVariable Long postId) {
        try {
            Offre offre = postService.getPostById(postId).orElseThrow(() -> new IllegalArgumentException("Offre with ID " + postId + " not found."));
            return ResponseEntity.ok(offre);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addOffre")
    public ResponseEntity<Offre> createPost( @RequestBody Offre offre) {
        try {

            return new ResponseEntity<Offre>(postService.createPost(offre), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/DeleteOffre/{postId}")
    public ResponseEntity<Void> deletePostById(@PathVariable Long postId) {
        try {
            postService.deletePostById(postId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/UpdateOffre/{postId}")
    public ResponseEntity<Offre> updatePost(@PathVariable Long ancienId, @PathVariable Long postId, @RequestBody Offre updatedOffre) {
        try {
            Offre updated = postService.updatePostById(ancienId, postId, updatedOffre);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/OffreByAncienEtudiantI/{emailInstitutionnelle}")

    public ResponseEntity<List<Offre>> getOffreByAncienEtudiantI(@PathVariable("emailInstitutionnelle") String emailInstitutionnelle) {
        try {
            List<Offre> resultat = postService.getOffresByAncienEtudiantI(emailInstitutionnelle);
            if (resultat.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(resultat, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

}