package com.example.wizardpedia.Models;

import jakarta.persistence.*;


@Entity
public class MagicalItem {


    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int powerLevel;

    private Protective protective;

    private String offensive;

    @ManyToOne
    private Wizard wizard;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MagicalItem() {
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

    public Protective getProtective() {
        return protective;
    }

    public void setProtective(Protective protective) {
        this.protective = protective;
    }

    public String getOffensive() {
        return offensive;
    }

    public void setOffensive(String offensive) {
        this.offensive = offensive;
    }

    public Wizard getWizard() {
        return wizard;
    }

    public void setWizard(Wizard wizard) {
        this.wizard = wizard;
    }
}


