package com.projet.back.services;

import com.projet.back.models.Demande;
import com.projet.back.repository.DemandeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandeService {
    @Autowired
    private DemandeRepo demandeRepo;

    public DemandeService() {
    }

    public List<Demande> findAll() {
        return this.demandeRepo.findAll();
    }

    public List<Demande> findByEtat(int etat) {
        return this.demandeRepo.findByEtat(etat);
    }

    public List<Demande> getMaterielByEmployee(Long employeeId) {
        return this.demandeRepo.findByEmployeeId(employeeId);
    }

    public Demande findById(long id) {
        return (Demande)this.demandeRepo.findById(id).orElse(null);
    }

    public Demande save(Demande demande) {
        return (Demande)this.demandeRepo.save(demande);
    }

    public void deleteById(Long id) {
        this.demandeRepo.deleteById(id);
    }

    public Long countAllDemandes() {
        return demandeRepo.count();
    }
}

