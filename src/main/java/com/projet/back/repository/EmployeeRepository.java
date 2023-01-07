package com.projet.back.repository;


import com.projet.back.models.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employees, Long> {
    void deleteEmployeeById(Long id);
    Optional<Employees> findEmployeeById(Long id);
    Employees findEmployeeByEmailAndPassword(String email, String password);
    Employees findEmployeeByEmail(String email);
    Employees findEmployeeByEmployeeRole(String role);

    @Query("SELECT e FROM Employees e WHERE LOWER(e.nom) LIKE CONCAT('%', LOWER(:nom), '%')")
    List<Employees> findByNomLikeIgnoreCase(@Param("nom") String nom);


}
