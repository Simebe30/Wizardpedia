package com.example.wizardpedia.controllers;

import com.example.wizardpedia.Models.MagicalItem;
import com.example.wizardpedia.Models.Wizard;
import com.example.wizardpedia.services.WizardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/wizard")
public class WizardController {

    @Autowired
    private WizardService wizardService;


    @GetMapping({"/list", "/list/"})
    public String index(Model model){
        model.addAttribute("wizards", wizardService.getAllWizards());
        return "index";
    }

    //get que busca segun el numero que le hemos introducido al searchbar, como escribimos manualmente y lo queremos conectar al controller con html se escribe requestparam
    //al terminar de buscar te redirige al get de abajo donde se busca en el localhost
    @GetMapping("/search")
    public String searchWizardById(@RequestParam String wizardName) {
        return "redirect:/wizard/" + wizardName;
    }

    //get que busca wizard arriba en el local host (por eso se necesita @PathVariable)
    @GetMapping("/{wizardName}")
    public String getWizardById(@PathVariable(name = "wizardName") String wizardName, Model model){
        Optional<Wizard> maybeWizard = wizardService.getByName(wizardName);
        if (maybeWizard.isPresent()) {
            model.addAttribute("wizard", maybeWizard.get());
            return "wizardDetails";
        } else {
            return "redirect:/error";
        }
    }

    @PostMapping("/list/add")
    public String addWizardSubmit(Wizard wizard){

        wizardService.add(wizard);
        return "redirect:./";
    }

    @PutMapping("/list/{id}")
    public String updateWizard(@PathVariable Long id, Wizard wizard){
        wizardService.update(id, wizard.getName(), wizard.getAge());
        return "redirect:./";
    }


    @DeleteMapping("/list/{id}")
    public String deleteWizard(@PathVariable Long id){
        wizardService.deleteWizard(id);
        return "redirect:./";
    }
}
