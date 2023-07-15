package com.spring30.spring310;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/interactions")
public class InteractionController {
    
    @Autowired
    private InteractionService interactionService;

    // CRUD
    // 1. CREATE

    @PostMapping(value="")
    public ResponseEntity<Interaction> createInteraction(@RequestBody Interaction interaction) {
        Interaction newInteraction = interactionService.createInteraction(interaction);
        
        return new ResponseEntity<>(newInteraction, HttpStatus.CREATED);
    }
    
    // 2. READ (Get and Get All)

    // Get All Interactions

    @GetMapping(value="")
    public ResponseEntity<ArrayList<Interaction>> getAllInteractions() {
        ArrayList<Interaction> allInteractions = interactionService.getAllInteractions();
        return new ResponseEntity<>(allInteractions, HttpStatus.OK);
    }

    // Get Interaction

    @GetMapping(value="/{id}")
    public ResponseEntity<Interaction> getInteraction(@PathVariable int id) {
        Interaction foundInteraction = interactionService.getInteraction(id);
        return new ResponseEntity<>(foundInteraction, HttpStatus.OK);
    }

    // 3. UPDATE

    @PutMapping(value="/{id}")
    public ResponseEntity<Interaction> updateInteraction(@PathVariable int id, @RequestBody Interaction interaction) {
        Interaction updatedInteraction = interactionService.updateInteraction(id, interaction);
        return new ResponseEntity<>(updatedInteraction, HttpStatus.OK);
    }

    // 4. DELETE

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Interaction> deleteInteraction(@PathVariable int id) {
        interactionService.deleteInteraction(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
