package com.example.wizardpedia.DTOs;

import com.example.wizardpedia.Models.MagicalItem;

public record MagicalItemDTO(String name, int powerLevel) {

    public MagicalItemDTO(MagicalItem m){
        this(m.getName(),m.getPowerLevel());
    }
}
