package com.example.wizardpedia.Models;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("protective")
public class ProtectiveItem extends MagicalItem{

    @Enumerated(EnumType.STRING)
    private Protective protectiveType;
}
