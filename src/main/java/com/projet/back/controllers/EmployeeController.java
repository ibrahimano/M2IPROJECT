package com.projet.back.controllers;


import com.projet.back.models.Employees;
import com.projet.back.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAnyRole('Chef', 'Adjoint')")
@RequestMapping("/admin/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public EmployeeController(EmployeeService employeeService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.employeeService = employeeService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    //contient seulement les fonctions effectuer par l'admin

    //avoir la liste des utilisateurs (employees)
    @GetMapping("/all")
    public ResponseEntity<List<Employees>> getAllEmployees() {
        List<Employees> employees = employeeService.findAllEmployees();
        return  new ResponseEntity<>(employees, HttpStatus.OK);
    }

    //trouver un employee par son id
    @GetMapping("/find/{id}")
    public ResponseEntity<Employees> getEmployeeById(@PathVariable("id") Long id) {
        Employees employee = employeeService.findEmployeeById(id);
        return  new ResponseEntity<>(employee, HttpStatus.OK);
    }
    //ajouter un empoyee
    @PostMapping("/add")
    public ResponseEntity<Employees> addEmployee(@RequestBody Employees employee) {

        String encodedPassword = bCryptPasswordEncoder
                .encode(employee.getPassword());

        employee.setPassword(encodedPassword);

        Employees newEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }
    //modiifer les information d'un employee
    @PutMapping("/update")
    public ResponseEntity<Employees> updateEmployee(@RequestBody Employees employee) {
        Employees oldEmployee = employeeService.findEmployeeById(employee.getId());
        if (oldEmployee.getPassword() != employee.getPassword()) {
            String encodedPassword = bCryptPasswordEncoder
                    .encode(employee.getPassword());

            employee.setPassword(encodedPassword);
        }
        Employees updateEmployee = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }

    //supprimer un employee
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        //return "deleted successfully";
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    //rechercher un employee par nom
    @GetMapping("/search")
    public ResponseEntity<List<Employees>> getEmployeeByNomLikeIgnoreCase(@RequestParam("nom") String nom) {
        List<Employees> employees = employeeService.findByNomLikeIgnoreCase(nom);
        return  new ResponseEntity<>(employees, HttpStatus.OK);
    }

    //compter le nombre des employees
    @GetMapping("/count")
    public ResponseEntity<Long> countAllEmployees() {
        long count = employeeService.countAllEmployees();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

}

