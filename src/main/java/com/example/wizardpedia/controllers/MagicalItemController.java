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
    public String searchByItemName(String itemName) {
        return "redirect:/item/list/" + itemName;
    }

    @GetMapping("/list/{itemName}")
    public String getByItemName(@PathVariable String itemName, Model model) {
        List<MagicalItem> items = magicalItemService.getItemsByItemName(itemName);
        if (items.isEmpty()) {
            return "redirect:/error";
        } else if(items.size() == 1) {
            MagicalItem item = items.get(0);

            model.addAttribute("item", item);

            return "redirect:/list/" + item.getWizard().getName() + "/" + item.getId();
        }else{
            model.addAttribute("items", items);
            return "listLinks";
        }
    }

    @GetMapping("/list/{wizardId}/{itemId}")
    public String getItemDetails(){
        return "itemDetails";
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
