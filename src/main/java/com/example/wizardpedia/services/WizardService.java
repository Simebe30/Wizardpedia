package com.example.wizardpedia.services;

import com.example.wizardpedia.Models.Wizard;
import com.example.wizardpedia.repositories.WizardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WizardService {

    @Autowired
    private WizardRepository wizardRepository;

    public WizardService(WizardRepository wizardRepository) {
        this.wizardRepository = wizardRepository;
    }

    public List<Wizard> getAllWizards(){
        return wizardRepository.findAll();
    }

    public void add(Wizard wizard){
        wizardRepository.save(wizard);
    }
    public Wizard add2(String name, int age){
        Wizard newWizard = new Wizard(name, age);
        wizardRepository.save(newWizard);
        return newWizard;
    }
    public Optional<Wizard> getWizard(Long id){
        return wizardRepository.findById(id);
    }

    public boolean deleteWizard(Long id){
        Optional<Wizard> wizard = wizardRepository.findById(id);
        if(wizard.isPresent()){
            wizardRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    public boolean update(Long id, String name, int age){
        Optional<Wizard> wizard = wizardRepository.findById(id);
        if(wizard.isEmpty()){
            return false;
        }
        wizard.get().setName(name);
        wizard.get().setAge(age);
         wizardRepository.save(wizard.get());
         return true;
    }
}
