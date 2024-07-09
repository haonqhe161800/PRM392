package com.example.project_g4.models;

public class PopularModel {
    private String name;
    private String rating;
    private String description;
    private String discount;
    private String type;
    private String img_url;

    public PopularModel() {
        this("", "", "", "", "", "");
    }

    public PopularModel(String name, String rating, String description, String discount, String type, String img_Url) {
        this.name = name != null ? name : "";
        this.rating = rating != null ? rating : "";
        this.description = description != null ? description : "";
        this.discount = discount != null ? discount : "";
        this.type = type != null ? type : "";
        this.img_url = img_Url != null ? img_Url : "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name != null ? name : "";
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating != null ? rating : "";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description != null ? description : "";
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount != null ? discount : "";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type != null ? type : "";
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url != null ? img_url : "";
    }
}