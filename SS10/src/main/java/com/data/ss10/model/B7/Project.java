package com.data.ss10.model.B7;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private int id;
    private String name;
    private String description;
    private List<Document> documents = new ArrayList<>();

    public Project() {}
    public Project(int id, String name, String description, List<Document> documents) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.documents = documents;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<Document> getDocuments() { return documents; }
    public void setDocuments(List<Document> documents) { this.documents = documents; }
}

