package com.projet.back.repository;

import com.projet.back.models.Demande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemandeRepo extends JpaRepository<Demande, Long> {
    List<Demande> findByEtat(int etat);

    List<Demande> findByEmployeeId(Long EmployeeId);
}