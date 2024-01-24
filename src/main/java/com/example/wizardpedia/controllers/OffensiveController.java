package com.example.wizardpedia.controllers;

import com.example.wizardpedia.services.OffensiveService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/offensive")
public class OffensiveController {

    private final OffensiveService offensiveService;

    public OffensiveController(OffensiveService offensiveService) {
        this.offensiveService = offensiveService;
    }

    @PostMapping("/shop")
    public String processShopFormOffensive(@RequestParam Long wizardIdOffensive, RedirectAttributes re) {
        boolean wasBought = offensiveService.shopItem(wizardIdOffensive);

        if(wasBought){
            re.addFlashAttribute("message", "The item was successfully purchased");
            return "redirect:/wizard/list";
        }
        re.addFlashAttribute("errorMessage", "the wizard doesn't have enough treasure, please try again.");
        return "redirect:/item/shop";
    }
}
