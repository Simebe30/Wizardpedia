package com.example.wizardpedia.services;

import com.example.wizardpedia.Models.MagicalItem;
import com.example.wizardpedia.Models.Wizard;
import com.example.wizardpedia.repositories.MagicalItemRepository;
import com.example.wizardpedia.repositories.WizardRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MagicalItemService {

    private final MagicalItemRepository magicalItemRepository;
    private final WizardRepository wizardRepository;

    public MagicalItemService(MagicalItemRepository magicalItemRepository, WizardRepository wizardRepository) {
        this.magicalItemRepository = magicalItemRepository;
        this.wizardRepository = wizardRepository;
    }


    public MagicalItem shopItem(MagicalItem magicalItem){


        Wizard wizard  = wizardRepository.findWizardById(magicalItem.getWizard().getId()).get();
        int wizardMoney = wizard.getCoins();
//        int itemPrice = protective.getPrice();
//        int currentWizardMoney = wizardMoney - itemPrice;

//        wizard.setCoins(currentWizardMoney);

        magicalItemRepository.save(magicalItem);
        return magicalItem;
    }

    public MagicalItem addItem(MagicalItem magicalItem){
        return magicalItemRepository.save(magicalItem);
    }

    public List<MagicalItem> getItemsByName(String itemName){
        List<MagicalItem> magicalItem =  magicalItemRepository.findMagicalItemByNameContainingIgnoreCase(itemName);
        if(magicalItem.isEmpty()){
            return new ArrayList<>();
        }
        return magicalItem;
    }

    public boolean delete(Long id){
        Optional<MagicalItem> maybeItem= magicalItemRepository.findById(id);
        if(maybeItem.isEmpty()){
            return false;
        }else{
            magicalItemRepository.deleteById(id);
            return true;
        }
    }

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

    public Optional<MagicalItem> getItemById(Long itemId) {
        return magicalItemRepository.findById(itemId);
    }

    public List<MagicalItem> getItemsByWizId(Long wizardId){
        return magicalItemRepository.findMagicalItemsByWizardId(wizardId);
    }

    public int getTotalScore(){
        return magicalItemRepository.findAll().stream()
                .mapToInt(MagicalItem::getPowerLevel)
                .sum();
    }
}
