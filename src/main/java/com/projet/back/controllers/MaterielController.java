package com.projet.back.controllers;

import com.projet.back.models.Employees;
import com.projet.back.models.Materiel;
import com.projet.back.services.MaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping({"/materiel"})
public class MaterielController {
    @Autowired
    private MaterielService materielService;

    public MaterielController() {
    }

    //ajouter un nouveau materiel
    @PostMapping({"/add"})
    public ResponseEntity<Materiel> createMateriel(@RequestBody Materiel materiel) {
        Materiel createdMateriel = this.materielService.createMateriel(materiel);
        return new ResponseEntity(createdMateriel, HttpStatus.CREATED);
    }

    //trouver un materiel par id
    @GetMapping({"/find/{id}"})
    public ResponseEntity<Materiel> getMaterielById(@PathVariable Long id) {
        Materiel materiel = this.materielService.getMaterielById(id);
        return materiel == null ? new ResponseEntity(HttpStatus.NOT_FOUND) : new ResponseEntity(materiel, HttpStatus.OK);
    }

    //trouver les materiels affecter à emplyee par id
    @GetMapping({"/find/employee/{id}"})
    public List<Materiel> findByEmployeeId(@PathVariable Long id) {
        return this.materielService.getMaterielByEmployee(id);
    }

    //rechercher les materiel par type
    @GetMapping({"/search"})
    public List<Materiel> search(@RequestParam("type") String type) {
        return this.materielService.searchByType(type);
    }

    //rechercher dans les materiel affecter aux employees par type
    @GetMapping({"/searchAffectation"})
    public List<Materiel> searchInMaterielAffecte(@RequestParam("type") String type) {
        return this.materielService.findByTypeAndDateAffectationNotNull(type);
    }

    //rechercher dans les materiel affecter à un seul employee par type materiel et id employee
    @GetMapping({"/employee/searchAffectation"})
    public List<Materiel> findByTypeAndDateAffectationNotNullAndEmployeeId(@RequestParam("type") String type, @Param("employeeId") Long employeeId) {
        return this.materielService.findByTypeAndDateAffectationNotNull(type);
    }
    //rechercher entre les materiels disponibles par type
    @GetMapping({"/searchDisponible"})
    public List<Materiel> searchInMaterielDisponible(@RequestParam("type") String type) {
        return this.materielService.findByTypeAndDateAffectationIsNull(type);
    }
    //rechercher par date d'affectation
    @GetMapping({"/dateAffectation"})
    public List<Materiel> searchdate(@RequestParam("date") String date) {
        return this.materielService.searchByDateAffectation(date);
    }
    //rechercher les materiel affecter
    @GetMapping({"/dateAffectation/null"})
    public List<Materiel> searchdateNull() {
        return this.materielService.searchByDateAffectationIsNull();
    }

    //rechercher les materiel non affecter i.e disponible
    @GetMapping({"/dateAffectation/not-null"})
    public List<Materiel> searchdateNotNull() {
        return this.materielService.searchByDateAffectationNotNull();
    }

    //avoir tous les materiels
    @GetMapping({"/all"})
    public List<Materiel> getAllMateriels() {
        return this.materielService.getAllMateriels();
    }

    //supprimer un materiel
    @DeleteMapping({"/delete/{id}"})
    public ResponseEntity<Void> deleteMateriel(@PathVariable Long id) {
        this.materielService.deleteMateriel(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    //compter le nombre de materiels
    @GetMapping("/count")
    public ResponseEntity<Long> countAllMateriels() {
        long count = materielService.countAllMateriels();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
    //affecter un materiel par id materiel et employee
    @PutMapping("/affecter/{id}")
    public Materiel affecterMateriel(@PathVariable long id,@RequestBody Employees employee){
        Materiel materiel = materielService.getMaterielById(id);
        materiel.setEmployee(employee);
        // date affectation
        LocalDate today = LocalDate.now();
        materiel.setDateAffectation(Date.valueOf(today));
        return materielService.updateMateriel(materiel);
    }
}

