package com.example.wizardpedia.repositories;

import com.example.wizardpedia.Models.OffensiveItem;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffensiveRepository extends ListCrudRepository<OffensiveItem, Long> {
}
