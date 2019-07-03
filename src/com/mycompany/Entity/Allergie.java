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
public class Allergie {
    
    int id_allergie;
    String dateAllergies;
    String antecedants;
    String descriptionAllergie;
    int id_dossier;
    
    public Allergie(){
        
    }

    public int getId_allergie() {
        return id_allergie;
    }

    public void setId_allergie(int id_allergie) {
        this.id_allergie = id_allergie;
    }

    public String getDateAllergies() {
        return dateAllergies;
    }

    public void setDateAllergies(String dateAllergies) {
        this.dateAllergies = dateAllergies;
    }

    public String getAntecedants() {
        return antecedants;
    }

    public void setAntecedants(String antecedants) {
        this.antecedants = antecedants;
    }

    public String getDescriptionAllergie() {
        return descriptionAllergie;
    }

    public void setDescriptionAllergie(String descriptionAllergie) {
        this.descriptionAllergie = descriptionAllergie;
    }

    public int getId_dossier() {
        return id_dossier;
    }

    public void setId_dossier(int id_dossier) {
        this.id_dossier = id_dossier;
    }

    @Override
    public String toString() {
        return "Allergie{" + "id_allergie=" + id_allergie + ", dateAllergies=" + dateAllergies + ", antecedants=" + antecedants + ", descriptionAllergie=" + descriptionAllergie + ", id_dossier=" + id_dossier + '}';
    }
    
    
    
}
