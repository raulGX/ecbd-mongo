package com.ecbd.Dao;

import com.ecbd.Entity.Article;
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

    public Collection<Article> getAllArticles() {
        return this.articles.values();
    }

    public Collection<Article> getArticlesForUser(String id) {
        return this.articles.entrySet().stream()
                .filter(article -> article.getValue().getUserId().equals(id))
                .map(entry -> entry.getValue())
                .collect(Collectors.toList());
    }

    public Article getArticleById(String id) {
        return articles.get(id);
    }

    public void updateArticle(Article article) {
        Article current = articles.get(article.getId());
        current.setBody(article.getBody());
        current.setName(article.getName());
        articles.put(current.getId(), current);
    }

    public void insertArticle(Article article) {
        article.setId(""+articles.size());
        articles.put(""+articles.size(), article);
    }
}
