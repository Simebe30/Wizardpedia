package com.example.wizardpedia.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "magicalItems")
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

    public MagicalItem() {
    }

    public MagicalItem(String name, int powerLevel) {
        this.name = name;
        this.powerLevel = powerLevel;
    }

    public MagicalItem(String name, int powerLevel, Wizard wizard) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPowerLevel() {
        return powerLevel;
    }

    public void setPowerLevel(int powerLevel) {
        this.powerLevel = powerLevel;
    }

    public Wizard getWizard() {
        return wizard;
    }

    public void setWizard(Wizard wizard) {
        this.wizard = wizard;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
