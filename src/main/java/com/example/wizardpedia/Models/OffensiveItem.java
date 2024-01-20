package com.example.wizardpedia.Models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
public class OffensiveItem extends MagicalItem {

    private String offensiveType;

    public OffensiveItem() {
    }
}
