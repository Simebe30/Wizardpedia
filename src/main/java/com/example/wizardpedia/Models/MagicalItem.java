package com.example.wizardpedia.Models;

import jakarta.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS )
public abstract class MagicalItem {


    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int powerLevel;

    private int price;

    @ManyToOne
    private Wizard wizard;

    public MagicalItem() {
    }

    public MagicalItem(Wizard wizard, String name, int powerLevel) {
        this.wizard = wizard;
        this.name = name;
        this.powerLevel = powerLevel;
    }
    public MagicalItem(Wizard wizard, String name, int powerLevel, int price) {
        this.wizard = wizard;
        this.name = name;
        this.powerLevel = powerLevel;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}


