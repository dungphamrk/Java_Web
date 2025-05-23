package com.data.ss11.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Category {
    private Long id;

    @NotBlank(message = "Tên không được để trống")
    @Size(max = 50, message = "Tên không quá 50 ký tự")
    private String categoryName;

    private Boolean status;

    public Category() {}

    public Category(Long id, String categoryName, Boolean status) {
        this.id = id;
        this.categoryName = categoryName;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }
}
