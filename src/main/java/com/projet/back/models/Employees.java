package com.projet.back.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode

@Entity
public class Employees implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(nullable = false, length = 20)
    private String nom;
    @Column(nullable = false, length = 20)
    private String prenom;
    @Column(nullable = false, length = 10, unique = true)
    private String cin;
    @Column(nullable = false, length = 80, unique = true)
    private String email;
    @Column(nullable = false, length = 20, unique = true)
    private String Telephone;
    @Column(nullable = false, length = 10)
    private String Bureau;
    @Column(nullable = false, length = 80)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmployeeRole employeeRole;
    @Column(nullable = false, length = 60)
    private String fonction;
    @Column(nullable = false, length = 20)
    private String grade;
    @Column(nullable = true, length = 500)
    private String imageUrl;

    public Employees(String nom, String prenom, String cin, String email, String telephone, String bureau, String password, EmployeeRole employeeRole, String fonction, String grade, String imageUrl) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.email = email;
        Telephone = telephone;
        Bureau = bureau;
        this.password = password;
        this.employeeRole = employeeRole;
        this.fonction = fonction;
        this.grade = grade;
        this.imageUrl = imageUrl;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(employeeRole.name());
        return Collections.singletonList(authority);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getBureau() {
        return Bureau;
    }

    public void setBureau(String bureau) {
        Bureau = bureau;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EmployeeRole getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(EmployeeRole employeeRole) {
        this.employeeRole = employeeRole;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
