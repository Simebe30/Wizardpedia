package com.example.wizardpedia.Models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@DiscriminatorValue("offensive")
public class OffensiveItem extends MagicalItem {

    @Enumerated(EnumType.STRING)
    private Offensive offensiveType;

    public OffensiveItem(Wizard wizard, Offensive offensive) {
        super(wizard, offensive.getDisplayName(), offensive.getPowerLevel(), 200);
        this.offensiveType = offensive;
    }

    public OffensiveItem() {

    }
}
