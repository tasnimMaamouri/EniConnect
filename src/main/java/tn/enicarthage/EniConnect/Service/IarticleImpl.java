package tn.enicarthage.EniConnect.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.enicarthage.EniConnect.Dao.AncienEtudiantRepository;
import tn.enicarthage.EniConnect.Dao.ArticleRepository;
import tn.enicarthage.EniConnect.Entity.Article;
import tn.enicarthage.EniConnect.Iservice.IArticle;

import java.util.ArrayList;
import java.util.List;

@Service
public class IarticleImpl implements IArticle {
    @Autowired
    private ArticleRepository articleR;
    @Autowired
    private AncienEtudiantRepository ancienEtudiantRepository;

    @Override
    public Article addArticle(Article article) {
        return articleR.save(article);

    }

    @Override
    public List<Article> getAllArticle() {
        List<Article> articles=new ArrayList<Article>();
        for(Article article:articleR.findAll()) {
            articles.add(article);
        }

        return articles;    }

    @Override
    public List<Article> getArticlesByAncienEtudiantP(String emailPersonnelle) {
        List<Article> articles=new ArrayList<Article>();
        for(Article article:articleR.findByAncienEtudiantEmailPersonnelle(emailPersonnelle)) {
            articles.add(article);
        }

        return articles;
    }

    @Override
    public List<Article> getArticlesByAncienEtudiantI(String emailInstitutionnelle) {
        List<Article> articles=new ArrayList<Article>();
        for(Article article:articleR.findByAncienEtudiantEmailInstitutionnelle(emailInstitutionnelle)) {
            articles.add(article);
        }

        return articles;
    }

    @Override
    public Article getArticleById(Long id) {
        if(articleR.findById(id).isPresent()) {
            return articleR.findById(id).get();
        }
        else {
            return null;
        }    }

    @Override
    public Article updateArticle(Article article, Long id) {
        article.setId(id);

        return articleR.save(article);    }

    @Override
    public void deleteArticle(Long id) {
        articleR.deleteById(id);

    }

}
