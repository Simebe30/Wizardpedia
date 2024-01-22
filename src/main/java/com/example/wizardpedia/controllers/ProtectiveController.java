package com.example.wizardpedia.controllers;

import com.example.wizardpedia.Models.Protective;
import com.example.wizardpedia.Models.ProtectiveItem;
import com.example.wizardpedia.Models.Wizard;
import com.example.wizardpedia.services.ProtectiveService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/protective")
public class ProtectiveController {

    private final ProtectiveService protectiveService;

    public ProtectiveController(ProtectiveService protectiveService) {
        this.protectiveService = protectiveService;
    }


    @PostMapping("/shop")
    public String processShopFormProtective(@ModelAttribute Protective protective, @RequestParam Long wizardId) {
        protectiveService.shopItem(protective, wizardId);
        return "redirect:/wizard/list";
    }


}
