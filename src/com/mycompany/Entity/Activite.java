/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

import java.util.Date;

/**
 *
 * @author poste
 */
public class Activite {

    private int idActivite;
    private String nomActivite;
    private String dateActivite;
    private String descriptionActivite;
    private String photo;
    private Animateur animateur;
    private Categorie categorieActivite;

    public Activite() {
    }

    public Activite(int idActivite, String nomActivite, String dateActivite, String descriptionActivite, String photo, Animateur animateur, Categorie categorieActivite) {
        this.idActivite = idActivite;
        this.nomActivite = nomActivite;
        this.dateActivite = dateActivite;
        this.descriptionActivite = descriptionActivite;
        this.photo = photo;
        this.animateur = animateur;
        this.categorieActivite = categorieActivite;
    }

  

    @Override
    public String toString() {
        return "Activite{" + "idActivite=" + idActivite + ", nomActivite=" + nomActivite + ", dateActivite=" + dateActivite + ", descriptionActivite=" + descriptionActivite + ", photo=" + photo + ", animateur=" + animateur + ", categorieActivite=" + categorieActivite + '}';
    }

    public int getIdActivite() {
        return idActivite;
    }

    public void setIdActivite(int idActivite) {
        this.idActivite = idActivite;
    }

    public String getNomActivite() {
        return nomActivite;
    }

    public void setNomActivite(String nomActivite) {
        this.nomActivite = nomActivite;
    }

    public String getDateActivite() {
        return dateActivite;
    }

    public void setDateActivite(String dateActivite) {
        this.dateActivite = dateActivite;
    }

    public String getDescriptionActivite() {
        return descriptionActivite;
    }

    public void setDescriptionActivite(String descriptionActivite) {
        this.descriptionActivite = descriptionActivite;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Animateur getAnimateur() {
        return animateur;
    }

    public void setAnimateur(Animateur animateur) {
        this.animateur = animateur;
    }

    public Categorie getCategorieActivite() {
        return categorieActivite;
    }

    public void setCategorieActivite(Categorie categorieActivite) {
        this.categorieActivite = categorieActivite;
    }

 



}
