package com.example.wizardpedia.restControllers;

import com.example.wizardpedia.DTOs.ErrorDTO;
import com.example.wizardpedia.DTOs.SearchRequestDTO;
import com.example.wizardpedia.DTOs.WizardDTO;
import com.example.wizardpedia.Models.Wizard;
import com.example.wizardpedia.services.WizardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/wizards")
public class WizardRestController {
    private final WizardService wizardService;

    public WizardRestController(WizardService wizardService) {
        this.wizardService = wizardService;
    }

    /**
     * Search for wizard by ID.
     *
     * @param id The ID of the wizard to search for.
     * @return ResponseEntity a found wizard or an error message.
     */

    @GetMapping("/search")
    public ResponseEntity<?> searchWizardBy(@RequestParam Long id) {
        Optional<Wizard> maybeWizard = wizardService.getWizardById(id);

        if (maybeWizard.isPresent()) {
            Wizard wizard = maybeWizard.get();
            return ResponseEntity.ok(new WizardDTO(wizard));
        }else{
            return ResponseEntity.badRequest().body(new ErrorDTO("Put a valid id"));
        }
    }

/**
 * Search for wizards by name.
 *
 * @param wizardName The name of the wizard to search for.
 * @return ResponseEntity with a list of found wizards or an error message.
 */
    @PostMapping("/search")
    public ResponseEntity<?> searchWizardByName(@RequestBody SearchRequestDTO wizardName) {
        List<Wizard> wizardList = wizardService.getWizardsByName(wizardName.wizardName());

        if (wizardList.isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorDTO("Put a valid id"));
        }else{
            return ResponseEntity.ok(wizardList.stream().map(w -> new WizardDTO(w)).collect(Collectors.toList()));
        }
    }

    @PostMapping
    public ResponseEntity<Object> addWizard(@RequestBody Wizard wizard){
        return ResponseEntity.status(HttpStatus.CREATED).body(wizardService.add2(wizard.getName(), wizard.getAge()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateWizard(@PathVariable Long id, @RequestBody Wizard wizard){

        return ResponseEntity.status(HttpStatus.CREATED).body(wizardService.update(id, wizard.getName(), wizard.getAge()));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWizard(@PathVariable Long id){

        return ResponseEntity.ok(wizardService.deleteWizard(id));
    }
}
