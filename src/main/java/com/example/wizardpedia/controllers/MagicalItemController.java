package com.example.wizardpedia.controllers;

import com.example.wizardpedia.Models.MagicalItem;
import com.example.wizardpedia.Models.Protective;
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

    @GetMapping("/{itemName}")
    public String getIndividualItemByName(@PathVariable String itemName, Model model) {
        List<MagicalItem> items = magicalItemService.getItemsByName(itemName);

        if (items.isEmpty()) {
            throw new RuntimeException("the item list is empty");
        }else if(items.size() == 1){
            model.addAttribute("item", items.get(0));
            return "individualItemsDetails";
        }else {
            throw new RuntimeException("the item doesn't exist");
        }
    }

    @GetMapping("/list/{wizardId}")
    public String getItemsDetails(@PathVariable Long wizardId, Model model){
        List<MagicalItem> items = magicalItemService.getItemsByWizId(wizardId);
        if (items.isEmpty()) {
            throw new RuntimeException("the item list is empty");
        }
        model.addAttribute("items", items);
        return "itemDetails";
    }


    @GetMapping("/shop")
    public String getAddItem(Model model) {
        model.addAttribute("wizards", wizardService.getAllWizards());
        model.addAttribute("protectives", Protective.values());
        return "shopItem";
    }

    @PostMapping("/shop")
    public String addItemSubmit(MagicalItem magicalItem) {
        magicalItemService.shopItem(magicalItem);
        return "redirect:/wizard/list";
    }

    @PutMapping("/list/{wizardName}/{itemId}")
    public String updateItem(@PathVariable String wizardName,
                             @PathVariable Long itemId,
                             MagicalItem magicalItem){

        magicalItemService.updateMagicalItem(itemId, magicalItem.getName(), magicalItem.getPowerLevel());
        return "redirect:/item/list/" + wizardName + "/" + itemId;
    }

    @DeleteMapping("/list/{wizardId}/{itemId}")
    public String deleteItem(@PathVariable String wizardId, @PathVariable Long itemId){
        magicalItemService.delete(itemId);
        return "redirect:/item/list/" + wizardId;

    }

}
