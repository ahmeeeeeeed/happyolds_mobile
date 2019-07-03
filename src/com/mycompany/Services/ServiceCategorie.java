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
import com.mycompany.Entite.Categorie;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceCategorie {

    public void ajoutCat(Categorie cat) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PidevHappyoldsSymfony/web/app_dev.php/cat/new?type="+cat.getType();
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public ArrayList<Categorie> parseListTaskJson(String json) {

        ArrayList<Categorie> listTasks = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

          
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
            
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            
            for (Map<String, Object> obj : list) {
                ServiceAnimateur sa = new ServiceAnimateur();
                Categorie e = new Categorie();
                
                float id = Float.parseFloat(obj.get("idCategoriea").toString());
                e.setId((int) id);
                e.setType(obj.get("type").toString());
                
                System.out.println(e);
                
                listTasks.add(e);

            }

        } catch (IOException ex) {
        }
        

        System.out.println(listTasks);
        return listTasks;

    }
    
    
    ArrayList<Categorie> listCategories = new ArrayList<>();
    
    public ArrayList<Categorie> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PidevHappyoldsSymfony/web/app_dev.php/cat/all");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceCategorie ser = new ServiceCategorie();
                listCategories = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listCategories;
    }
    
    
    

}
