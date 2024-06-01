package com.imashashop;

import java.io.Serializable;

public class Clothing extends Product implements Serializable {
    private String size;
    private String color;

    //Constructor
    public Clothing(String productId, String productName, int availableItems, double price, String size, String color) {
        super(productId, productName, availableItems, price);
        this.size = size;
        this.color = color;
    }

    //Getters and Setters

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String getInfo() {
        return  this.getSize() + ", " + this.getColor();
    }
    @Override
    public String getProduct() {
        return this.getProductId() + ",\n" + this.getProductName() + ",\n" +this.getInfo();
    }
    @Override
    public String getCategory() {
        return "Clothing";
    }

}
