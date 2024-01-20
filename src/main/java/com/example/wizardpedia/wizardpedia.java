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
public class wizardpedia{


    public static void main(String[] args) {
        SpringApplication.run(wizardpedia.class, args);
    }

}
