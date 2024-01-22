package com.example.wizardpedia.Models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
public class OffensiveItem extends MagicalItem {

    public OffensiveItem(Wizard wizard, String name, int powerLever) {
        super(wizard,name, powerLever);
    }

    public OffensiveItem() {

    }
}
