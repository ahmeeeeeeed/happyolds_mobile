/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Services;

import com.codename1.components.SpanLabel;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entite.Evenement;
import com.mycompany.GUI.EventForm;
//import com.mycompany.gui.HomeForm;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author acer
 */
public class ServiceEvent {

    public void ajoutEvent(Evenement ev) {

        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PidevHappyoldsSymfony/web/app_dev.php/evenements/newEV?dateDEvenement=" + ev.getDateDEvenement() + "&dateFEvenement=" + ev.getDateFEvenement() + "&heureEvenement=" + ev.getHeureEvenement() + "&nomEvenement=" + ev.getNomEvenement() + "&adresseEvenement=" + ev.getAdresseEvenement() + "&descriptionEvenement=" + ev.getDescriptionEvenement();
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });

        NetworkManager.getInstance().addToQueueAndWait(con);

    }

    public ArrayList<Evenement> parseListEventJson(String json) throws ParseException {

        ArrayList<Evenement> listEvent = new ArrayList<>();

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
            Map<String, Object> evenements = j.parseJSON(new CharArrayReader(json.toCharArray()));

            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche                
             */
            List<Map<String, Object>> list = (List<Map<String, Object>>) evenements.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Evenement e = new Evenement();

                float id = Float.parseFloat(obj.get("idEvenement").toString());
                e.setIdEvenement((int) id);

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date datedeb = df.parse((String) obj.get("dateDEvenement"));
                e.setDateDEvenement(datedeb);

                Date datefin = df.parse((String) obj.get("dateFEvenement"));
                e.setDateFEvenement(datefin);
                e.setHeureEvenement(obj.get("heureEvenement").toString());
                e.setNomEvenement(obj.get("nomEvenement").toString());
                e.setAdresseEvenement(obj.get("adresseEvenement").toString());
                e.setDescriptionEvenement(obj.get("descriptionEvenement").toString());
                System.out.println(e);

                listEvent.add(e);

            }

        } catch (IOException ex) {

        }

        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
         */
        System.out.println(listEvent);
        return listEvent;

    }

    ArrayList<Evenement> listEvents = new ArrayList<>();

    public ArrayList<Evenement> getList2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PidevHappyoldsSymfony/web/app_dev.php/evenements/allEV");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceEvent ser = new ServiceEvent();
                try {
                    String str = new String(con.getResponseData());
                    System.out.println(str);
                    listEvents = ser.parseListEventJson(str);
                } catch (ParseException ex) {
//                    Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvents;
    }
    Form f;
    SpanLabel lb;

    public void supprimerEvent(int id) {
          ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/PidevHappyoldsSymfony/web/app_dev.php/evenements/suppEV/" + id + "";
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
        
       
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
   
        /*ConnectionRequest con = new ConnectionRequest();

        con.setUrl("http://localhost/PidevHappyoldsSymfony/web/app_dev.php/evenements/suppEV/" + id + "");

        NetworkManager.getInstance().addToQueueAndWait(con);*/

       /* f = new Form();
        lb = new SpanLabel("");
        f.add(lb);
        ServiceEvent serviceTask = new ServiceEvent();
        lb.setText(serviceTask.getList2().toString());*/

       /* f.getToolbar().addCommandToRightBar("back", null, (ev) -> {
            EventForm h = new EventForm();
            h.show();
        });*/
    }

    public void modifierEvent(Evenement e1) {
        ConnectionRequest con = new ConnectionRequest();

        String Url = "http://localhost/PidevHappyoldsSymfony/web/app_dev.php/evenements/modifEV/"
                + e1.getIdEvenement() + "?dateDEvenement=" + e1.getDateDEvenement() + "&dateFEvenement=" + e1.getDateFEvenement()
                + "&heureEvenement=" + e1.getHeureEvenement() + "&nomEvenement=" + e1.getNomEvenement()
                + "&adresseEvenement=" + e1.getAdresseEvenement() + "&descriptionEvenement=" + e1.getDescriptionEvenement()
                + "";
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
}
