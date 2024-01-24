package com.example.wizardpedia.repositories;

import com.example.wizardpedia.Models.MagicalItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MagicalItemRepository extends JpaRepository<MagicalItem, Long> {

    MagicalItem findMagicalItemById(Long id);

    List<MagicalItem> findMagicalItemsByWizardId(Long wizardId);

    List<MagicalItem> findMagicalItemByNameContainingIgnoreCase(String itemName);


}
