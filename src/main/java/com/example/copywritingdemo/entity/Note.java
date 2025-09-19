package com.example.copywritingdemo.entity;

import jakarta.persistence.Entity;

@Entity
public class Note extends BaseEntity {

    private String title;
    private String content;

    // Getter & Setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
