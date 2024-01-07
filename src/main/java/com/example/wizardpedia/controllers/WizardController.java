package com.example.wizardpedia.controllers;

import com.example.wizardpedia.Models.MagicalItem;
import com.example.wizardpedia.Models.Wizard;
import com.example.wizardpedia.services.WizardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/search")
    public String search(@RequestParam String searchTerm, @RequestParam String searchType) {
        if ("wizards".equals(searchType)) {
            return "redirect:/wizard/" + searchTerm;
        } else if ("items".equals(searchType)) {
            return "redirect:/item/" + searchTerm;
        } else {
            return "redirect:/error";
        }
    }
    //get que busca wizard arriba en el local host (por eso se necesita @PathVariable)
    @GetMapping("/{wizardName}")
    public String getWizardById(@PathVariable(name = "wizardName") String wizardName, Model model){
        List<Wizard> wizards = wizardService.getByName(wizardName);

        if (wizards.isEmpty()) {
            return "redirect:/error";
        }else if(wizards.size() == 1){
            model.addAttribute("wizard", wizards.get(0));
            return "wizardDetails";
        }else {
            model.addAttribute("wizards", wizards);
            return "listLinks";
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
