package com.example.wizardpedia.Models;



public enum Protective {

    ARMOR("Armor", 100, 110),
    SHIELD("Shield", 50, 53),
    CLOAK("Cloak", 30, 32),
    AMULET("Amulet", 20, 15),
    RING("Ring", 5, 3);

    private String displayName;
    private int price;
    private int powerLevel;

     Protective(String displayName, int price, int powerLevel) {
        this.displayName = displayName;
        this.price = price;
        this.powerLevel = powerLevel;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPowerLevel() {
        return powerLevel;
    }

    public void setPowerLevel(int powerLevel) {
        this.powerLevel = powerLevel;
    }
}
