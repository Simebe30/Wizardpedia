package com.example.wizardpedia.services;

import com.example.wizardpedia.Models.*;
import com.example.wizardpedia.repositories.OffensiveRepository;
import com.example.wizardpedia.repositories.WizardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class OffensiveService implements MagicalItemService{

    private final WizardRepository wizardRepository;
    private final OffensiveRepository offensiveRepository;

    @Autowired
    public OffensiveService(WizardRepository wizardRepository, OffensiveRepository offensiveRepository) {
        super();
        this.wizardRepository = wizardRepository;
        this.offensiveRepository = offensiveRepository;
    }

    @Override
    public ProtectiveItem shopItem(Protective protective, Long wizardId) {
        return null;
    }


    @Override
    public MagicalItem shopItem(Long wizardId) {
        Wizard wizard = wizardRepository.findWizardById(wizardId).get();

        List<OffensiveItem> items = offensiveRepository.findAll();

        Random random = new Random();
        OffensiveItem item = items.get(random.nextInt(items.size()));
        Set<MagicalItem> offensiveItems = wizard.getMagicalItems();
        offensiveItems.add(item);
        wizard.setCoins(wizard.getCoins() - item.getPrice());

        wizardRepository.save(wizard);
        offensiveRepository.save(item);
        return item;
    }

    @Override
    public MagicalItem addItem(MagicalItem magicalItem) {
        return null;
    }

    @Override
    public List<MagicalItem> getItemsByName(String itemName) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean updateMagicalItem(Long id, String name, int powerLevel) {
        return false;
    }

    @Override
    public List<MagicalItem> getItemsByWizId(Long wizardId) {
        return null;
    }


    public int getTotalScore() {
        return 0;
    }
}
