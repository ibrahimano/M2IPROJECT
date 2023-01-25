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

   /* @GetMapping("/home")
    public String HomePage() {
        return "Home page";
    }

    @GetMapping("/admin")
    public String AdminPage() {
        return "Admin page";
    }*/

    //find a user by email
    @GetMapping("/find/{email}")
    public ResponseEntity<Employees> getEmployee(@PathVariable("email") String email) {
        Employees employee = employeeService.findEmployeeByEmail(email);
        return  new ResponseEntity<>(employee, HttpStatus.OK);
    }
    //find a user by his id
    @GetMapping("/find/user/{id}")
    public ResponseEntity<Employees> getEmployeeById(@PathVariable("id") Long id) {
        Employees employee = employeeService.findEmployeeById(id);
        return  new ResponseEntity<>(employee, HttpStatus.OK);
    }

    //check if a role exist
    @GetMapping("/checkRole/{role}")
    public boolean getCheckedFonction(@PathVariable("role") String role) {
        return  employeeService.CheckIfRoleExists(role);
    }

    //update some specific variables for a user this is used when a user is not an admin so his not allowed to change all variables
    @PutMapping("/update/user/{id}")
    public ResponseEntity<Employees> updateEmployee(@PathVariable("id") Long id, @RequestBody Employees employee) {
        Employees updatedEmployee = employeeService.updateEmployee(id, employee.getNom(), employee.getPrenom(), employee.getCin(), employee.getEmail(), employee.getTelephone(), employee.getBureau(), employee.getImageUrl());

        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    //change only the user's password
  /*  @PutMapping("/update/password/{id}")
    public ResponseEntity<String> updatePassword(@PathVariable("id") Long id, @RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, @RequestParam("confirmNewPassword") String confirmNewPassword) {
        String message = employeeService.updatePassword(id, oldPassword, newPassword, confirmNewPassword);
        if (message.equals("Success")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }*/
  /*  @PutMapping("/update/password/{id}")
    public ResponseEntity<String> updatePassword(@PathVariable("id") Long id,@RequestBody EmployeePasswords Passwords) {
        String message = employeeService.updatePassword(id, Passwords.getOldPassword(), Passwords.getNewPassword(), Passwords.getConfirmNewPassword());
        if (message.equals("Success")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }*/
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
