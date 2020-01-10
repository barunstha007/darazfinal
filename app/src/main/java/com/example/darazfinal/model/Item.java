package com.example.darazfinal.model;

public class Item { private String productName;
    private String productImage;
    private String price;

    public Item(String productName, String productImage, String price) {
        this.productName = productName;
        this.productImage = productImage;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
