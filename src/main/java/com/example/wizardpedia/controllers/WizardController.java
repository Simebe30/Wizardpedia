package com.example.wizardpedia.controllers;

import com.example.wizardpedia.Models.MagicalItem;
import com.example.wizardpedia.Models.Wizard;
import com.example.wizardpedia.services.MagicalItemService;
import com.example.wizardpedia.services.WizardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/wizard")
public class WizardController {

    private final WizardService wizardService;

    private final MagicalItemService itemService;

    public WizardController(WizardService wizardService, MagicalItemService itemService) {
        this.wizardService = wizardService;
        this.itemService = itemService;
    }


    @GetMapping({"/list", "/list/"})
    public String index(Model model){
        model.addAttribute("wizards", wizardService.getAllWizards());
        model.addAttribute("totalScore", itemService.getTotalScore());
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam String searchInput, Model model, RedirectAttributes re) {
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
            re.addFlashAttribute("error", "the input doesn't exist");
            return "redirect:./list/";
        }
    }
    @GetMapping("/{wizardName}")
    public String searchInput(@PathVariable(name = "wizardName") String wizardName, Model model, RedirectAttributes re){
        List<Wizard> wizards = wizardService.getByName(wizardName);

        if (wizards.isEmpty()) {
            throw new RuntimeException("the Wizard list is empty");
        }else if(wizards.size() == 1){
            model.addAttribute("wizard", wizards.get(0));
            return "wizardDetails";
        }else {
            re.addFlashAttribute("error", "the Wizard doesn't exist");
            return "index";
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
