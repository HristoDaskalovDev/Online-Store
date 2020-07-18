package com.hristodaskalov.online.store.model;

public abstract class Picture {

    private Long id;
    private String name;
    private String path;


    public Picture(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public Picture() {
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
