package com.ecbd.Service;

import com.ecbd.Dao.ArticleDao;
import com.ecbd.Entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;

    public Collection<Article> getAllArticles() {
        return this.articleDao.getAllArticles();
    }

    public Article getArticleById(int id) {
        return this.articleDao.getArticleById(id);
    }

    public void updateArticle(Article article) {
        articleDao.updateArticle(article);
    }

    public void insertArticle(Article article) {
        articleDao.insertArticle(article);
    }
}