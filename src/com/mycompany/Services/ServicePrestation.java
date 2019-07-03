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
import com.mycompany.Entity.Resident;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ahmed
 */
public class ServicePrestation {
    
    
public ServicePrestation(){}

    public ArrayList<Resident> parseListResidentJson(String json) {

        ArrayList<Resident> listResident = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

        
            Map<String, Object> residents = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
       
            List<Map<String, Object>> list = (List<Map<String, Object>>) residents.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                
              //  System.out.println(obj.toString());
                //Création des tâches et récupération de leurs données
                Resident r = new Resident();
                
                 float id = Float.parseFloat(obj.get("idResident").toString());
               r.setId_resident((int) id);
               float age = Float.parseFloat(obj.get("ageResident").toString());
               r.setAge_resident((int) age);
               r.setSexe_resident(obj.get("sexeResident").toString());
                r.setNom_resident(obj.get("nomResident").toString());
                r.setPrenom_resident(obj.get("prenomResident").toString());
              //  r.setAge_resident((int) obj.get("ageResident"));
                r.setSexe_resident(obj.get("sexeResident").toString());
                r.setResponsable(obj.get("responsable").toString());
                r.setTelephone_responsable( obj.get("telephoneResponsable").toString());
                

            /*    float id = Float.parseFloat(obj.get("id").toString());

                e.setId((int) id);
                e.setEtat(obj.get("status").toString());
                e.setNom(obj.get("name").toString());
                System.out.println(e);*/
                
                listResident.add(r);

            }

        } catch (IOException ex) {
        }
        
 
      //  System.out.println(listResident);
        return listResident;

    }
    
    
    ArrayList<Resident> listResident = new ArrayList<>();
    
    public ArrayList<Resident> getListResident(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PidevHappyoldsSymfony/web/app_dev.php/afficherListeResidentMobile");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServicePrestation sp = new ServicePrestation();
                listResident = sp.parseListResidentJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        //System.out.println(listResident);
        return listResident;
    }

    
}
