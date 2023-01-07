package com.projet.back.services;


import com.projet.back.models.Employees;
import com.projet.back.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EmployeeService {
    public final EmployeeRepository employeeRepo;
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }
    public Employees addEmployee(Employees employee) {
        return employeeRepo.save(employee);
    }
    public List<Employees> findAllEmployees() {
        return employeeRepo.findAll();
    }
    public Employees updateEmployee(Employees employee) {
        return employeeRepo.save(employee);
    }
    public Employees findEmployeeById(Long id) {
        return employeeRepo.findEmployeeById(id).orElse(null);
    }
    public void deleteEmployee(Long id){
        employeeRepo.deleteEmployeeById(id);
    }
    public Employees findEmployeeByEmail(String email) {
        return employeeRepo.findEmployeeByEmail(email);
    }
    public Boolean CheckIfRoleExists(String role) {
        Employees employee = employeeRepo.findEmployeeByEmployeeRole(role);
        if (employee == null)
            return false;
        else
            return true;
    }

    public List<Employees> findByNomLikeIgnoreCase(String nom) {
        return employeeRepo.findByNomLikeIgnoreCase(nom);
    }




}
