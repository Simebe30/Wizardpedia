package com.example.wizardpedia.DTOs;

import com.example.wizardpedia.Models.Wizard;

public record WizardDTO(Long id, String name, int age, int totalPower, int treasure) {

    public WizardDTO(Wizard w) {
        this(w.getId(), w.getName(), w.getAge(), w.getTotalScore(), w.getCoins());
    }
}
