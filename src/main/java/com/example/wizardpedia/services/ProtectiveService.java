package com.example.wizardpedia.services;

import com.example.wizardpedia.Models.MagicalItem;
import com.example.wizardpedia.Models.Protective;
import com.example.wizardpedia.Models.ProtectiveItem;
import com.example.wizardpedia.Models.Wizard;
import com.example.wizardpedia.repositories.MagicalItemRepository;
import com.example.wizardpedia.repositories.ProtectiveRepository;
import com.example.wizardpedia.repositories.WizardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProtectiveService implements MagicalItemService {

    private final MagicalItemRepository magicalItemRepository;
    private final WizardRepository wizardRepository;
    private final ProtectiveRepository protectiveRepository;

    @Autowired
    public ProtectiveService(MagicalItemRepository magicalItemRepository, WizardRepository wizardRepository, ProtectiveRepository protectiveRepository) {
        super();
        this.magicalItemRepository = magicalItemRepository;
        this.wizardRepository = wizardRepository;
        this.protectiveRepository = protectiveRepository;
    }

    @Override
    public ProtectiveItem shopItem(Protective protective, Long wizardId) {

        Wizard wizard = wizardRepository.findWizardById(wizardId).get();
        int wizardMoney = wizard.getCoins();
        int itemPrice = protective.getPrice();
        int currentWizardMoney = wizardMoney - itemPrice;
        String itemName = protective.name().toLowerCase() + " the " + protective.getDisplayName().toLowerCase();

        wizard.setCoins(currentWizardMoney);

        ProtectiveItem protectiveItem = new ProtectiveItem(wizard, itemName, protective.getPowerLevel(), protective);
        protectiveRepository.save(protectiveItem);
        return protectiveItem;
    }

    @Override
    public MagicalItem shopItem() {
        return null;
    }

    @Override
    public MagicalItem addItem(MagicalItem magicalItem) {
        if (magicalItem instanceof ProtectiveItem protectiveItem) {
            return protectiveRepository.save(protectiveItem);
        } else {
            return null;
        }
    }

    @Override
    public List<MagicalItem> getItemsByName(String itemName){
        List<MagicalItem> magicalItem =  magicalItemRepository.findMagicalItemByNameContainingIgnoreCase(itemName);
        if(magicalItem.isEmpty()){
            return new ArrayList<>();
        }
        return magicalItem;
    }

    @Override
    public boolean delete(Long id){
        Optional<MagicalItem> maybeItem= magicalItemRepository.findById(id);
        if(maybeItem.isEmpty()){
            return false;
        }else{
            magicalItemRepository.deleteById(id);
            return true;
        }
    }

    @Override
    public boolean updateMagicalItem(Long id, String name, int powerLevel){
        Optional<MagicalItem> maybeItem= Optional.ofNullable(magicalItemRepository.findMagicalItemById(id));
        if(maybeItem.isEmpty()){
            return false;
        }

        MagicalItem magicalItem = maybeItem.get();
        magicalItem.setName(name);
        magicalItem.setPowerLevel(powerLevel);
        magicalItemRepository.save(magicalItem);
        return true;
    }

    @Override
    public List<MagicalItem> getItemsByWizId(Long wizardId){
        return magicalItemRepository.findMagicalItemsByWizardId(wizardId);
    }

    @Override
    public int getTotalScore() {
        return magicalItemRepository.findAll().stream()
                .mapToInt(MagicalItem::getPowerLevel)
                .sum();
    }


}
