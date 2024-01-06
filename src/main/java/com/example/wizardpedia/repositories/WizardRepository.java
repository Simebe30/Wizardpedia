package com.example.wizardpedia.repositories;

import com.example.wizardpedia.Models.Wizard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WizardRepository extends JpaRepository<Wizard,Long> {

    //Wizard findWizardById(Long id);
}
