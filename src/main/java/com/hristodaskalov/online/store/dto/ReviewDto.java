package com.hristodaskalov.online.store.dto;

public class ReviewDto {

    private Long id;
    private Float rating;
    private String content;
    private ItemDto item;

    public ReviewDto(Float rating, String content, ItemDto item) {
        this.rating = rating;
        this.content = content;
        this.item = item;
    }

    public ReviewDto() {
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

    public ItemDto getItem() {
        return item;
    }

    public void setItem(ItemDto item) {
        this.item = item;
    }
}
