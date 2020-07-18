package com.hristodaskalov.online.store.model;

public class SubCategory {

    private Long id;
    private String name;
    private Category category;

    public SubCategory(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public SubCategory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
