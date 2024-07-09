package com.example.project_g4.models;

public class MyCartModell {
    private String productName;
    private String productPrice;
    private String currentDate;
    private String currentTime;
    private String totalQuantity; // Thay đổi thành String nếu là kiểu dữ liệu String
    private long totalPrice; // Sử dụng long cho kiểu dữ liệu totalPrice

    public MyCartModell() {
        // Empty constructor required for Firebase
    }

    public MyCartModell(String productName, String productPrice, String currentDate, String currentTime, String totalQuantity, long totalPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.currentDate = currentDate;
        this.currentTime = currentTime;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
    }

    // Các phương thức getter và setter
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }
}
