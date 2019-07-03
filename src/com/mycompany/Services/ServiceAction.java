
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
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entite.ActionBenevole;
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
 * @author asus
 */
public class ServiceAction {

    public void ajoutAction(ActionBenevole ac) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/PidevHappyoldsSymfony/web/app_dev.php/newj?dateDAction=" + ac.getDateDAction()
                + "&dateFAction=" + ac.getDateFAction() + "&description=" + ac.getDescription();
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    Form f;
    SpanLabel lb;

    public void supprimerAction(int id) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/PidevHappyoldsSymfony/web/app_dev.php/deletej/" + id + "";
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
        NetworkManager.getInstance().addToQueueAndWait(con);
        f = new Form();
        lb = new SpanLabel("");
        f.add(lb);
        ServiceAction serviceTask = new ServiceAction();
        lb.setText(serviceTask.getList2().toString());
       /* f.getToolbar().addCommandToRightBar("back", null, (ev) -> {
            HomeForm h = new HomeForm();
            h.getF().show();
        });*/

    }
    

    public ArrayList<ActionBenevole> parseListTaskJson(String json) throws ParseException {

        ArrayList<ActionBenevole> listAction = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                ActionBenevole a = new ActionBenevole();
                float id = Float.parseFloat(obj.get("idAction").toString());
                a.setIdAction((int) id);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date datedebut = df.parse((String) obj.get("dateDAction"));
                a.setDateDAction(datedebut);

                Date datefin = df.parse((String) obj.get("dateFAction"));
                a.setDateFAction(datefin);

                a.setDescription(obj.get("description").toString());
                System.out.println(a);

                listAction.add(a);

            }

        } catch (IOException ex) {
        }

        System.out.println(listAction);
        return listAction;

    }

    ArrayList<ActionBenevole> listActions = new ArrayList<>();

    public ArrayList<ActionBenevole> getList2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PidevHappyoldsSymfony/web/app_dev.php/all");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                ServiceAction act = new ServiceAction();

                try {
                    String str = new String(con.getResponseData());
                    System.out.println(str);
                    listActions = act.parseListTaskJson(str);

                } catch (ParseException ex) {
                    // Logger.getLogger(ServiceAction.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listActions;
    }
    public void modifier(ActionBenevole a1){
     ConnectionRequest con = new ConnectionRequest();
     String Url = "http://localhost/PidevHappyoldsSymfony/web/app_dev.php/modifj/"
             + a1.getIdAction()
             + "?dateDAction=" +a1.getDateDAction()
             + "&dateFAction=" + a1.getDateFAction() + "&description=" + a1.getDescription()
             +"";
        con.setUrl(Url);
          con.addResponseListener((a) -> {
            String str = new String(con.getResponseData());
            

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}
