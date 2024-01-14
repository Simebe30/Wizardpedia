package com.example.wizardpedia.Models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
public class Wizard {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int age;

    private int coins = 1000;

    @OneToMany(mappedBy = "wizard", cascade = CascadeType.ALL)
    Set<MagicalItem> magicalItems = new HashSet<>();

    public Wizard() {
    }

    public Wizard(String name, int age) {
        this.name = name;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public Set<MagicalItem> getMagicalItems() {
        return magicalItems;
    }

    public int getTotalScore(){
        return magicalItems.stream().mapToInt(MagicalItem::getPowerLevel).sum();
    }

    public void setMagicalItems(Set<MagicalItem> magicalItems) {
        this.magicalItems = magicalItems;
    }
}
