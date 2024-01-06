package com.example.wizardpedia;

import com.example.wizardpedia.Models.MagicalItem;
import com.example.wizardpedia.Models.Wizard;
import com.example.wizardpedia.repositories.MagicalItemRepository;
import com.example.wizardpedia.repositories.WizardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class wizardpedia implements CommandLineRunner {

    @Autowired
    private WizardRepository wizardRepository;

    @Autowired
    private MagicalItemRepository magicalItemRepository;

    public static void main(String[] args) {
        SpringApplication.run(wizardpedia.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        var w1 = new Wizard("The Great Marvelous", 20);
//        var w2 = new Wizard("The Unnameable", 43);
//        wizardRepository.saveAll(List.of(w1, w2));
//
//        var m1 = new MagicalItem("The Sword of Death", 25);
//        var m2 = new MagicalItem("The Infernal Ark", 55);
//        var m3 = new MagicalItem("The Fire of Death", 71);
//        var m4 = new MagicalItem("The Silent One", 5);
//        var m5 = new MagicalItem("The Eye", 93);
//
//
//        m2.setWizard(w1);
//        m4.setWizard(w1);
//        m1.setWizard(w2);
//        m3.setWizard(w2);
//        m4.setWizard(w2);
//        m5.setWizard(w2);
//
//        magicalItemRepository.saveAll(List.of(m1, m2, m3, m4, m5));


    }
}
