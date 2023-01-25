package com.projet.back.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(
        name = "materiel"
)
public class Materiel implements Serializable {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String numInventaire;
    private String type;
    private Date dateAcquisition;
    private Date dateAffectation;
    @ManyToOne
    @JoinColumn(
            name = "id_employee"
    )
    private Employees employee;

    public Materiel() {
    }

    public Materiel(Long id, String numInventaire, String type, Date dateAcquisition, Date dateAffectation, Employees employee) {
        this.id = id;
        this.numInventaire = numInventaire;
        this.type = type;
        this.dateAcquisition = dateAcquisition;
        this.dateAffectation = dateAffectation;
        this.employee = employee;
    }

    public Date getDateAffectation() {
        return this.dateAffectation;
    }

    public String toString() {
        return "Materiel{id=" + this.id + ", numInventaire='" + this.numInventaire + "', type='" + this.type + "', dateAcquisition=" + this.dateAcquisition + ", dateAffectation=" + this.dateAffectation + ", enseignant=" + this.employee + "}";
    }

    public void setDateAffectation(Date dateAffectation) {
        this.dateAffectation = dateAffectation;
    }

    public Employees getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumInventaire() {
        return this.numInventaire;
    }

    public void setNumInventaire(String numInventaire) {
        this.numInventaire = numInventaire;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateAcquisition() {
        return this.dateAcquisition;
    }

    public void setDateAcquisition(Date dateAcquisition) {
        this.dateAcquisition = dateAcquisition;
    }
}