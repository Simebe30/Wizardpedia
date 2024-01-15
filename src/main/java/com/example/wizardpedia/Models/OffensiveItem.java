package com.example.wizardpedia.Models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("offensive")
public class OffensiveItem extends MagicalItem {

    private String offensiveType;
}
