package com.hristodaskalov.online.store.dto;

public class ItemPictureDto extends PictureDto {

    private ItemDto itemDto;

    public ItemPictureDto(String name, String path, ItemDto itemDto) {
        super(name, path);
        this.itemDto = itemDto;
    }

    public ItemDto getItemDto() {
        return itemDto;
    }

    public void setItemDto(ItemDto itemDto) {
        this.itemDto = itemDto;
    }
}
