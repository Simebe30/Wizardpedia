package com.example.wizardpedia.controllers;

import com.example.wizardpedia.services.OffensiveService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/offensive")
public class OffensiveController {

    private final OffensiveService offensiveService;

    public OffensiveController(OffensiveService offensiveService) {
        this.offensiveService = offensiveService;
    }

    @PostMapping("/shop")
    public String processShopFormOffensive(@RequestParam Long wizardIdOffensive) {
        offensiveService.shopItem(wizardIdOffensive);
        return "redirect:/wizard/list";
    }
}
