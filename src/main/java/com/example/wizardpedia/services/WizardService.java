package com.example.wizardpedia.services;

import com.example.wizardpedia.Models.Wizard;
import com.example.wizardpedia.repositories.MagicalItemRepository;
import com.example.wizardpedia.repositories.WizardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WizardService {

    private final WizardRepository wizardRepository;

    public WizardService(WizardRepository wizardRepository, MagicalItemRepository magicalItemRepository) {
        this.wizardRepository = wizardRepository;
    }

    public List<Wizard> getAllWizards() {
        return wizardRepository.findAll();
    }

    public Optional<Wizard> getWizardById(Long id) {
        return wizardRepository.findById(id);
    }

    public List<Wizard> getWizardsByName(String name) {
        return wizardRepository.findWizardsByNameContainingIgnoreCase(name);
    }


    public Wizard add(Wizard wizard) {
        return wizardRepository.save(wizard);
    }

    public boolean delete(Long id) {
        return wizardRepository.findById(id).map(w -> {
            wizardRepository.deleteById(id);
            return true;
        }).orElse(false);
    }

    public boolean update(Long id, Wizard wizard) {
        return wizardRepository.findById(id).map(w -> {
            w.setName(wizard.getName());
            w.setAge(wizard.getAge());
            wizardRepository.save(w);
            return true;
        }).orElse(false);
    }
}
