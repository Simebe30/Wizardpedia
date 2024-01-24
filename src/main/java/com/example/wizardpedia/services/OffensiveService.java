package com.example.wizardpedia.services;

import com.example.wizardpedia.Models.*;
import com.example.wizardpedia.repositories.MagicalItemRepository;
import com.example.wizardpedia.repositories.OffensiveRepository;
import com.example.wizardpedia.repositories.ProtectiveRepository;
import com.example.wizardpedia.repositories.WizardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class OffensiveService {

    private final MagicalItemService magicalItemService;

    private final OffensiveRepository offensiveRepo;
    private final WizardRepository wizardRepo;

    @Autowired
    public OffensiveService(MagicalItemRepository magicalItemRepo, WizardRepository wizardRepo, OffensiveRepository offensiveRepo) {
        this.magicalItemService = new MagicalItemService(magicalItemRepo);
        this.offensiveRepo = offensiveRepo;
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
        offensiveRepo.save(new OffensiveItem(wizard, item));

        return true;
    }
}
