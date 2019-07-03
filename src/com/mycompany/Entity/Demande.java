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
public class Demande {
       private int idDemande;
       private int idMaison;
       private int idDemandeCategorie;
       private int quantiteDemande;
       private String descriptionDemande;
       private int idUser;

    public Demande() {
    }

    public Demande(int idDemande, int idMaison, int idDemandeCategorie, int quantiteDemande, String descriptionDemande, int idUser) {
        this.idDemande = idDemande;
        this.idMaison = idMaison;
        this.idDemandeCategorie = idDemandeCategorie;
        this.quantiteDemande = quantiteDemande;
        this.descriptionDemande = descriptionDemande;
        this.idUser = idUser;
    }

    public int getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(int idDemande) {
        this.idDemande = idDemande;
    }

    public int getIdMaison() {
        return idMaison;
    }

    public void setIdMaison(int idMaison) {
        this.idMaison = idMaison;
    }

    public int getIdDemandeCategorie() {
        return idDemandeCategorie;
    }

    public void setIdDemandeCategorie(int idDemandeCategorie) {
        this.idDemandeCategorie = idDemandeCategorie;
    }

    public int getQuantiteDemande() {
        return quantiteDemande;
    }

    public void setQuantiteDemande(int quantiteDemande) {
        this.quantiteDemande = quantiteDemande;
    }

    public String getDescriptionDemande() {
        return descriptionDemande;
    }

    public void setDescriptionDemande(String descriptionDemande) {
        this.descriptionDemande = descriptionDemande;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Demande{" + "idDemande=" + idDemande + ", idMaison=" + idMaison + ", idDemandeCategorie=" + idDemandeCategorie + ", quantiteDemande=" + quantiteDemande + ", descriptionDemande=" + descriptionDemande + ", idUser=" + idUser + '}';
    }
       
    
    
       
       
       

    
    
}
