/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

import java.util.Date;

/**
 *
 * @author acer
 */
public class Evenement {
    
    
    private int idEvenement;
    private Date dateDEvenement;
    private Date dateFEvenement;
    private String heureEvenement;
    private String nomEvenement;
    private String adresseEvenement;
    private String descriptionEvenement;

    public Evenement() {
    }

    public Evenement(int idEvenement, Date dateDEvenement, Date dateFEvenement, String heureEvenement, String nomEvenement, String adresseEvenement, String descriptionEvenement) {
        this.idEvenement = idEvenement;
        this.dateDEvenement = dateDEvenement;
        this.dateFEvenement = dateFEvenement;
        this.heureEvenement = heureEvenement;
        this.nomEvenement = nomEvenement;
        this.adresseEvenement = adresseEvenement;
        this.descriptionEvenement = descriptionEvenement;
    }

    public Evenement(int idEvenement, String heureEvenement, String nomEvenement, String adresseEvenement, String descriptionEvenement) {
        this.idEvenement = idEvenement;
        this.heureEvenement = heureEvenement;
        this.nomEvenement = nomEvenement;
        this.adresseEvenement = adresseEvenement;
        this.descriptionEvenement = descriptionEvenement;
    }
    

    public int getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    public Date getDateDEvenement() {
        return dateDEvenement;
    }

    public void setDateDEvenement(Date dateDEvenement) {
        this.dateDEvenement = dateDEvenement;
    }

    public Date getDateFEvenement() {
        return dateFEvenement;
    }

    public void setDateFEvenement(Date dateFEvenement) {
        this.dateFEvenement = dateFEvenement;
    }

    public String getHeureEvenement() {
        return heureEvenement;
    }

    public void setHeureEvenement(String heureEvenement) {
        this.heureEvenement = heureEvenement;
    }

    public String getNomEvenement() {
        return nomEvenement;
    }

    public void setNomEvenement(String nomEvenement) {
        this.nomEvenement = nomEvenement;
    }

    public String getAdresseEvenement() {
        return adresseEvenement;
    }

    public void setAdresseEvenement(String adresseEvenement) {
        this.adresseEvenement = adresseEvenement;
    }

    public String getDescriptionEvenement() {
        return descriptionEvenement;
    }

    public void setDescriptionEvenement(String descriptionEvenement) {
        this.descriptionEvenement = descriptionEvenement;
    }

    @Override
    public String toString() {
        return "Evenement{" + "idEvenement=" + idEvenement + ", dateDEvenement=" + dateDEvenement + ", dateFEvenement=" + dateFEvenement + ", heureEvenement=" + heureEvenement + ", nomEvenement=" + nomEvenement + ", adresseEvenement=" + adresseEvenement + ", descriptionEvenement=" + descriptionEvenement + '}';
    }

   
    
   
    
}
