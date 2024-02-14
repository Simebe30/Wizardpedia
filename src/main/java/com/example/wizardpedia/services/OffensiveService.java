package com.example.wizardpedia.services;

import com.example.wizardpedia.Models.*;
import com.example.wizardpedia.repositories.MagicalItemRepository;
import com.example.wizardpedia.repositories.WizardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class OffensiveService {

    private final MagicalItemService magicalItemService;
    private final WizardRepository wizardRepo;

    @Autowired
    public OffensiveService(MagicalItemRepository magicalItemRepo, WizardRepository wizardRepo) {
        this.magicalItemService = new MagicalItemService(magicalItemRepo);
        this.wizardRepo = wizardRepo;
    }

    public boolean shopItem(Long wizardId) {
        Wizard wizard = wizardRepo.findWizardById(wizardId).get();

        Random random = new Random();
        List<Offensive> items = List.of(Offensive.values());
        Offensive item = items.get(random.nextInt(items.size()));
        int currentMoney = wizard.getCoins() - 200;

        if (currentMoney < 0) {
            return false;
        }

        wizard.setCoins(wizard.getCoins() - 200);
        magicalItemService.save(new OffensiveItem(wizard, item));

        return true;
    }
}
