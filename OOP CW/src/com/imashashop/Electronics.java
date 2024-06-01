package com.imashashop;

import java.io.Serializable;

public class Electronics extends Product implements Serializable {
    private String brand;
    private int warrantyPeriod; //Assuming warranty period is in months

    //Constructor for Electronics
    public Electronics(String productId, String productName, int availableItems, double price, String brand, int warrantyPeriod) {
        super(productId, productName, availableItems, price);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    //Getters and Setters

    public String getBrand() {
        return brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public String getInfo() {
        return this.getBrand() + ", " + this.getWarrantyPeriod();
    }
    @Override
    public String getProduct() {
        return this.getProductId() + ",\n" + this.getProductName() + ",\n" +this.getInfo();
    }
    @Override
    public String getCategory() {
        return "Electronics";
    }

}
