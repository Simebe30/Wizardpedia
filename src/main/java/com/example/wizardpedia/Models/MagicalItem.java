package com.example.wizardpedia.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "magicalItems")
@Getter
@Setter
@NoArgsConstructor
public class MagicalItem {


    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int powerLevel;

    private Protective protectiveItem;

    private String offensiveItem;

    @ManyToOne
    private Wizard wizard;


    public MagicalItem(String name, int powerLevel) {
        this.name = name;
        this.powerLevel = powerLevel;
    }
    public MagicalItem(String name, int powerLevel, Wizard wizard) {
        this.name = name;
        this.powerLevel = powerLevel;
        this.wizard = wizard;
    }
    public MagicalItem(String name, Protective protective, Wizard wizard) {
        this.name = name;
        this.protectiveItem = protective;
    }

}
