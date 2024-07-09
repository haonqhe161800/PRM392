package com.example.project_g4.models;

import java.io.Serializable;

public class ViewAllModel implements Serializable{
     String name;
     String rating;
     String description;
     String type;
     String img_url;
     int prcie;

    public ViewAllModel() {
    }

    public ViewAllModel(String name, String rating, String description, String type, String img_url, int prcie) {
        this.name = name;
        this.rating = rating;
        this.description = description;
        this.type = type;
        this.img_url = img_url;
        this.prcie = prcie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getPrcie() {
        return prcie;
    }

    public void setPrcie(int prcie) {
        this.prcie = prcie;
    }
}
