/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

/**
 *
 * @author poste
 */
public class Animateur {

    private int idAnimateur;
    private String nomAnimateur;
    private String prenomAnimateur;
    private String mailAnimateur;
    private int telAnimateur;
    private String adresseAnimateur;
    private String dispo;
    private String image;

    public Animateur() {
        
        
        
    }

    public Animateur(int idAnimateur) {
        this.idAnimateur = idAnimateur;
    }

    public Animateur(int idAnimateur, String nomAnimateur, String prenomAnimateur, String mailAnimateur, String adresseAnimateur) {
        this.idAnimateur = idAnimateur;
        this.nomAnimateur = nomAnimateur;
        this.prenomAnimateur = prenomAnimateur;
        this.mailAnimateur = mailAnimateur;
        this.adresseAnimateur = adresseAnimateur;
    }

    public Animateur(int idAnimateur, String nomAnimateur, String prenomAnimateur, String mailAnimateur, int telAnimateur, String adresseAnimateur) {
        this.idAnimateur = idAnimateur;
        this.nomAnimateur = nomAnimateur;
        this.prenomAnimateur = prenomAnimateur;
        this.mailAnimateur = mailAnimateur;
        this.telAnimateur = telAnimateur;
        this.adresseAnimateur = adresseAnimateur;
    }
    
    
    

    public Animateur(int idAnimateur, String nomAnimateur, String prenomAnimateur, String mailAnimateur, int telAnimateur, String adresseAnimateur, String dispo, String image) {
        this.idAnimateur = idAnimateur;
        this.nomAnimateur = nomAnimateur;
        this.prenomAnimateur = prenomAnimateur;
        this.mailAnimateur = mailAnimateur;
        this.telAnimateur = telAnimateur;
        this.adresseAnimateur = adresseAnimateur;
        this.dispo = dispo;
        this.image = image;
    }
    
    

    public int getIdAnimateur() {
        return idAnimateur;
    }

    public void setIdAnimateur(int idAnimateur) {
        this.idAnimateur = idAnimateur;
    }

    public String getNomAnimateur() {
        return nomAnimateur;
    }

    public void setNomAnimateur(String nomAnimateur) {
        this.nomAnimateur = nomAnimateur;
    }

    public String getPrenomAnimateur() {
        return prenomAnimateur;
    }

    public void setPrenomAnimateur(String prenomAnimateur) {
        this.prenomAnimateur = prenomAnimateur;
    }

    public String getMailAnimateur() {
        return mailAnimateur;
    }

    public void setMailAnimateur(String mailAnimateur) {
        this.mailAnimateur = mailAnimateur;
    }

    public int getTelAnimateur() {
        return telAnimateur;
    }

    public void setTelAnimateur(int telAnimateur) {
        this.telAnimateur = telAnimateur;
    }

    public String getAdresseAnimateur() {
        return adresseAnimateur;
    }

    public void setAdresseAnimateur(String adresseAnimateur) {
        this.adresseAnimateur = adresseAnimateur;
    }

    public String getDispo() {
        return dispo;
    }

    public void setDispo(String dispo) {
        this.dispo = dispo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Animateur{" + "idAnimateur=" + idAnimateur + ", nomAnimateur=" + nomAnimateur + ", prenomAnimateur=" + prenomAnimateur + ", mailAnimateur=" + mailAnimateur + ", telAnimateur=" + telAnimateur + ", adresseAnimateur=" + adresseAnimateur + ", dispo=" + dispo + ", image=" + image + '}';
    }

}
