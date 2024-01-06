package com.example.wizardpedia.controllers;

import com.example.wizardpedia.Models.MagicalItem;
import com.example.wizardpedia.services.MagicalItemService;
import com.example.wizardpedia.services.WizardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/item")
public class MagicalItemController {
    private final MagicalItemService magicalItemService;
    private final WizardService wizardService;

    public MagicalItemController(MagicalItemService magicalItemService, WizardService wizardService) {
        this.magicalItemService = magicalItemService;
        this.wizardService = wizardService;
    }

    @GetMapping("/search")
    public String searchByWizardId(@RequestParam Long wizardId) {
        return "redirect:/item/list/" + wizardId;
    }

    @GetMapping("/list/{wizardId}")
    public String getItemByWizardId(@PathVariable Long wizardId, Model model) {
        List<MagicalItem> items = magicalItemService.getItemsbyWizId(wizardId);
        if (items.isEmpty()) {
            return "redirect:/error";
        } else {
            model.addAttribute("items", items);
            return "itemDetails";
        }
    }

    @GetMapping("/add")
    public String getAddItem(Model model) {
        model.addAttribute("wizards", wizardService.getAllWizards());

        return "addItem";
    }

    @PostMapping("/add")
    public String addItemSubmit(MagicalItem magicalItem) {
        magicalItemService.addItem(magicalItem);
        return "redirect:/wizard/list";
    }

    @PutMapping("/list/{wizardId}/{id}")
    public String updateItem(@PathVariable Long wizardId,
                             @PathVariable Long id,
                             MagicalItem magicalItem){

        magicalItemService.updateMagicalItem(id, magicalItem.getName(), magicalItem.getPowerLevel());
        return "redirect:/item/list/" + wizardId;
    }

    @DeleteMapping("/list/{wizardId}/{id}")
    public String deleteItem(@PathVariable Long wizardId, @PathVariable Long id){
        magicalItemService.delete(id);
        return "redirect:/item/list/" + wizardId;

    }

}
