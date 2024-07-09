package com.example.project_g4.models;

public class NavCategoryDetailedModel {
    String name;
    String type;
    String img_url;
    String price;

    public NavCategoryDetailedModel() {
    }

    public NavCategoryDetailedModel(String name, String img_url, String price) {
        this.name = name;
        this.img_url = img_url;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
