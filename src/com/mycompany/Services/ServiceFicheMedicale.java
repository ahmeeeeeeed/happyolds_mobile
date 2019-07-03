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
import com.mycompany.Entity.Fiche_medicale;
import com.mycompany.Entity.Resident;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ahmed
 */
public class ServiceFicheMedicale {
    
    public ServiceFicheMedicale(){
        
    }

  
    
    

    public void ajoutFiche(Fiche_medicale f,String idResident) {
        System.out.println("in ajout method : "+f.toString());
        
        
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/PidevHappyoldsSymfony/web/app_dev.php/ajouterFicheMobile/"+idResident+
                "?groupe_sanguin="+f.getGroupeSanguin() + 
                "&medicaments="+ f.getMedicaments()+
                "&niveau_sucre="+f.getNiveauSuc()+
                "&niveau_temperature="+f.getNiveauTemp()+
                "&niveau_tension="+f.getNiveauTension()+
                "&poids_resident="+f.getPoids_resident()+
                "&remarques_fiche="+f.getRemarques_fiche()+
                "&taille_resident="+f.getTaille_resident();
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
        
        
//exemple d'ajout  http://localhost/PidevHappyoldsSymfony/web/app_dev.php/ajouterFicheMobile/35?groupe_sanguin=B-&medicaments=medicaments&niveau_sucre=0.9&niveau_temperature=37.0&niveau_tension=12.5&poids_resident=75.0&remarques_fiche=Remarques&taille_resident=170.0

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    
     public void modifierFiche(Fiche_medicale f,String idResident) {
      //  System.out.println("in ajout method : "+f.toString());
        
        
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/PidevHappyoldsSymfony/web/app_dev.php/modifierFicheMobile/"+f.getId_fich()+"%20"+idResident+
                "?medicaments="+ f.getMedicaments()+
                "&niveau_sucre="+f.getNiveauSuc()+
                "&niveau_temperature="+f.getNiveauTemp()+
                "&niveau_tension="+f.getNiveauTension()+
                "&poids_resident="+f.getPoids_resident()+
                "&remarques_fiche="+f.getRemarques_fiche();
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
        
        
//exemple d'ajout  http://localhost/PidevHappyoldsSymfony/web/app_dev.php/ajouterFicheMobile/35?groupe_sanguin=B-&medicaments=medicaments&niveau_sucre=0.9&niveau_temperature=37.0&niveau_tension=12.5&poids_resident=75.0&remarques_fiche=Remarques&taille_resident=170.0

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    
    
    

    public ArrayList<Fiche_medicale> parseListFicheJson(String json) {

        ArrayList<Fiche_medicale> listfiches = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

        
            Map<String, Object> fiches = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
       
            List<Map<String, Object>> list = (List<Map<String, Object>>) fiches.get("root");
            
          //  System.out.println(list);

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
              //  System.out.println(obj.toString());
                //Création des tâches et récupération de leurs données
                
                Fiche_medicale f = new Fiche_medicale();
                
                 float id = Float.parseFloat(obj.get("idFiche").toString());
                f.setId_fich((int) id);
                
                f.setRemarques_fiche(obj.get("remarquesFiche").toString());
                f.setNiveauSuc(Float.parseFloat(obj.get("niveauSec").toString()));
                f.setNiveauTemp(Float.parseFloat(obj.get("niveauTemp").toString()));
                f.setNiveauTension(Float.parseFloat(obj.get("niveauTension").toString()));
                f.setPoids_resident(Float.parseFloat(obj.get("poidsResident").toString()));
                f.setTaille_resident(Float.parseFloat(obj.get("tailleResident").toString()));
                f.setGroupeSanguin(obj.get("groupeSanguin").toString());
                f.setMedicaments(obj.get("medicaments").toString());
             /*   Task e = new Task();

                float id = Float.parseFloat(obj.get("id").toString());

                e.setId((int) id);
                e.setEtat(obj.get("status").toString());
                e.setNom(obj.get("name").toString());
                System.out.println(e);
                
              //  listTasks.add(e);
*/
             listfiches.add(f);

            }

        } catch (IOException ex) {
        }
        
 
       //System.out.println("at service : "+listfiches);
        return listfiches;

    }
    
    
    ArrayList<Fiche_medicale> listfiche = new ArrayList<>();
    
    public ArrayList<Fiche_medicale> getListFicheByResident(String idResident){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PidevHappyoldsSymfony/web/app_dev.php/afficherFicheMobile/"+idResident);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceFicheMedicale sfm = new ServiceFicheMedicale();
                listfiche = sfm.parseListFicheJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listfiche;
    }

    
}
