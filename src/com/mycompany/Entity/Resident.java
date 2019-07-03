/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entity;

import com.mycompany.Entity.Maison;
import java.util.Date;

/**
 *
 * @author sana
 */
public class Resident {

    private int id_resident;
    private String nom_resident;
    private String prenom_resident;
    private int age_resident;
    private String alzheimer_resident;
    private String maladie_resident;
    private String responsable;
    private String telephone_responsable;
    private String sexe_resident;
    private Date date_resident;
    private String etat;
    
    private String dateResident;
    private String telephoneResponsable;
     private Maison maison;

    public Resident() {
    }

    public Resident(String nom_resident, String prenom_resident, String alzheimer_resident, String maladie_resident, String responsable, String telephone_responsable, String sexe_resident, String etat) {
        this.nom_resident = nom_resident;
        this.prenom_resident = prenom_resident;
        this.alzheimer_resident = alzheimer_resident;
        this.maladie_resident = maladie_resident;
        this.responsable = responsable;
        this.telephone_responsable = telephone_responsable;
        this.sexe_resident = sexe_resident;
        this.etat = etat;
    }

    public Resident(String nom_resident, String prenom_resident, String alzheimer_resident, String maladie_resident, String responsable, String telephone_responsable, String sexe_resident, Date date_resident, String etat) {
        this.nom_resident = nom_resident;
        this.prenom_resident = prenom_resident;
        this.alzheimer_resident = alzheimer_resident;
        this.maladie_resident = maladie_resident;
        this.responsable = responsable;
        this.telephone_responsable = telephone_responsable;
        this.sexe_resident = sexe_resident;
        this.date_resident = date_resident;
        this.etat = etat;
    }

    public int getId_resident() {
        return id_resident;
    }

    public void setId_resident(int id_resident) {
        this.id_resident = id_resident;
    }

    public String getNom_resident() {
        return nom_resident;
    }

    public void setNom_resident(String nom_resident) {
        this.nom_resident = nom_resident;
    }

    public String getPrenom_resident() {
        return prenom_resident;
    }

    public void setPrenom_resident(String prenom_resident) {
        this.prenom_resident = prenom_resident;
    }

    public int getAge_resident() {
        return age_resident;
    }

    public void setAge_resident(int age_resident) {
        this.age_resident = age_resident;
    }

    public String getAlzheimer_resident() {
        return alzheimer_resident;
    }

    public void setAlzheimer_resident(String alzheimer_resident) {
        this.alzheimer_resident = alzheimer_resident;
    }

    public String getMaladie_resident() {
        return maladie_resident;
    }

    public void setMaladie_resident(String maladie_resident) {
        this.maladie_resident = maladie_resident;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getTelephone_responsable() {
        return telephone_responsable;
    }

    public void setTelephone_responsable(String telephone_responsable) {
        this.telephone_responsable = telephone_responsable;
    }

    public String getSexe_resident() {
        return sexe_resident;
    }

    public void setSexe_resident(String sexe_resident) {
        this.sexe_resident = sexe_resident;
    }

    public Date getDate_resident() {
        return date_resident;
    }

    public void setDate_resident(Date date_resident) {
        this.date_resident = date_resident;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Resident(String nom_resident, String prenom_resident, int age_resident, String alzheimer_resident, String maladie_resident, String responsable, String telephone_responsable, String sexe_resident, Date date_resident, String etat) {
        this.nom_resident = nom_resident;
        this.prenom_resident = prenom_resident;
        this.age_resident = age_resident;
        this.alzheimer_resident = alzheimer_resident;
        this.maladie_resident = maladie_resident;
        this.responsable = responsable;
        this.telephone_responsable = telephone_responsable;
        this.sexe_resident = sexe_resident;
        this.date_resident = date_resident;
        this.etat = etat;
    }

    public String getDateResident() {
        return dateResident;
    }

    public void setDateResident(String dateResident) {
        this.dateResident = dateResident;
    }

    public String getTelephoneResponsable() {
        return telephoneResponsable;
    }

    public void setTelephoneResponsable(String telephoneResponsable) {
        this.telephoneResponsable = telephoneResponsable;
    }

    public Maison getMaison() {
        return maison;
    }

    public void setMaison(Maison maison) {
        this.maison = maison;
    }

    
    
    
    

}
