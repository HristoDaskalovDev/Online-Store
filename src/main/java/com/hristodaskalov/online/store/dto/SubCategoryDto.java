package com.hristodaskalov.online.store.dto;

public class SubCategoryDto {

    private Long id;
    private String name;

    public SubCategoryDto(String name) {
        this.name = name;
    }

    public SubCategoryDto() {
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
}
