package com.hristodaskalov.online.store.model;

public class ItemPicture extends Picture {

    private Item item;

    public ItemPicture(String name, String path, Item item) {
        super(name, path);
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
