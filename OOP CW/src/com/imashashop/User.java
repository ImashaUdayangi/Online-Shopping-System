package com.imashashop;

public class User {
    private String username;
    private String password;
    private boolean FirstTimeDiscount;

    //Constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //Getters and Setters

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isFirstTimeDiscount(){
        return FirstTimeDiscount;
    }
    public void setFirstTimeDiscount(boolean FirstTimeDiscount){

        this.FirstTimeDiscount=FirstTimeDiscount;
    }

}
