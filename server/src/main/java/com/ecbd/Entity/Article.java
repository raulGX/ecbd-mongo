package com.ecbd.Entity;

public class Article {
    private int id;
    private String name;
    private String body;

    public Article(int id, String name, String body) {
        this.id = id;
        this.name = name;
        this.body = body;
    }

    public Article(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
