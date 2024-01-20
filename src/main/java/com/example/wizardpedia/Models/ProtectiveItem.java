package com.example.wizardpedia.Models;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("protective")
public class ProtectiveItem extends MagicalItem{

    @Enumerated(EnumType.STRING)
    private Protective protectiveType;

    public ProtectiveItem(Wizard wizard, String name, int powerLever, Protective protectiveType) {
        super(wizard,name, powerLever);
        this.protectiveType = protectiveType;
    }

    public ProtectiveItem() {

    }
}
