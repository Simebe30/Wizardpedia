package com.example.wizardpedia.Models;

public enum Protective {

    ARMOR("Armor"),
    SHIELD("Shield"),
    CLOAK("Cloak"),
    AMULET("Amulet"),
    RING("Ring");

    private String displayName;

    Protective(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
