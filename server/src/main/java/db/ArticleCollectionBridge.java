package db;

import com.ecbd.Entity.Article;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.types.ObjectId;
import tools.WordParser;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class ArticleCollectionBridge {
    public static List<Article> getArticles() {
        MongoCollection<Document> articleCollection = DbSingleton.getInstance().getArticleCollection();
        FindIterable<Document> documents = articleCollection.find();
        MongoCursor<Document> iterator = documents.iterator();
        List<Article> retList = new LinkedList<>();
        while(iterator.hasNext()) {
            retList.add(parseArticleDocument(iterator.next()));
        }
        return retList;
    }
    public static List<Article> getArticles(String page, String search) {
        int pageNumber = Integer.parseInt(page);
        int pageSize = 20;
        MongoCollection<Document> articleCollection = DbSingleton.getInstance().getArticleCollection();
        MongoCursor<Document> iterator;
        if ((search != null) && search.length() > 0) {

        } else {
            iterator = articleCollection.find().skip(pageSize * (pageNumber - 1)).limit(pageSize).iterator();
        }

        List<Article> retList = new LinkedList<>();
        while(iterator.hasNext()) {
            retList.add(parseArticleDocument(iterator.next()));
        }
        return retList;
    }
    public static List<Article> getMyArticles(String userId) {
        MongoCollection<Document> articleCollection = DbSingleton.getInstance().getArticleCollection();
        FindIterable<Document> documents = articleCollection.find(eq("userId", userId));
        MongoCursor<Document> iterator = documents.iterator();
        List<Article> retList = new LinkedList<>();
        while(iterator.hasNext()) {
            retList.add(parseArticleDocument(iterator.next()));
        }
        return retList;
    }

    public static Article getArticle(String id) {
        MongoCollection<Document> articleCollection = DbSingleton.getInstance().getArticleCollection();
        return parseArticleDocument(articleCollection.find(eq("_id", new ObjectId(id))).first());
    }

    public static void addArticle(Article article) {
        MongoCollection<Document> articleCollection = DbSingleton.getInstance().getArticleCollection();
        Document articleDoc = makeArticleDocument(article);
        articleCollection.insertOne(articleDoc);
        WordParser wp = new WordParser();
        wp.parseDoc(article.getBody() + article.getName());
        HashMap<String, Integer> wordMap = wp.getWordMap();
        IndirectIndexCollectionBridge.addIndirectIndex(wordMap, articleDoc.get("_id").toString());
    }

    public static void updateArticle(Article article) {
        MongoCollection<Document> articleCollection = DbSingleton.getInstance().getArticleCollection();
        Document articleDoc = makeArticleDocument(article);
        ObjectId objectId = new ObjectId(article.getId());
        articleDoc.put("_id", objectId);
        articleCollection.replaceOne(eq("_id", objectId), articleDoc);
    }


    public static Article parseArticleDocument(Document articleDoc) {
        return new Article(articleDoc.get("_id").toString(), articleDoc.get("name").toString(), articleDoc.get("body").toString(), articleDoc.get("userId").toString());
    }

    public static Document makeArticleDocument(Article article) {
        Document articleDoc = new Document();
        articleDoc.put("name", article.getName());
        articleDoc.put("body", article.getBody());
        articleDoc.put("userId", article.getUserId());
        return articleDoc;
    }

}
