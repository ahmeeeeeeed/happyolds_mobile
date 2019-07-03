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
public class Dossier {
    int id_dossier;
    String problemesSante;
    int nbVisite;
    int id_resident;

    public  Dossier(){
    
}
    public int getId_dossier() {
        return id_dossier;
    }

    public void setId_dossier(int id_dossier) {
        this.id_dossier = id_dossier;
    }

    public String getProblemesSante() {
        return problemesSante;
    }

    public void setProblemesSante(String problemesSante) {
        this.problemesSante = problemesSante;
    }

    public int getNbVisite() {
        return nbVisite;
    }

    public void setNbVisite(int nbVisite) {
        this.nbVisite = nbVisite;
    }

    public int getId_resident() {
        return id_resident;
    }

    public void setId_resident(int id_resident) {
        this.id_resident = id_resident;
    }

    @Override
    public String toString() {
        return "Dossier{" + "id_dossier=" + id_dossier + ", problemesSante=" + problemesSante + ", nbVisite=" + nbVisite + ", id_resident=" + id_resident + '}';
    }
    
    
    
}
