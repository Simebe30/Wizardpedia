package com.example.wizardpedia.services;

import com.example.wizardpedia.Models.*;
import com.example.wizardpedia.repositories.MagicalItemRepository;
import com.example.wizardpedia.repositories.WizardRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface MagicalItemService {

//    ProtectiveItem shopItem(Protective protective, Wizard wizard);

    ProtectiveItem shopItem(Protective protective, Long wizardId);

    MagicalItem shopItem();

    MagicalItem addItem(MagicalItem magicalItem);
    List<MagicalItem> getItemsByName(String itemName);
    boolean delete(Long id);
    boolean updateMagicalItem(Long id, String name, int powerLevel);
    List<MagicalItem> getItemsByWizId(Long wizardId);
    int getTotalScore();
}

