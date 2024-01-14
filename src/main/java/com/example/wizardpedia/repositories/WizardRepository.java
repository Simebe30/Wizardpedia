package com.example.wizardpedia.repositories;

import com.example.wizardpedia.Models.Wizard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WizardRepository extends JpaRepository<Wizard,Long> {
    List<Wizard> findWizardsByNameContainingIgnoreCase(String name);

}
