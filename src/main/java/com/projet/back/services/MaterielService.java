package com.projet.back.services;

import com.projet.back.models.Employees;
import com.projet.back.models.Materiel;
import com.projet.back.repository.MaterielRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class MaterielService {
    @Autowired
    private MaterielRepo materielRepository;

    public MaterielService() {
    }

    public Materiel createMateriel(Materiel materiel) {
        return (Materiel)this.materielRepository.save(materiel);
    }

    public Materiel getMaterielById(Long id) {
        return materielRepository.findById(id).orElse(null); //(Materiel)
    }

    public List<Materiel> getMaterielByEmployee(Long employeeId) {
        return this.materielRepository.findByEmployeeId(employeeId);
    }

    @Transactional(
            readOnly = true
    )
    public List<Materiel> searchByDateAffectation(String date) {
        //return this.materielRepository.findByDateAffectationContaining(date);
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = formatter.parse(date);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            return this.materielRepository.findByDateAffectation(sqlDate);

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Materiel> searchByDateAffectationIsNull() {
        return this.materielRepository.findByDateAffectationIsNull();
    }

    public List<Materiel> searchByDateAffectationNotNull() {
        return this.materielRepository.findByDateAffectationIsNotNull();
    }

    @Transactional(
            readOnly = true
    )
    public List<Materiel> searchByType(String type) {
        return this.materielRepository.findByTypeContaining(type);
    }

    public List<Materiel> getAllMateriels() {
        return this.materielRepository.findAll();
    }

    public Materiel updateMateriel(Materiel materiel) {
        return (Materiel)this.materielRepository.save(materiel);
    }

    public void deleteMateriel(Long id) {
        this.materielRepository.deleteById(id);
    }

    public Long countAllMateriels() {
        return materielRepository.count();
    }

    public List<Materiel> findByTypeAndDateAffectationNotNull(String type) {
        return materielRepository.findByTypeAndDateAffectationNotNull(type);
    }

    public List<Materiel> findByTypeAndDateAffectationNotNullAndEmployeeId(String type, Long employeeId) {
        return materielRepository.findByTypeAndDateAffectationNotNullAndEmployeeId(type, employeeId);
    }
    public List<Materiel> findByTypeAndDateAffectationIsNull(String type) {
        return materielRepository.findByTypeAndDateAffectationIsNull(type);
    }
}
