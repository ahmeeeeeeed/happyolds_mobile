/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entity.Allergie;
import com.mycompany.Entity.Dossier;
import com.mycompany.Entity.Fiche_medicale;
import com.mycompany.Entity.Prescription;
import com.mycompany.Entity.Resident;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ahmed
 */
public class ServicePrescription {
    
    public ServicePrescription(){
        
    }

  
    
    

    public void ajoutPrescription(Prescription a) {
     //   System.out.println("in ajout method : "+f.toString());
        
        
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/PidevHappyoldsSymfony/web/app_dev.php/ajouterPrescriptionMobile"+
                "?iddossierhidden="+a.getId_dossier() + 
                "&medicaments="+ a.getDescriptionMedicament();
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
        
       
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
     public void supprimerPrescription(int id_pres) {
     //   System.out.println("in ajout method : "+f.toString());
        
        
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/PidevHappyoldsSymfony/web/app_dev.php/supprimerPrescriptionMobile/"+id_pres;
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
        
       
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }


    public ArrayList<Prescription> parseListPrescriptionJson(String json) {

        ArrayList<Prescription> listfiches = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

        
            Map<String, Object> fiches = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
       
            List<Map<String, Object>> list = (List<Map<String, Object>>) fiches.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
              //  System.out.println(obj.toString());
                //Création des tâches et récupération de leurs données
                
               Prescription p = new Prescription();
                
                 float id = Float.parseFloat(obj.get("idPrescriptionsMedicaments").toString());
                p.setId_prescription((int) id);
                p.setDescriptionMedicament(obj.get("descriptionMedicament").toString());
             
      
             listfiches.add(p);

            }

        } catch (IOException ex) {
        }
        
 
       //System.out.println("at service : "+listfiches);
        return listfiches;

    }
    
    
    ArrayList<Prescription> listpresc = new ArrayList<>();
    
    public ArrayList<Prescription> getListPrescriptionByDossier(String idResident){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PidevHappyoldsSymfony/web/app_dev.php/afficherPrescription/"+idResident);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServicePrescription sfm = new ServicePrescription();
                listpresc = sfm.parseListPrescriptionJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listpresc;
    }

    
}
