package com.example.newsie;

public class News {
    public String imageUrl;
    public String title;
    public String description;
    public String articleUrl;

    public News(String imageUrl, String title, String description,String articleUrl) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
        this.articleUrl = articleUrl;

    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getArticleUrl() {
        return articleUrl;
    }
}
