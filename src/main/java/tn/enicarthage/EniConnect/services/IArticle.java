package tn.enicarthage.EniConnect.services;

import tn.enicarthage.EniConnect.entities.Article;

import java.util.List;

public interface IArticle {

    Article addArticle(Article article);
    List<Article> getAllArticle();
    List<Article> getArticlesByAncienEtudiantP(String emailPersonnelle);
    List<Article> getArticlesByAncienEtudiantI(String emailInstitutionnelle);
    Article getArticleById(Long id);
    Article updateArticle(Article article, Long id);
    void deleteArticle(Long id);

}
