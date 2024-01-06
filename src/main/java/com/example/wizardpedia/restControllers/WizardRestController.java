package com.example.wizardpedia.restControllers;

import com.example.wizardpedia.DTOs.ErrorDTO;
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


    //hacer search by get --requestparam
    @GetMapping("/search") //con el request param se ve asi GET /search?Id=123
    public ResponseEntity<?> searchWizardBy(@RequestParam Long id) {
        Optional<Wizard> maybeWizard = wizardService.getWizard(id);

        if (maybeWizard.isPresent()) {
            Wizard wizard = maybeWizard.get();
            return ResponseEntity.ok(new WizardDTO(wizard));
        }else{
            return ResponseEntity.badRequest().body(new ErrorDTO("Put a valid id"));
        }
    }

    //hacer search by post --requestBody
//    @PostMapping("/search") //se tiene que crear un SearchRequestDTO que contenga un Long id, como este DTO solo tiene un parametro que es el id, al escribir el body en el postman solo necesitamos un valor que es el del id, si el requestbody fuese de WizardDTO se necesitarian 3 parametros en el body
//    public ResponseEntity<?> searchWizardByPost(@RequestBody SearchRequestDTO request) {
//        Long id = request.getId();
//        Wizard wizard = wizardService.getWizard(id);
//
//        if (wizard != null) {
//            return ResponseEntity.ok().body(new WizardDTO(wizard.getId(), wizard.getName(), wizard.getAge()));
//        } else {
//            return ResponseEntity.badRequest().body(new ErrorDTO("Put a valid id"));
//        }
//    }

    @GetMapping
    public List<WizardDTO> getAllWizards() {
        List<Wizard> wizards = wizardService.getAllWizards();
        return wizards.stream()
                .map(wizard -> new WizardDTO(wizard))
                .collect(Collectors.toList());
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
