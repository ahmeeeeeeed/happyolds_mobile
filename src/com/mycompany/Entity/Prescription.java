/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entity;

/**
 *
 * @author ahmed
 */
public class Prescription {
    
    int id_prescription;
    String dateDebutTraitement;
    String descriptionMedicament;
    int id_dossier;
    
    public Prescription(){
        
    }

    public int getId_prescription() {
        return id_prescription;
    }

    public void setId_prescription(int id_prescription) {
        this.id_prescription = id_prescription;
    }

    public String getDateDebutTraitement() {
        return dateDebutTraitement;
    }

    public void setDateDebutTraitement(String dateDebutTraitement) {
        this.dateDebutTraitement = dateDebutTraitement;
    }

    public String getDescriptionMedicament() {
        return descriptionMedicament;
    }

    public void setDescriptionMedicament(String descriptionMedicament) {
        this.descriptionMedicament = descriptionMedicament;
    }

    public int getId_dossier() {
        return id_dossier;
    }

    public void setId_dossier(int id_dossier) {
        this.id_dossier = id_dossier;
    }

    @Override
    public String toString() {
        return "Prescription_medicament{" + "id_prescription=" + id_prescription + ", dateDebutTraitement=" + dateDebutTraitement + ", descriptionMedicament=" + descriptionMedicament + ", id_dossier=" + id_dossier + '}';
    }
    
    
    
}
