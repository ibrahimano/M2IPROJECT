package com.projet.back.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
public class Demande implements Serializable {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private Date dateCreation;
    private int etat;
    @OneToOne
    private Materiel materiel;
    @ManyToOne
    @JoinColumn(
            name = "id_employee"
    )
    private Employees employee;

    public int getEtat() {
        return this.etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Demande() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreation() {
        return this.dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Materiel getMateriel() {
        return this.materiel;
    }

    public void setMateriel(Materiel materiel) {
        this.materiel = materiel;
    }

    public Employees getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }
}
