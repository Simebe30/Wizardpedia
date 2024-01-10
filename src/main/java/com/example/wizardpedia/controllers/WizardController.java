package com.example.wizardpedia.controllers;

import com.example.wizardpedia.Models.MagicalItem;
import com.example.wizardpedia.Models.Wizard;
import com.example.wizardpedia.services.MagicalItemService;
import com.example.wizardpedia.services.WizardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/wizard")
public class WizardController {

    @Autowired
    private WizardService wizardService;

    @Autowired
    private MagicalItemService itemService;


    @GetMapping({"/list", "/list/"})
    public String index(Model model){
        model.addAttribute("wizards", wizardService.getAllWizards());
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam String searchInput, Model model) {
        List<Wizard> maybeWizards = wizardService.getByName(searchInput);
        List<MagicalItem> maybeItems = itemService.getItemsByName(searchInput);


        if (!maybeWizards.isEmpty() && !maybeItems.isEmpty()) {
                model.addAttribute("wizards", maybeWizards);
                model.addAttribute("items", maybeItems);
            return "listLinks";
        } else if (maybeWizards.size() == 1) {
            return "redirect:/wizard/" + searchInput;
        } else if (maybeItems.size() == 1) {
            return "redirect:/item/" + searchInput;
        } else{
            throw new RuntimeException("the input doesn't exist");
        }
    }
    @GetMapping("/{wizardName}")
    public String searchInput(@PathVariable(name = "wizardName") String wizardName, Model model){
        List<Wizard> wizards = wizardService.getByName(wizardName);

        if (wizards.isEmpty()) {
            throw new RuntimeException("the Wizard list is empty");
        }else if(wizards.size() == 1){
            model.addAttribute("wizard", wizards.get(0));
            return "wizardDetails";
        }else {
          throw new RuntimeException("the Wizard doesn't exist");
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
