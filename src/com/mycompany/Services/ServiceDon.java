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
import com.mycompany.Entity.Demande;
import com.mycompany.Entity.Don;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class ServiceDon {
    
        public void ajoutDon(Don da) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/PidevHappyoldsSymfony/web/app_dev.php/ajoutdon?quantiteDon=" + da.getQuantiteDon() + "&descriptionDon=" + da.getDescriptionDon();// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
          //  System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
         public void affecterDon(Don d) {
            // System.out.println(d);
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/PidevHappyoldsSymfony/web/app_dev.php/affecterdonmobile"+
                "?quantite=" + d.getQuantiteDon() + 
                "&description=" + d.getDescriptionDon()+
                "&iddemande="+d.getIdDemande();// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
           // System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    
                public void modifierDon(Don da) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/PidevHappyoldsSymfony/web/app_dev.php/modificationdon/idDon="+da.getIdDon()+"?quantiteDon=" + da.getQuantiteDon() + "&descriptionDon=" + da.getDescriptionDon();// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
          //  System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
        
    }
                
                public ArrayList<Don> parseListDonJson(String json) {

        ArrayList<Don> listDon = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String, Object> Dons = j.parseJSON(new CharArrayReader(json.toCharArray()));
            
            System.out.println(Dons);
                       
            
            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche                
            */
            List<Map<String, Object>> list = (List<Map<String, Object>>) Dons.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Don e = new Don();

                float id = Float.parseFloat(obj.get("idDon").toString());
               // float idMaison = Float.parseFloat(obj.get("idMaison").toString());
               // float idDemandeCategorie = Float.parseFloat(obj.get("idCategorie").toString());
                float idQuantiteDemande = Float.parseFloat(obj.get("quantiteDon").toString());
            //    float idUserDemande = Float.parseFloat(obj.get("idUser").toString());

               
                e.setIdDon((int) id);
             //   e.setIdMaison((int)idMaison);
              //  e.setIdDemandeCategorie((int)idDemandeCategorie);
                e.setQuantiteDon((int)idQuantiteDemande);
                e.setDescriptionDon(obj.get("descriptionDon").toString());
               // e.setIdUser((int)idUserDemande);
                
               // System.out.println(e);
               
                System.out.println(e);
                
               listDon.add(e);

            }

        } catch (IOException ex) {
        }
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
       // System.out.println(listDon);
        return listDon;

    }
     ArrayList<Don> listDon = new ArrayList<>();
    
    public ArrayList<Don> getListDon(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PidevHappyoldsSymfony/web/app_dev.php/affichedonmobile");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceDon ser = new ServiceDon();
               listDon = ser. parseListDonJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return  listDon  ;
    }
}
