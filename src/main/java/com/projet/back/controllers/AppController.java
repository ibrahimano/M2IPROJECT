package com.projet.back.controllers;

import com.projet.back.models.Employees;
import com.projet.back.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
    private final EmployeeService employeeService;

    public AppController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/home")
    public String HomePage() {
        return "Home page";
    }

    @GetMapping("/admin")
    public String AdminPage() {
        return "Admin page";
    }


    @GetMapping("/find/{email}")
    public ResponseEntity<Employees> getEmployee(@PathVariable("email") String email) {
        Employees employee = employeeService.findEmployeeByEmail(email);
        return  new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping("/checkRole/{role}")
    public boolean getCheckedFonction(@PathVariable("role") String role) {
        return  employeeService.CheckIfRoleExists(role);
    }

}
