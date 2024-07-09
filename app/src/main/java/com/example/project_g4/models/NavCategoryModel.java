package com.example.project_g4.models;

public class NavCategoryModel {
    String name;
    String description;
    String discount;
    String img_url;
    String type;

    public NavCategoryModel() {
    }

    public NavCategoryModel(String name, String description, String discount, String img_url) {
        this.name = name;
        this.description = description;
        this.discount = discount;
        this.img_url = img_url;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String discription) {
        this.description = discription;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
