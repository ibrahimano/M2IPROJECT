package com.projet.back.controllers;

import com.projet.back.models.EmployeePasswords;
import com.projet.back.models.Employees;
import com.projet.back.services.EmployeeService;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AppController {
    private final EmployeeService employeeService;

    public AppController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    //trouver un utilisateur par email
    @GetMapping("/find/{email}")
    public ResponseEntity<Employees> getEmployee(@PathVariable("email") String email) {
        Employees employee = employeeService.findEmployeeByEmail(email);
        return  new ResponseEntity<>(employee, HttpStatus.OK);
    }
    //trouver un utilisateur par id
    @GetMapping("/find/user/{id}")
    public ResponseEntity<Employees> getEmployeeById(@PathVariable("id") Long id) {
        Employees employee = employeeService.findEmployeeById(id);
        return  new ResponseEntity<>(employee, HttpStatus.OK);
    }

    //verifier si role existe
    @GetMapping("/checkRole/{role}")
    public boolean getCheckedFonction(@PathVariable("role") String role) {
        return  employeeService.CheckIfRoleExists(role);
    }

    //modifier les informations de l'utilisateur sans modifier le grade, role, fonction qui seul admin peuvent faire
    @PutMapping("/update/user/{id}")
    public ResponseEntity<Employees> updateEmployee(@PathVariable("id") Long id, @RequestBody Employees employee) {
        Employees updatedEmployee = employeeService.updateEmployee(id, employee.getNom(), employee.getPrenom(), employee.getCin(), employee.getEmail(), employee.getTelephone(), employee.getBureau(), employee.getImageUrl());

        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    //changer le mot de passe d'un utilisateur on verifiant le mdp actuel et le nouveau mdp avec sa confirmation
    @PutMapping("/update/password/{id}")
    public ResponseEntity<Object> updatePassword(@PathVariable("id") Long id,@RequestBody EmployeePasswords Passwords) {
        String message = employeeService.updatePassword(id, Passwords.getOldPassword(), Passwords.getNewPassword(), Passwords.getConfirmNewPassword());
        JSONObject response = new JSONObject();
        if (message.equals("Success")) {
            response.put("message", message);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", message);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
