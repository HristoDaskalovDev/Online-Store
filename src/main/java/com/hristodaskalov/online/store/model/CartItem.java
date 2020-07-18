package com.hristodaskalov.online.store.model;

public class CartItem {

    private Long id;
    private Item item;
    private Cart cart;
    private Long quantity;

    public CartItem(Item item, Cart cart, Long quantity) {
        this.item = item;
        this.cart = cart;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
