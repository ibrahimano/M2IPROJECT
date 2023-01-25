package com.projet.back.repository;

import com.projet.back.models.Materiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface MaterielRepo extends JpaRepository<Materiel, Long> {
    List<Materiel> findByEmployeeId(Long employeeId);

    List<Materiel> findByTypeContaining(String type);

    List<Materiel> findByDateAffectationContaining(String date);

    List<Materiel> findByDateAffectation(Date dateAffectation);

    List<Materiel> findByDateAffectationIsNull();

    List<Materiel> findByDateAffectationIsNotNull();

    @Query("SELECT m FROM Materiel m WHERE m.dateAffectation IS NOT NULL AND LOWER(m.type) LIKE CONCAT('%', LOWER(:type), '%')")
    List<Materiel> findByTypeAndDateAffectationNotNull(@Param("type") String type);

    @Query("SELECT m FROM Materiel m WHERE m.dateAffectation IS NULL AND LOWER(m.type) LIKE CONCAT('%', LOWER(:type), '%')")
    List<Materiel> findByTypeAndDateAffectationIsNull(@Param("type") String type);

    //rechercher les materiels affectee à un utilisateur
    @Query("SELECT m FROM Materiel m WHERE m.dateAffectation IS NOT NULL AND LOWER(m.type) LIKE CONCAT('%', LOWER(:type), '%') AND m.employee.id = :employeeId")
    List<Materiel> findByTypeAndDateAffectationNotNullAndEmployeeId(@Param("type") String type, @Param("employeeId") Long employeeId);

}
