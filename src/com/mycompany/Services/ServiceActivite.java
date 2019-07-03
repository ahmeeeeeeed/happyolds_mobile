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
import static com.codename1.io.Preferences.set;

import com.codename1.ui.events.ActionListener;
import com.mycompany.Entite.Activite;
import com.mycompany.Entite.Animateur;
import com.mycompany.Entite.Categorie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author poste
 */
public class ServiceActivite {

    public ArrayList<Activite> parseListTaskJson(String json) {

        ArrayList<Activite> listTasks = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            for (Map<String, Object> obj : list) {

                Map<String, Object> b = (Map<String, Object>) obj.get("categorieActivite");
                Categorie etb = new Categorie();

                float idetb = Float.parseFloat(b.get("idCategoriea").toString());
                etb.setId((int) idetb);
                etb.setType(b.get("type").toString());
                System.out.println("*******" + etb);
                System.out.println("**********************************************************************");
                Map<String, Object> a = (Map<String, Object>) obj.get("animateur");
                Animateur aa = new Animateur();

                float ida = Float.parseFloat(a.get("idAnimateur").toString());
                aa.setIdAnimateur((int) ida);
                aa.setNomAnimateur(a.get("nomAnimateur").toString());
                System.out.println("*******" + aa);
                System.out.println("**********************************************************************");
                Activite e = new Activite();

                float id = Float.parseFloat(obj.get("idActivite").toString());
                e.setIdActivite((int) id);
                e.setNomActivite(obj.get("nomActivite").toString());
                e.setDateActivite(obj.get("dateActivite").toString());
                e.setDescriptionActivite(obj.get("descriptionActivite").toString());
                e.setPhoto(obj.get("photo").toString());
                e.setCategorieActivite(etb);
                e.setAnimateur(aa);

                System.out.println(e);

                listTasks.add(e);

            }

        } catch (IOException ex) {
        }

        System.out.println(listTasks);

        return listTasks;

    }

    ArrayList<Activite> listActivite = new ArrayList<>();

    public ArrayList<Activite> getList2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PidevHappyoldsSymfony/web/app_dev.php/activite/all");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceActivite ser = new ServiceActivite();
                listActivite = ser.parseListTaskJson(new String(con.getResponseData()));
                //  System.out.println( listt.get(0));   
            }

        });

        NetworkManager.getInstance().addToQueueAndWait(con);
        return listActivite;
    }

    public ArrayList<Activite> getList3(String rech, int c) {
        ConnectionRequest con = new ConnectionRequest();
        if (c == 0) {
            con.setUrl("http://localhost/PidevHappyoldsSymfony/web/app_dev.php/activite/rech?par=0&rech=" + rech + "");
            con.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    ServiceActivite ser = new ServiceActivite();
                    listActivite = ser.parseListTaskJson(new String(con.getResponseData()));
                    //  System.out.println( listt.get(0));   
                }

            });

            NetworkManager.getInstance().addToQueueAndWait(con);
        }
        
        
         if (c ==1) {
            con.setUrl("http://localhost/PidevHappyoldsSymfony/web/app_dev.php/activite/rech?par=1&rech="+ rech + "");
            con.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    ServiceActivite ser = new ServiceActivite();
                    listActivite = ser.parseListTaskJson(new String(con.getResponseData()));
                    //  System.out.println( listt.get(0));   
                }

            });

            NetworkManager.getInstance().addToQueueAndWait(con);
        }
        return listActivite;
    }

}
