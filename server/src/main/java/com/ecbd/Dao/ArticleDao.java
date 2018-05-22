package com.ecbd.Dao;

import com.ecbd.Entity.Article;
import db.ArticleCollectionBridge;
import db.DbSingleton;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Repository
public class ArticleDao {

    private static Map<String, Article> articles;

    static {

        articles = new HashMap<String, Article>(){
            {
                put("1", new Article("1", "Hello", "Hello world", "1"));
                put("2", new Article("2", "Salut", "Lume", "1"));
                put("3", new Article("3", "Salut", "Lume", "1"));
                put("4", new Article("4", "Salut", "Lume", "2"));
            }
        };
    }

    public Collection<Article> getAllArticles(String page, String search) {
        return ArticleCollectionBridge.getArticles(page, search);
    }

    public Collection<Article> getArticlesForUser(String id) {
        return ArticleCollectionBridge.getMyArticles(id);
    }

    public Article getArticleById(String id) {
        return ArticleCollectionBridge.getArticle(id);
    }
    public void updateArticle(Article article) {
        ArticleCollectionBridge.updateArticle(article);

    }

    public void insertArticle(Article article) {
        ArticleCollectionBridge.addArticle(article);
    }
}
