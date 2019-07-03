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
 * @author poste
 */
public class ServiceResident {
     ArrayList<String> listt ;
         public void ajoutRes(Resident re) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PidevHappyoldsSymfony/web/app_dev.php/ajouterResidentMobile/"+re.getMaison().getIdMaison() +"?nomResident="+ re.getNom_resident()+ "&prenomResident=" + re.getPrenom_resident()+ "&responsable=" + re.getResponsable() + "&telephoneResponsable=" + re.getTelephoneResponsable() + "&sexeResident=" + re.getSexe_resident()+ "&ageResident=" + re.getAge_resident();
        con.setUrl(Url);
//        String Url = "http://localhost/PidevHappyoldsSymfony/web/app_dev.php/ajouterResidentMobile/"+re.getMaison().getIdMaison()+"?nomResident="+ re.getNomResident()

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        
//            int n= r.setMaison(r.setMaison(getMaison().getNbrPersonne()+1));
            
    }
         
//              public void suppTask (int id) {
//        try {
//        ConnectionRequest con = new ConnectionRequest();
//            con.setPost(true);
//            con.setContentType("application/json");
//            con.setUrl("http://localhost/symfony/web/app_dev.php/BonPlan/SupprimerEtablissement/" + id);
//            con.addResponseListener((e) -> {
//                String str = new String(con.getResponseData());
//                System.out.println(str);
//                    
//                            
//
//            });
//            NetworkManager.getInstance().addToQueueAndWait(con);
//        } catch (Exception err) {
//            System.err.println(err.getMessage());
//        }
//    }
     
    public ArrayList<String> getByID(String name)
    {
        ArrayList<String> listMaison = new ArrayList<String>();
        
        String nom = name.substring(32,38) ; 
        listMaison.add(nom);
            System.out.println(nom);
            
            //ArrayList<Animateur> listanim =  parseListTasJson(name);
            
            return listMaison ; 
         // parseListTasJson(String name);
    }
    
     public ArrayList<Resident> parseListTaskJson(String json) {

        ArrayList<Resident> listTasks = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

          
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
            
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            
            for (Map<String, Object> obj : list) {
                
                Resident r = new Resident();
                           
                float id = Float.parseFloat(obj.get("idResident").toString());
                float age = Float.parseFloat(obj.get("ageResident").toString());
                r.setId_resident((int) id);
                r.setNom_resident(obj.get("nomResident").toString());
                r.setPrenom_resident(obj.get("prenomResident").toString());
                r.setAge_resident((int) age);
                r.setResponsable(obj.get("responsable").toString());
                r.setTelephoneResponsable(obj.get("telephoneResponsable").toString());
                r.setSexe_resident(obj.get("sexeResident").toString());
                r.setDateResident(obj.get("dateResident").toString());
                r.setEtat(obj.get("etat").toString());          
                              
              System.out.println(r);
               
                listTasks.add(r);

            }

        } catch (IOException ex) {
        }
       System.out.println(listTasks);
       
        return listTasks;

    }
    
    ArrayList<Resident> listResident = new ArrayList<>();
    
    public ArrayList<Resident> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PidevHappyoldsSymfony/web/app_dev.php/allre");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceResident ser = new ServiceResident();
                listResident = ser.parseListTaskJson(new String(con.getResponseData()));
              //  System.out.println( listt.get(0));   
            }
            
        });
        
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listResident;
    }
        public void deletResident(Resident r) {

        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/pepinieremobile2/web/app_dev.php/plantsitting/Offrejardin/supprimer";

        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
        con.addArgument("idResident", ""+r.getId_resident());
       
        con.addResponseListener((e) -> {
           
            System.out.println("done");//Affichage de la réponse serveur sur la console

        });

        NetworkManager.getInstance().addToQueueAndWait(con);//
    }
                
        public void supprimerResident(Resident r) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/PidevHappyoldsSymfony/web/app_dev.php/deleteMobile/" + r.getId_resident();// création de l'URL
         //  System.out.println(r.getIdResident());
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    
         }
        
        public void modifier(Resident r){
     ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PidevHappyoldsSymfony/web/app_dev.php/modifMobile/" 
                              + r.getId_resident()+ "/" + r.getPrenom_resident();
//+ "/"
//                + r.getNomResident() + "/" + r.getAgeResident() + "/" + r.getSexeResident() + "/" + r.getResponsable() + "/" + r.getTelephoneResponsable()
        con.setUrl(Url);
            System.out.println(Url);
          con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
        public void Valider(int idM){
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PidevHappyoldsSymfony/web/app_dev.php/ValiderMobile/" +idM;
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println("Done");

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}
