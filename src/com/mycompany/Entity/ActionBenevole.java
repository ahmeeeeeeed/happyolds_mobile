/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;
import java.util.Date;


/**
 *
 * @author asus
 */
public class ActionBenevole {
    private int idAction;
    private Date dateDAction;
    private Date dateFAction;
    private String description;
    private String etat;
    private String type;

    public ActionBenevole() {
    }

    public ActionBenevole(int idAction, Date dateDAction, Date dateFAction, String description, String etat, String type) {
        this.idAction = idAction;
        this.dateDAction = dateDAction;
        this.dateFAction = dateFAction;
        this.description = description;
        this.etat = etat;
        this.type = type;
    }

    public ActionBenevole(int idAction, Date dateDAction, Date dateFAction, String description) {
        this.idAction = idAction;
        this.dateDAction = dateDAction;
        this.dateFAction = dateFAction;
        this.description = description;
    }

    @Override
    public String toString() {
        return "ActionBenevole{" + "idAction=" + idAction + ", dateDAction=" + dateDAction + ", dateFAction=" + dateFAction + ", description=" + description + ", etat=" + etat + ", type=" + type + '}';
    }

    public int getIdAction() {
        return idAction;
    }

    public void setIdAction(int idAction) {
        this.idAction = idAction;
    }

    public Date getDateDAction() {
        return dateDAction;
    }

    public void setDateDAction(Date dateDAction) {
        this.dateDAction = dateDAction;
    }

    public Date getDateFAction() {
        return dateFAction;
    }

    public void setDateFAction(Date dateFAction) {
        this.dateFAction = dateFAction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    

 
  
    
}
