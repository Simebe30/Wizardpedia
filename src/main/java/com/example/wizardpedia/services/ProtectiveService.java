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
public class ProtectiveService{

    private final MagicalItemService magicalItemService;  // Composición

    private final ProtectiveRepository protectiveRepo;
    private final WizardRepository wizardRepo;

    @Autowired
    public ProtectiveService(MagicalItemRepository magicalItemRepo, WizardRepository wizardRepo, ProtectiveRepository protectiveRepo) {
        this.magicalItemService = new MagicalItemService(magicalItemRepo);
        this.wizardRepo = wizardRepo;
        this.protectiveRepo = protectiveRepo;
    }


    public ProtectiveItem shopItem(Protective protective, Long wizardId) {

        Wizard wizard = wizardRepo.findWizardById(wizardId).get();
        int wizardMoney = wizard.getCoins();
        int itemPrice = protective.getPrice();
        int currentWizardMoney = wizardMoney - itemPrice;
        wizard.setCoins(currentWizardMoney);

        protective.setDisplayName(protective.name().toLowerCase() + " the " + protective.getDisplayName().toLowerCase());


        return protectiveRepo.save(new ProtectiveItem(wizard, protective));
    }
}
