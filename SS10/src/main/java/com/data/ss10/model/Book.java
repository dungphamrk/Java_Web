package com.data.ss10.model;

import org.springframework.web.multipart.MultipartFile;

public class Book {
    private String title;
    private String author;
    private String description;
    private MultipartFile file;

    public Book() {
    }

    public Book(String title, String author, String description, MultipartFile file) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.file = file;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
