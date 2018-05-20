package com.ecbd.Entity;

public class Article {
    private String id;
    private String name;
    private String body;
    private String userId;

    public Article(String id, String name, String body, String userId) {
        this.id = id;
        this.name = name;
        this.body = body;
        this.userId = userId;
    }

    public Article(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
