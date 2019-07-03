/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entity;

/**
 *
 * @author sana
 */
public class Maison {

    private int idMaison;
    private String nomMaison;
    private String adresseMaison;
    private String telephoneMaison;
    private String mailMaison;
    private int nbrPersonne;

    public Maison() {
    }

    public String getNomMaison() {
        return nomMaison;
    }

    public void setNomMaison(String nomMaison) {
        this.nomMaison = nomMaison;
    }

    public String getAdresseMaison() {
        return adresseMaison;
    }

    public void setAdresseMaison(String adresseMaison) {
        this.adresseMaison = adresseMaison;
    }

    public String getTelephoneMaison() {
        return telephoneMaison;
    }

    public void setTelephoneMaison(String telephoneMaison) {
        this.telephoneMaison = telephoneMaison;
    }

    public String getMailMaison() {
        return mailMaison;
    }

    public void setMailMaison(String mailMaison) {
        this.mailMaison = mailMaison;
    }

    public int getNbrPersonne() {
        return nbrPersonne;
    }

    public void setNbrPersonne(int nbrPersonne) {
        this.nbrPersonne = nbrPersonne;
    }

    public int getIdMaison() {
        return idMaison;
    }

    public void setIdMaison(int idMaison) {
        this.idMaison = idMaison;
    }

    public Maison(String nomMaison, String adresseMaison, String telephoneMaison, String mailMaison, int nbrPersonne) {
        this.nomMaison = nomMaison;
        this.adresseMaison = adresseMaison;
        this.telephoneMaison = telephoneMaison;
        this.mailMaison = mailMaison;
        this.nbrPersonne = nbrPersonne;
    }

    public Maison(String nomMaison, String adresseMaison, String telephoneMaison, String mailMaison) {
        this.nomMaison = nomMaison;
        this.adresseMaison = adresseMaison;
        this.telephoneMaison = telephoneMaison;
        this.mailMaison = mailMaison;
    }

}
