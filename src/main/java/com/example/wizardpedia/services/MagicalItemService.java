package com.example.wizardpedia.services;

import com.example.wizardpedia.Models.MagicalItem;
import com.example.wizardpedia.Models.Wizard;
import com.example.wizardpedia.repositories.MagicalItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MagicalItemService {
    @Autowired
    private MagicalItemRepository magicalItemRepository;


    public MagicalItem addItem(MagicalItem magicalItem){

        magicalItemRepository.save(magicalItem);
        return magicalItem;
    }

    public Optional<MagicalItem> getItemsByItemName(String itemName){
        return magicalItemRepository.findMagicalItemByNameContainingIgnoreCase(itemName);
    }

    public MagicalItem addItem(String name, int powerLevel, Wizard wizard) {
        return magicalItemRepository.save(new MagicalItem(name, powerLevel, wizard));
    }

    public Object addItem(String name, int powerLevel) {
        return magicalItemRepository.save(new MagicalItem(name, powerLevel));
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
}
