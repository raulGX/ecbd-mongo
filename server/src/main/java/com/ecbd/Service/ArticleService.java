package com.ecbd.Service;

import com.ecbd.Dao.ArticleDao;
import com.ecbd.Entity.Article;
import db.IndirectIndexCollectionBridge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.WordParser;

import java.util.Collection;
import java.util.HashMap;

@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;

    public Collection<Article> getAllArticles(String page, String search) {
        return this.articleDao.getAllArticles(page, search);
    }

    public Article getArticleById(String id) {
        return this.articleDao.getArticleById(id);
    }

    public void updateArticle(Article article) {
        articleDao.updateArticle(article);
    }

    public void insertArticle(Article article) {
        articleDao.insertArticle(article);
    }

    public Collection<Article> getMyArticles(String id) {
        return articleDao.getArticlesForUser(id);
    }
}