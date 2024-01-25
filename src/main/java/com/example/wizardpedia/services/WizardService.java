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

    /**
     * Retrieves a list of all wizards.
     *
     * @return A list of all wizards.
     */
    public List<Wizard> getAllWizards() {
        return wizardRepository.findAll();
    }

    /**
     * Retrieves a wizard by ID.
     *
     * @param id The ID of the wizard.
     * @return An optional containing the wizard if found, or an empty optional otherwise.
     */
    public Optional<Wizard> getWizardById(Long id) {
        return wizardRepository.findById(id);
    }

    /**
     * Retrieves wizards by name (case-insensitive).
     *
     * @param name The name to search for.
     * @return A list of wizards matching the provided name.
     */
    public List<Wizard> getWizardsByName(String name) {
        return wizardRepository.findWizardsByNameContainingIgnoreCase(name);
    }

    /**
     * Adds a new wizard.
     *
     * @param wizard The wizard to be added.
     * @return The newly added wizard.
     */
    public Wizard add(Wizard wizard) {
        return wizardRepository.save(wizard);
    }

    /**
     * Deletes a wizard by ID.
     *
     * @param id The ID of the wizard to be deleted.
     * @return true if the wizard was found and deleted, false otherwise.
     */
    public boolean delete(Long id) {
        return wizardRepository.findById(id).map(w -> {
            wizardRepository.deleteById(id);
            return true;
        }).orElse(false);
    }

    /**
     * Updates a wizard's information.
     *
     * @param id     The ID of the wizard to be updated.
     * @param wizard The updated wizard information.
     * @return true if the wizard was found and updated, false otherwise.
     */
    public boolean update(Long id, Wizard wizard) {
        return wizardRepository.findById(id).map(w -> {
            w.setName(wizard.getName());
            w.setAge(wizard.getAge());
            wizardRepository.save(w);
            return true;
        }).orElse(false);
    }
}
