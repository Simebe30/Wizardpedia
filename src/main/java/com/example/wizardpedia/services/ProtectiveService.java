package com.example.wizardpedia.services;

import com.example.wizardpedia.Models.Protective;
import com.example.wizardpedia.Models.ProtectiveItem;
import com.example.wizardpedia.Models.Wizard;
import com.example.wizardpedia.repositories.MagicalItemRepository;
import com.example.wizardpedia.repositories.ProtectiveRepository;
import com.example.wizardpedia.repositories.WizardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProtectiveService {

    private final MagicalItemService magicalItemService;  // Composición

    private final ProtectiveRepository protectiveRepo;
    private final WizardRepository wizardRepo;

    @Autowired
    public ProtectiveService(MagicalItemRepository magicalItemRepo, WizardRepository wizardRepo, ProtectiveRepository protectiveRepo) {
        this.magicalItemService = new MagicalItemService(magicalItemRepo);
        this.wizardRepo = wizardRepo;
        this.protectiveRepo = protectiveRepo;
    }


    public boolean shopItem(Protective protective, Long wizardId) {

        Wizard wizard = wizardRepo.findWizardById(wizardId).get();
        int wizardMoney = wizard.getCoins();
        int itemPrice = protective.getPrice();
        int currentMoney = wizardMoney - itemPrice;
        if (currentMoney < 0) {
            return false;
        }
        wizard.setCoins(currentMoney);
        protective.setDisplayName(protective.name().toLowerCase() + " the " + protective.getDisplayName().toLowerCase());
        protectiveRepo.save(new ProtectiveItem(wizard, protective));
        return true;
    }
}
