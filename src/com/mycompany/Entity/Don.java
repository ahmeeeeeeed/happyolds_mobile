/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entity;

/**
 *
 * @author Admin
 */
public class Don {
    int idDon;
    int quantiteDon;
    String descriptionDon;
    int idDemande;
    int idCategorie;

    public Don() {
    }

    public Don(int idDon, int quantiteDon, String descriptionDon) {
        this.idDon = idDon;
        this.quantiteDon = quantiteDon;
        this.descriptionDon = descriptionDon;
    }

    public Don(int idDon, String descriptionDon) {
        this.idDon = idDon;
        this.descriptionDon = descriptionDon;
    }
    

    public int getIdDon() {
        return idDon;
    }

    public void setIdDon(int idDon) {
        this.idDon = idDon;
    }

    public int getQuantiteDon() {
        return quantiteDon;
    }

    public void setQuantiteDon(int quantiteDon) {
        this.quantiteDon = quantiteDon;
    }

    public String getDescriptionDon() {
        return descriptionDon;
    }

    public void setDescriptionDon(String descriptionDon) {
        this.descriptionDon = descriptionDon;
    }

    @Override
    public String toString() {
        return "Don{" + "idDon=" + idDon + ", quantiteDon=" + quantiteDon + ", descriptionDon=" + descriptionDon + ", idDemande=" + idDemande + '}';
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    

    public int getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(int idDemande) {
        this.idDemande = idDemande;
    }
    
    

}