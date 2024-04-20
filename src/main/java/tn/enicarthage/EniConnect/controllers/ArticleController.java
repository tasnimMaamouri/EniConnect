package tn.enicarthage.EniConnect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.EniConnect.entities.Article;
import tn.enicarthage.EniConnect.services.IArticle;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/GArticle")
public class ArticleController {
    @Autowired
    private IArticle articleService;

    @PostMapping("/AddArticle")

    public ResponseEntity<Article> addOrdonnance(@RequestBody Article article) {
        try {

            return new ResponseEntity<Article>(articleService.addArticle(article), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/AllArticle")

    public ResponseEntity<List<Article>> getAllArticle() {
        try {
            if (articleService.getAllArticle().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(articleService.getAllArticle(), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/Article/{idArticle}")

    public ResponseEntity<Article> getArticleById(@PathVariable("idArticle") Long idArticle) {
        try {
            Article resultat = articleService.getArticleById(idArticle);
            if (resultat != null) {
                return new ResponseEntity<>(resultat, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/ArticleByAncienEtudiantP/{emailPersonnelle}")

    public ResponseEntity<List<Article>> getArticleByAncienEtudiantP(@PathVariable("emailPersonnelle") String emailPersonnelle) {
        try {
            List<Article> resultat = articleService.getArticlesByAncienEtudiantP(emailPersonnelle);
            if (resultat.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(resultat, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @GetMapping("/ArticleByAncienEtudiantI/{emailInstitutionnelle}")

    public ResponseEntity<List<Article>> getArticleByAncienEtudiantI(@PathVariable("emailInstitutionnelle") String emailInstitutionnelle) {
        try {
            List<Article> resultat = articleService.getArticlesByAncienEtudiantI(emailInstitutionnelle);
            if (resultat.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(resultat, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @PutMapping("/UpdateArticle/{idArticle}")

    public ResponseEntity<Article> updateArticle(@RequestBody Article article, @PathVariable("idArticle") Long idArticle) {
        try {
            if (articleService.getArticleById(idArticle) != null) {

                return new ResponseEntity<>(articleService.updateArticle(article, idArticle), HttpStatus.OK);


            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/DeleteArticle/{idArticle}")

    public ResponseEntity<HttpStatus> deleteOrdonnance(@PathVariable("idArticle") Long idArticle) {
        try {
            if (articleService.getArticleById(idArticle) != null) {
                articleService.deleteArticle(idArticle);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
