package com.hristodaskalov.online.store.model;

public class Review {

    private Long id;
    private Float rating;
    private String content;
    private Item item;
    private User user;

    public Review(Float rating, String content, Item item, User user) {
        this.rating = rating;
        this.content = content;
        this.item = item;
        this.user = user;
    }

    public Review() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
