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
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ahmed
 */
public class ServiceDossier {
    
    public ServiceDossier(){
        
    }

  
    
    

    public void ajoutDossier(Dossier d,Allergie a,Prescription p,String idResident) {
       // System.out.println("in ajout method : "+f.toString());
        
        
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/PidevHappyoldsSymfony/web/app_dev.php/ajouterDossierMobile/"+idResident+
                "?nbvisite="+d.getNbVisite()+ 
                "&problemesante="+ d.getProblemesSante()+
                "&descmedicament="+p.getDescriptionMedicament()+
                "&antecedent="+a.getAntecedants()+
                "&descallergie="+a.getDescriptionAllergie();
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
        

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    
     public void modifierDossier(Dossier d) {

        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/PidevHappyoldsSymfony/web/app_dev.php/modifierDossierMobile"+
                "?problemesante="+ d.getProblemesSante()+
                "&iddossierhidden="+d.getId_dossier()+
                "&nbvisite="+d.getNbVisite();
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
        
        
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    
    
    

    public Dossier parseListDossierJson(String json) {

        Dossier d = new Dossier();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

        
            Map<String, Object> dossiers = j.parseJSON(new CharArrayReader(json.toCharArray()));
           //  System.out.println("dossier : "+dossiers);       
           
       
            List<Map<String, Object>> list = (List<Map<String, Object>>) dossiers.get("root");
            
           //  System.out.println(list);


            for (Map<String, Object> obj : list) {

                
               
                
                 float id = Float.parseFloat(obj.get("idDossier").toString());
                 d.setId_dossier((int) id);
                 float nbVisite = Float.parseFloat(obj.get("nbVisite").toString());
                 d.setNbVisite((int) nbVisite);
                 d.setProblemesSante(obj.get("problemesSante").toString());
                

             //listfiches.add(f);

            }
         //   System.out.println(d);

        } catch (IOException ex) {
        }
        
 
       //System.out.println("at service : "+listfiches);
        return d;

    }
     public ArrayList<Dossier> parseListDossierJson2(String json) {

       ArrayList<Dossier> listfiches = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

        
            Map<String, Object> dossiers = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
       
            List<Map<String, Object>> list = (List<Map<String, Object>>) dossiers.get("root");


            for (Map<String, Object> obj : list) {

                
               Dossier d = new Dossier();
                
                 float id = Float.parseFloat(obj.get("idDossier").toString());
                 d.setId_dossier((int) id);
                 float nbVisite = Float.parseFloat(obj.get("nbVisite").toString());
                 d.setNbVisite((int) nbVisite);
                 d.setProblemesSante(obj.get("problemesSante").toString());
                

             listfiches.add(d);

            }
         //   System.out.println(d);

        } catch (IOException ex) {
        }
        
 
       //System.out.println("at service : "+listfiches);
        return listfiches;

    }
    
    
   
     Dossier dossier = new Dossier();
    
    public Dossier getDossierByResident(String idResident){ 
       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PidevHappyoldsSymfony/web/app_dev.php/afficherDossierParResidentMobile/"+idResident);  
        System.out.println(Arrays.toString(con.getResponseData()));
        if(Arrays.toString(con.getResponseData()) == null){
            System.out.println("hereeeeeeeeeeeeeeeeee");
            return new Dossier();
        }
            
        else{
            con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               // System.out.println(new String(con.getResponseData()));
                
                ServiceDossier sd = new ServiceDossier();
                dossier = sd.parseListDossierJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
       // System.out.println(dossier);
        return dossier;
        }
        
    }
     ArrayList<Dossier> listdossier = new ArrayList<>();
      public ArrayList<Dossier> getDossierByResident2(String idResident){ 
       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PidevHappyoldsSymfony/web/app_dev.php/afficherTabDossier/"+idResident);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceDossier sd = new ServiceDossier();
                listdossier = sd.parseListDossierJson2(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
       // System.out.println(dossier);
        return listdossier;
    }

    
}
