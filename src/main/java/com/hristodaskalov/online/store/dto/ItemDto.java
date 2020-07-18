package com.hristodaskalov.online.store.dto;

public class ItemDto {

    private Long id;
    private String name;
    private UserDto merchant;
    private Long stock;
    private Float price;
    private Short discount;
    private SubCategoryDto subCategory;

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

    public UserDto getMerchant() {
        return merchant;
    }

    public void setMerchant(UserDto merchant) {
        this.merchant = merchant;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Short getDiscount() {
        return discount;
    }

    public void setDiscount(Short discount) {
        this.discount = discount;
    }

    public SubCategoryDto getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategoryDto subCategory) {
        this.subCategory = subCategory;
    }
}
