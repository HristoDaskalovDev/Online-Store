package com.hristodaskalov.online.store.entity;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item_picture")
public class ItemPictureEntity extends PictureEntity {

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemEntity item;

    public ItemEntity getItem() {
        return item;
    }

    public void setItem(ItemEntity item) {
        this.item = item;
    }
}
