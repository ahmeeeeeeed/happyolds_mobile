/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Services;


import com.mycompany.Entity.Demande;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class ServiceDemande {
    
    
    
     public ArrayList<Demande> parseListDemandeJson(String json) {

        ArrayList<Demande> listDemande = new ArrayList<>();

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
            Map<String, Object> Demandes = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
            
            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche                
            */
            List<Map<String, Object>> list = (List<Map<String, Object>>) Demandes.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Demande e = new Demande();

                float id = Float.parseFloat(obj.get("idDemande").toString());
               // float idMaison = Float.parseFloat(obj.get("idMaison").toString());
               // float idDemandeCategorie = Float.parseFloat(obj.get("idCategorie").toString());
                float idQuantiteDemande = Float.parseFloat(obj.get("quantiteDemande").toString());
            //    float idUserDemande = Float.parseFloat(obj.get("idUser").toString());

               
                e.setIdDemande((int) id);
             //   e.setIdMaison((int)idMaison);
              //  e.setIdDemandeCategorie((int)idDemandeCategorie);
                e.setQuantiteDemande((int)idQuantiteDemande);
                e.setDescriptionDemande(obj.get("descriptionDemande").toString());
               // e.setIdUser((int)idUserDemande);
                
              //  System.out.println(e);
                
                listDemande.add(e);

            }

        } catch (IOException ex) {
        }
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
       // System.out.println(listDemande);
        return listDemande;

    }
     ArrayList<Demande> listDemande = new ArrayList<>();
    
    public ArrayList<Demande> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PidevHappyoldsSymfony/web/app_dev.php/affichedemandemobile");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceDemande ser = new ServiceDemande();
                listDemande = ser.parseListDemandeJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listDemande ;
    }
    
    
}
