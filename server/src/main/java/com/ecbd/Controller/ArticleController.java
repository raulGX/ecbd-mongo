package com.ecbd.Controller;

import com.ecbd.Entity.Article;
import com.ecbd.Entity.User;
import com.ecbd.Service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tools.Encoder;

import java.util.Collection;

@CrossOrigin
@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    @RequestMapping(value="/myArticles", method = RequestMethod.GET)
    public Collection<Article> getMyArticles(@RequestHeader("Authorization") String token) {
        User user = Encoder.decodeUser(token);
        return articleService.getMyArticles(user.getId());
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Article getArticleByID(@PathVariable("id") String id) {
        return articleService.getArticleById(id);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateArticle(@RequestBody Article article, @PathVariable("id") String id) {
        articleService.updateArticle(article);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertArticle(@RequestBody Article article) {
        articleService.insertArticle(article);
    }
}
