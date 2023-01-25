package com.projet.back.controllers;

import com.projet.back.models.Demande;
import com.projet.back.models.Materiel;
import com.projet.back.services.DemandeService;
import com.projet.back.services.MaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping({"/demande"})
public class DemandeController {
    @Autowired
    private DemandeService demandeService;
    @Autowired
    private MaterielService materielService;


    public DemandeController() {
    }

    @GetMapping({"/all"})
    public List<Demande> findAll() {
        return this.demandeService.findAll();
    }

    @GetMapping({"/etat/{etat}"})
    public List<Demande> findByEtat(@PathVariable("etat") int etat) {
        return this.demandeService.findByEtat(etat);
    }

    @GetMapping({"/employee/materiel/{id}"})
    public List<Demande> findByEmployeeId(@PathVariable Long id) {
        return this.demandeService.getMaterielByEmployee(id);
    }

    @PostMapping({"/add"})
    public Demande createDemande(@RequestBody Demande demande) {
        return this.demandeService.save(demande);
    }

    @DeleteMapping({"/delete/{id}"})
    public void deleteDemande(@PathVariable("id") Long id) {
        this.demandeService.deleteById(id);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countAllDemandes() {
        long count = demandeService.countAllDemandes();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
    @PutMapping("/accepter/{id}")
    public Demande acceptDemande(@PathVariable(value = "id") long id) {
        Demande currentDemande = demandeService.findById(id);
        currentDemande.setEtat(1);
        Materiel currentMateriel = materielService.getMaterielById(currentDemande.getMateriel().getId());
        LocalDate today = LocalDate.now();
        currentMateriel.setDateAffectation(Date.valueOf(today));
        currentMateriel.setEmployee(currentDemande.getEmployee());

        materielService.updateMateriel(currentMateriel);
        return demandeService.save(currentDemande);
    }
    @PutMapping("/rejeter/{id}")
    public Demande rejectDemande(@PathVariable(value = "id") long id) {
        Demande currentDemande = demandeService.findById(id);
        currentDemande.setEtat(2);
        return demandeService.save(currentDemande);
    }
}
