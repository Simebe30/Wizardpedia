package com.example.wizardpedia.services;

import com.example.wizardpedia.Models.*;
import com.example.wizardpedia.repositories.MagicalItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void delete(Long id) {
        magicalItemRepository.findById(id)
                .ifPresent(item -> magicalItemRepository.deleteById(id));
    }

    public void updateMagicalItem(Long id, String name, int powerLevel) {
        magicalItemRepository.findMagicalItemById(id)
                .ifPresent(magicalItem -> {
                    magicalItem.setName(name);
                    magicalItem.setPowerLevel(powerLevel);
                    magicalItemRepository.save(magicalItem);
                });
    }

    public List<MagicalItem> getItemsByWizId(Long wizardId) {
        return magicalItemRepository.findMagicalItemsByWizardId(wizardId);
    }
}

