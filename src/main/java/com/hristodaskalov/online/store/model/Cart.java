package com.hristodaskalov.online.store.model;

public class Cart {

    private Long id;
    private User user;
    private Float priceTotal;

    public Cart(Float priceTotal) {
        this.priceTotal = priceTotal;
    }

    public Cart() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Float getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(Float priceTotal) {
        this.priceTotal = priceTotal;
    }
}
