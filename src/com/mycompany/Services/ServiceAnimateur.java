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
import com.mycompany.Entite.Animateur;
import com.mycompany.Entite.Categorie;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author poste
 */
public class ServiceAnimateur {

    public ArrayList<Animateur> parseListTaskJson(String json) {

        ArrayList<Animateur> listTasks = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            for (Map<String, Object> obj : list) {

                Animateur e = new Animateur();

                float id = Float.parseFloat(obj.get("idAnimateur").toString());
                e.setIdAnimateur((int) id);
                e.setNomAnimateur(obj.get("nomAnimateur").toString());
                e.setPrenomAnimateur(obj.get("prenomAnimateur").toString());
                e.setImage(obj.get("image").toString());
                float tel = Float.parseFloat(obj.get("telAnimateur").toString());
                e.setTelAnimateur((int) tel);

                e.setMailAnimateur(obj.get("mailAnimateur").toString());
                e.setAdresseAnimateur(obj.get("adresseAnimateur").toString());
                e.setDispo(obj.get("dispo").toString());

                System.out.println(e);

                listTasks.add(e);

            }

        } catch (IOException ex) {
        }

        System.out.println(listTasks);
        return listTasks;

    }

    ArrayList<Animateur> listAnimateur = new ArrayList<>();

    public ArrayList<Animateur> getList2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PidevHappyoldsSymfony/web/app_dev.php/animateur/all");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceAnimateur ser = new ServiceAnimateur();
                listAnimateur = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listAnimateur;
    }

      public void modifanimateur (Animateur t){
         ConnectionRequest con = new ConnectionRequest();
          System.out.println(t);
        String Url = "http://localhost/PidevHappyoldsSymfony/web/app_dev.php/animateur/modif/"+t.getIdAnimateur()+"?nomAnimateur="+t.getNomAnimateur()+"&prenomAnimateur="+t.getPrenomAnimateur()+"&mailAnimateur="+t.getMailAnimateur()+"&adresseAnimateur="+t.getAdresseAnimateur()+"&telAnimateur="+t.getTelAnimateur();
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
      
      
         public void supprimer(Animateur ta) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/PidevHappyoldsSymfony/web/app_dev.php/animateur/sup/" + ta.getIdAnimateur();// création de l'URL
           System.out.println(ta.getIdAnimateur());
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    
         }
         
         
          public void ajoutanimateur(Animateur a) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PidevHappyoldsSymfony/web/app_dev.php/animateur/adda?nomAnimateur="+a.getNomAnimateur()+"&&prenomAnimateur="+a.getPrenomAnimateur()+"&mailAnimateur="+a.getMailAnimateur()+"&adresseAnimateur="+a.getAdresseAnimateur()+"&telAnimateur="+a.getTelAnimateur();
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}
