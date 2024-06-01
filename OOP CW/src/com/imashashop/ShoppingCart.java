package com.imashashop;

import java.util.ArrayList;

public class ShoppingCart {
    // instance variables of shopping cart
    private ArrayList<Product> productCart;

    private ArrayList <Integer> quantity;

    // constructor to create a new shopping cart (instantiates a new object of the class ShoppingCart)
    public ShoppingCart() {
        this.productCart = new ArrayList<Product>();
        this.quantity = new ArrayList<>();
    }

    //method to add products to the shopping cart
    public void addProduct(Product product) {
        int index = productCart.indexOf(product); //get the index of the product in the shopping cart
        if (index != -1){  //if the product is already in the shopping cart
            quantity.set(index, quantity.get(index) + 1);//increment the quantity of the product by 1
        }else {
            productCart.add(product); //add the product to the shopping cart
            quantity.add(1);//set the quantity of the product to 1
        }
    }

    //method to remove products from the shopping cart
    public void removeProduct(Product product) {
        productCart.remove(product);
    }

    //calculate the total price of the products in the shopping cart
    public ArrayList<Product> getProductCart(){

        return productCart;
    }
    public  ArrayList<Integer> getQuantity(){

        return quantity;
    }

    public double calculateTotalPrice(){
        double totalPrice =0.0;
        //for loop to iterate through the products in the shopping cart
        for(int i=0;i<productCart.size();i++){
            totalPrice += productCart.get(i).getPrice();// add the price of each product to the total price
            //getPrice() is a getter method in the Products class
            //get(i) returns the product at index i
        }
        return totalPrice;
    }

    public boolean isEligibleForDiscount() {
        for(int i=0; i<productCart.size(); i++) {
            Product products = productCart.get(i);
            int amount = quantity.get(i);
            if (hasEnoughProductsOfSameCategory(products, amount)) {
                return true;
            }
        }
        return false;

    }

    private boolean hasEnoughProductsOfSameCategory(Product products, int amount) {
        int count = 0;
        for(int i=0; i<productCart.size(); i++) {
            Product otherproduct = productCart.get(i);
            if (otherproduct.getCategory().equals(products.getCategory())) { // if the category of the product is the same as the category of the other product
                count += quantity.get(i);
                if (count >=3){
                    return true;
                }
            }
        }
        return false;
    }
}
