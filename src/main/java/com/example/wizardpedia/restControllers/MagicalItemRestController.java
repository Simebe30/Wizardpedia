package com.example.wizardpedia.restControllers;

import com.example.wizardpedia.DTOs.ErrorDTO;
import com.example.wizardpedia.DTOs.MagicalItemDTO;
import com.example.wizardpedia.Models.MagicalItem;
import com.example.wizardpedia.Models.Wizard;
import com.example.wizardpedia.services.MagicalItemService;
import com.example.wizardpedia.services.WizardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/items")
public class MagicalItemRestController {

    private final MagicalItemService magService;

    private final WizardService wizardService;

    public MagicalItemRestController(MagicalItemService magService, WizardService wizardService) {
        this.magService = magService;
        this.wizardService = wizardService;
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchByWizardId(@RequestParam Long wizardId){
        List<MagicalItem> items =magService.getItemsByWizId(wizardId);
        if(items.isEmpty()){
           return ResponseEntity.notFound().build();
        }else{
            List<MagicalItemDTO> itemsDTOs = items.stream()
                    .map(m -> new MagicalItemDTO(m))
                    .toList();
            return ResponseEntity.ok(itemsDTOs);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody Optional<MagicalItem> item, @RequestParam Long wizardId){

        //buscas el wizard segun el id que has escrito en el RequestParam POST /add?wizardId=1
        Optional<Wizard> wizard = wizardService.getWizard(wizardId);
        //compruebas que el wizard y el item existen     y sino existe se para la aplicacion
        if(wizard.isEmpty()){
            return ResponseEntity.badRequest().body(new ErrorDTO("The wizard doesn't exist"));
        } else if (item.isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorDTO("The item doesn't exist"));
        }
        //le añades el wizard al item que hemos creado en el requestBody {
        //  "name":" sip",
        //  "powerLevel" : 70
        //}
        item.get().setWizard(wizard.get());
        //Añadimos el item al service y lo nombramos addedItem
        MagicalItem addedItem = magService.addItem(item.get());
        //creamos un MagicalItemDTO y le añadimos el nombre y el poder que le habiamos escrito en el requestBody
        return ResponseEntity.status(HttpStatus.CREATED).body(new MagicalItemDTO(addedItem));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateItem(@PathVariable Long id, @RequestParam String name, @RequestParam int powerLevel){
        return ResponseEntity.ok(magService.updateMagicalItem(id, name, powerLevel));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id){
        return ResponseEntity.ok(magService.delete(id));//si ha borrado correctamente el item pondra correcto si no le ha salido falso
    }
}
