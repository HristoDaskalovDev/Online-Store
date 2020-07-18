package com.hristodaskalov.online.store.dto;

public class CartItemDto {

    private ItemDto item;
    private Long quantity;

    public CartItemDto(ItemDto item, Long quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public ItemDto getItem() {
        return item;
    }

    public void setItem(ItemDto item) {
        this.item = item;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
