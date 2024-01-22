package com.example.wizardpedia.Models;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("protective")
public class ProtectiveItem extends MagicalItem{

    @Enumerated(EnumType.STRING)
    private Protective protectiveType;

    public ProtectiveItem(Wizard wizard, Protective protectiveType) {
        super(wizard, protectiveType.getDisplayName(), protectiveType.getPowerLevel(), protectiveType.getPrice());
        this.protectiveType = protectiveType;
    }
    public ProtectiveItem() {

    }
}
