package com.ecbd.Dao;

import com.ecbd.Entity.Article;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ArticleDao {

    private static Map<Integer, Article> articles;

    static {

        articles = new HashMap<Integer, Article>(){
            {
                put(1, new Article(1, "Hello", "Hello world"));
                put(2, new Article(2, "Salut", "Lume"));
                put(3, new Article(3, "Salut", "Lume"));
                put(4, new Article(4, "Salut", "Lume"));
            }
        };
    }

    public Collection<Article> getAllArticles() {
        return this.articles.values();
    }

    public Article getArticleById(int id) {
        return articles.get(id);
    }

    public void updateArticle(Article article) {
        Article current = articles.get(article.getId());
        current.setBody(article.getBody());
        current.setName(article.getName());
        articles.put(current.getId(), current);
    }

    public void insertArticle(Article article) {
        article.setId(articles.size());
        articles.put(articles.size(), article);
    }
}
