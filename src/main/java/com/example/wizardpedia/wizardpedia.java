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

        var w1= new Wizard("El gran maravilloso", 20);
        var w2= new Wizard("EL innombrable", 43);
        wizardRepository.saveAll(List.of(w1, w2));

        var m1 = new MagicalItem("la espada de la muerte", 25);
        var m2 = new MagicalItem("el arca infernal", 55);
        var m3 = new MagicalItem("el fuego de la muerte", 71);
        var m4 = new MagicalItem("el silencioso", 5);
        var m5 = new MagicalItem("el ojo", 93);

        m2.setWizard(w1);
        m4.setWizard(w1);
        m1.setWizard(w2);
        m3.setWizard(w2);
        m4.setWizard(w2);
        m5.setWizard(w2);

        magicalItemRepository.saveAll(List.of(m1, m2, m3, m4, m5));


    }
}
