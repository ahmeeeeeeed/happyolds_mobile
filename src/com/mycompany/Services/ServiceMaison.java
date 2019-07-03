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
import com.mycompany.Entity.Maison;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author poste
 */
public class ServiceMaison {
    
     public ArrayList<Maison> parseListTaskJson(String json) {

        ArrayList<Maison> listTasks = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

          
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
            
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            
            for (Map<String, Object> obj : list) {
                
                Maison m = new Maison();
                
                float id = Float.parseFloat(obj.get("idMaison").toString());
                m.setIdMaison((int) id);
                m.setNomMaison(obj.get("nomMaison").toString());
                m.setAdresseMaison(obj.get("adresseMaison").toString());
                m.setTelephoneMaison(obj.get("telephoneMaison").toString());
                m.setMailMaison(obj.get("mailMaison").toString());

                float nbr = Float.parseFloat(obj.get("nbrPersonne").toString());
                        m.setNbrPersonne((int) nbr);
               
                
                System.out.println(m);
               
                listTasks.add(m);

            }

        } catch (IOException ex) {
        }
        

        System.out.println(listTasks);
        return listTasks;

    }
    
    
    ArrayList<Maison> listMaison = new ArrayList<>();
    
    public ArrayList<Maison> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PidevHappyoldsSymfony/web/app_dev.php/allM");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceMaison ser = new ServiceMaison();
                listMaison = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listMaison;
    }
    
    
}