package com.hristodaskalov.online.store.dto;

public class OrderDto {

    private Long id;
    private CartItemDto cartItem;
    private UserDto user;
    private String status;
    private String deliveryAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CartItemDto getCartItem() {
        return cartItem;
    }

    public void setCartItem(CartItemDto cartItem) {
        this.cartItem = cartItem;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
