package com.example.wizardpedia.services;

import com.example.wizardpedia.Models.*;
import com.example.wizardpedia.repositories.MagicalItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MagicalItemService {
    private final MagicalItemRepository magicalItemRepository;

    public MagicalItemService(MagicalItemRepository magicalItemRepository) {
        this.magicalItemRepository = magicalItemRepository;
    }

    public void save(MagicalItem magicalItem) {
        magicalItemRepository.save(magicalItem);
    }

    public List<MagicalItem> getItemsByName(String itemName) {
        return magicalItemRepository.findMagicalItemByNameContainingIgnoreCase(itemName);
    }

    public boolean delete(Long id) {
        Optional<MagicalItem> maybeItem = magicalItemRepository.findById(id);
        if (maybeItem.isEmpty()) {
            return false;
        } else {
            magicalItemRepository.deleteById(id);
            return true;
        }
    }

    public boolean updateMagicalItem(Long id, String name, int powerLevel) {
        Optional<MagicalItem> maybeItem = Optional.ofNullable(magicalItemRepository.findMagicalItemById(id));
        if (maybeItem.isEmpty()) {
            return false;
        }

        MagicalItem magicalItem = maybeItem.get();
        magicalItem.setName(name);
        magicalItem.setPowerLevel(powerLevel);
        magicalItemRepository.save(magicalItem);
        return true;
    }

    public List<MagicalItem> getItemsByWizId(Long wizardId) {
        return magicalItemRepository.findMagicalItemsByWizardId(wizardId);
    }
}

