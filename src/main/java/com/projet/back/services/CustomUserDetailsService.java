package com.projet.back.services;


import com.projet.back.models.Employees;
import com.projet.back.repository.EmployeeRepository;
import com.projet.back.security.config.CustomUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@AllArgsConstructor
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employees employee = employeeRepo.findEmployeeByEmail(email);
        if (employee == null) {
            throw new UsernameNotFoundException("Invalid email or password.");
        }

        //return new User(employee.getEmail(), employee.getPassword(), employee.getAuthorities());
        return new CustomUserDetails(employee);

    }

}
