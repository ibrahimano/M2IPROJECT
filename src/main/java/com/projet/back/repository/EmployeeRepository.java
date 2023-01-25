package com.projet.back.repository;


import com.projet.back.models.EmployeeRole;
import com.projet.back.models.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
    //Employees findEmployeeByEmployeeRole(String role);

    @Query("SELECT e FROM Employees e WHERE e.employeeRole = :role")
    List<Employees> findEmployeeByEmployeeRole(@Param("role") EmployeeRole role);

    @Query("SELECT e FROM Employees e WHERE LOWER(e.nom) LIKE CONCAT('%', LOWER(:nom), '%')")
    List<Employees> findByNomLikeIgnoreCase(@Param("nom") String nom);

   /* @Modifying
    @Transactional
    @Query("UPDATE Employees e SET e.nom = :nom, e.prenom = :prenom, e.cin = :cin, e.email = :email, e.Telephone = :telephone, e.Bureau = :bureau, e.password = :password, e.imageUrl = :imageUrl WHERE e.id = :id")
    int updateEmployee(@Param("id") Long id, @Param("nom") String nom, @Param("prenom") String prenom, @Param("cin") String cin, @Param("email") String email, @Param("telephone") String telephone, @Param("bureau") String bureau, @Param("password") String password, @Param("imageUrl") String imageUrl);
    */

}
