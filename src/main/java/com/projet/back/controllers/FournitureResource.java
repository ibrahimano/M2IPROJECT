package com.projet.back.controllers;

import com.projet.back.models.Fourniture;
import com.projet.back.services.FournitureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/fourniture"})
public class FournitureResource {
    @Autowired
    private FournitureServiceImpl fournitureService;

    //seul admin peut effectuer ses fonctions
    public FournitureResource() {
    }

    //afficher tous les fournitures
    @GetMapping({"/all"})
    public List<Fourniture> getAllFournitures() {
        return this.fournitureService.findAll();
    }

    //rechercher un fourniture
    @GetMapping({"/search"})
    public List<Fourniture> search(@RequestParam("type") String type) {
        return this.fournitureService.searchByType(type);
    }

    //trouver un fourniture par id
    @GetMapping({"/find/{id}"})
    public Fourniture getFournitureById(@PathVariable("id") long id) {
        return this.fournitureService.findById(id);
    }

    //ajouter un fourniture
    @PostMapping({"/add"})
    public Fourniture createFourniture(@RequestBody Fourniture fourniture) {
        return this.fournitureService.save(fourniture);
    }

    //modifier les information ou le nombre d'un fourniture
    @PutMapping({"/update/{id}"})
    public Fourniture updateFourniture(@PathVariable("id") long id, @RequestBody Fourniture fourniture) {
        Fourniture currentFourniture = this.fournitureService.findById(id);
        currentFourniture.setType(fourniture.getType());
        currentFourniture.setNombre(fourniture.getNombre());
        return this.fournitureService.save(currentFourniture);
    }

    //supprimer les fournitures
    @DeleteMapping({"/delete/{id}"})
    public void deleteFourniture(@PathVariable("id") long id) {
        this.fournitureService.deleteById(id);
    }

    //compter le nombre des fournitures
    @GetMapping("/count")
    public ResponseEntity<Long> countAllFournitures() {
        long count = fournitureService.countAllFournitures();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
