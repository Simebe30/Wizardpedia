package com.example.wizardpedia.DTOs;

import com.example.wizardpedia.Models.Wizard;

public record WizardDTO(Long id, String name, int age) {

    public WizardDTO(Wizard w) {
        this(w.getId(), w.getName(), w.getAge());
    }
}
