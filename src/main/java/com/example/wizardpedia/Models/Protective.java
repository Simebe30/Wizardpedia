package com.example.wizardpedia.Models;

import lombok.Getter;

@Getter
public enum Protective {

    ARMOR("Armor", 100, 80),
    SHIELD("Shield", 50, 60),
    CLOAK("Cloak", 30, 40),
    AMULET("Amulet", 20, 30),
    RING("Ring", 10, 5);

    private String displayName;
    private int price;
    private int powerLevel;

    Protective(String displayName, int price, int powerLevel) {
        this.displayName = displayName;
        this.price = price;
        this.powerLevel = powerLevel;
    }
}
