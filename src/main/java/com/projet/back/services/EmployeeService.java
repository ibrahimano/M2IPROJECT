package com.projet.back.services;


import com.projet.back.models.EmployeeRole;
import com.projet.back.models.Employees;
import com.projet.back.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EmployeeService {
    public final EmployeeRepository employeeRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.employeeRepo = employeeRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
        EmployeeRole employeeRole = EmployeeRole.valueOf(role);
        List<Employees> employeeList  = employeeRepo.findEmployeeByEmployeeRole(employeeRole);
        if (employeeList.size()>0)
            return true;
        else
            return false;
    }

    public List<Employees> findByNomLikeIgnoreCase(String nom) {
        return employeeRepo.findByNomLikeIgnoreCase(nom);
    }


    public Employees updateEmployee(Long id, String nom, String prenom, String cin, String email, String telephone, String bureau, String imageUrl) {
        Employees employee = employeeRepo.findEmployeeById(id).orElse(null);
        if (employee == null) {
            return null;
        }
        employee.setNom(nom);
        employee.setPrenom(prenom);
        employee.setCin(cin);
        employee.setEmail(email);
        employee.setTelephone(telephone);
        employee.setBureau(bureau);
        employee.setImageUrl(imageUrl);
        return employeeRepo.save(employee);
    }

    public String updatePassword(Long id, String oldPassword, String newPassword, String confirmNewPassword) {
        Employees employee = employeeRepo.findEmployeeById(id).orElse(null);
        if (employee == null) {
            return "User not found.";
        }
        if (!bCryptPasswordEncoder.matches(oldPassword, employee.getPassword())) {
            return "Old password is incorrect.";
        }
        if (!newPassword.equals(confirmNewPassword)) {
            return "New password and confirmed new password do not match.";
        }
        employee.setPassword(bCryptPasswordEncoder.encode(newPassword));
        employeeRepo.save(employee);
        return "Success";
    }

    public Long countAllEmployees() {
        return employeeRepo.count();
    }
}
