/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.GUI;

import com.mycompany.myapp.*;
import com.mycompany.SideMenu.SideMenuBaseForm;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.XYMultipleSeriesDataset;
import com.codename1.charts.models.XYSeries;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer;
import com.codename1.charts.renderers.XYSeriesRenderer;
import com.codename1.charts.views.CubicLineChart;
import com.codename1.charts.views.PointStyle;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.table.TableModel;
import com.codename1.ui.util.Resources;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.validation.Constraint;
import com.mycompany.Services.ServiceAnimateur;
import com.codename1.ui.validation.Validator;
import com.mycompany.Entite.Animateur;
import java.util.ArrayList;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

/**
 *
 * @author Shai Almog
 */
public class AffichageAnimateurForm extends SideMenuBaseForm {
    private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};
    
     public static  Resources resources;
    
    
     Label lb;
    Button view, sup;
    
     Label lnom, lprn, lmail, ltel, lad;
    TextField txtnom, txtprn, txtmail, txttel, txtad;
    Button btnajoutan;
    
    Button btnaff, btnlistan;

    public AffichageAnimateurForm(Resources res) {
       super(BoxLayout.y());
        
        Image profilePic = res.getImage("elder-couple.png");
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
            
        Image tintedImage = Image.createImage(profilePic.getWidth(), profilePic.getHeight());
        Graphics g = tintedImage.getGraphics();
        g.drawImage(profilePic, 0, 0);
        g.drawImage(res.getImage("gradient-overlay.png"), 0, 0, profilePic.getWidth(), profilePic.getHeight());
        
        tb.getUnselectedStyle().setBgImage(tintedImage);
        
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Button settingsButton = new Button("");
        settingsButton.setUIID("Title");
        FontImage.setMaterialIcon(settingsButton, FontImage.MATERIAL_SETTINGS);
        
        Label space = new Label("", "TitlePictureSpace");
        space.setShowEvenIfBlank(true);
        Container titleComponent = 
                BorderLayout.north(
                    BorderLayout.west(menuButton).add(BorderLayout.EAST, settingsButton)
                ).
                add(BorderLayout.CENTER, space).
                add(BorderLayout.SOUTH, 
                        FlowLayout.encloseIn(
                                //new Label("  Jennifer ", "WelcomeBlue"),
                                new Label("Liste Animateur", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
                        
        add(new Label("Today", "TodayTitle"));
        
        //////////////////////////////////////////////////////////////////////////////////////////////////////////
       
        lb = new Label();

        view = new Button("détaille");
        sup = new Button("supprimer");

        ServiceAnimateur serviceTask = new ServiceAnimateur();
        ArrayList<Animateur> listanim = serviceTask.getList2();

        InfiniteContainer ic;
        for (Animateur o : listanim) {
            ic = new InfiniteContainer() {
                @Override
                public Component[] fetchComponents(int index, int amount) {

                    amount = listanim.size() - index;

                    Component[] cmp = new Component[amount];

                    for (int iter = 0; iter < amount; iter++) {

                        Container c = new Container(BoxLayout.y());

                        String id = String.valueOf(listanim.get(iter).getIdAnimateur());
                        String n = String.valueOf(listanim.get(iter).getNomAnimateur());
                        String p = String.valueOf(listanim.get(iter).getPrenomAnimateur());
                        String m = String.valueOf(listanim.get(iter).getMailAnimateur());
                        String t = String.valueOf(listanim.get(iter).getTelAnimateur());
                        String a = String.valueOf(listanim.get(iter).getAdresseAnimateur());
                        String d = String.valueOf(listanim.get(iter).getDispo());

                        Label lid = new Label(id);

                        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(getWidth(),getWidth() / 5, 0xffff0000), true);
                        URLImage background = URLImage.createToStorage(placeholder, listanim.get(iter).getImage(), "http://localhost/PidevHappyoldsSymfony/web/uploads/post/" + listanim.get(iter).getImage());
                        background.fetch();

                        Label i = new Label();
                        i.setIcon(background);
                        i.setHeight(500);

                        Button b = new Button("détaille");
                        Button s = new Button("supprimer");

                        b.setHeight(70);

                        s.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {

                                ServiceAnimateur s = new ServiceAnimateur();
                                Dialog.show("Voulez vous supprimer?", "", "OK", "Cancel");
                                s.supprimer(o);
                                AffichageAnimateurForm h = new AffichageAnimateurForm(res);
                                h.show();

                                /**
                                 * **************APISMS****************
                                 */
                                /*   
                                String accountSID = "AC3bd9017f77b3ae36eb2f4d6e95687ec1";
String authToken = "af441e5e8e3d5c12249faeeed519dc7e";
String fromPhone = "+216 27 914 140";
Random r = new Random();
String val = "" + r.nextInt(10000);
while(val.length() < 4) {
    val = "0" + val;
}
                                String destinationPhone = "+216 27 914 140";
                Response<Map> result = Rest.post("https://api.twilio.com/2010-04-01/Accounts/" + accountSID + "/Messages.json").
        queryParam("To", destinationPhone).
        queryParam("From", fromPhone).
        queryParam("Body", val).
        header("Authorization", "Basic " + Base64.encodeNoNewline((accountSID + ":" + authToken).getBytes())).
        getAsJsonMap();
        /*                      
                                
            AuthMethod auth = new TokenAuthMethod("ba8ff2bf", "Tux2U0JVKTQyZgGN");  // (api_key,api_secret)
                        NexmoClient client = new NexmoClient(auth);
                        SmsSubmissionResult[] responses;
            try {
                responses = client.getSmsClient().submitMessage(new TextMessage(
                        "Chahir",
                        "+21627914140",
                        "Merci"));
                for (SmsSubmissionResult response : responses) {
                                System.out.println(response);
                            }
            } catch (IOException | NexmoClientException ex) {
               
            }
                                 */
                            }

                        });

                        b.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt
                            ) { 
                                DetailAnimateurForm detail = new DetailAnimateurForm(o,res);
                            detail.show();
                            }
                                /*
                                Form hi1 = new Form(n, new BoxLayout(CENTER).y());

                                Label lb = new Label("Nom :" + n);
                                Label lb1 = new Label("Prenom :" + p);
                                Label lb2 = new Label("Mail :" + m);
                                Label lb3 = new Label("Numero telephone :" + t);
                                Label lb4 = new Label("Adresse :" + a);
                                Label lb5 = new Label("Etat :" + d);
                                Button mdf = new Button("Modifier");

                                mdf.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evt) {

                                        Form upd = new Form(n, new BoxLayout(CENTER).y());
                                        Label l1 = new Label("Nom :");
                                        Label l2 = new Label("Prenom :");
                                        Label l3 = new Label("Mail :");
                                        Label l4 = new Label("Adresse :");
                                        Label l5 = new Label("Numero telephone :");

                                        TextField txt1 = new TextField(n);
                                        TextField txt2 = new TextField(p);
                                        TextField txt3 = new TextField(m);
                                        TextField txt4 = new TextField(a);
                                        TextField txt5 = new TextField(t);
                                        Button update = new Button("Modifier");

                                        update.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent evt) {
                                                ServiceAnimateur s = new ServiceAnimateur();

                                                o.setNomAnimateur(txt1.getText());
                                                o.setPrenomAnimateur(txt2.getText());
                                                o.setMailAnimateur(txt3.getText());
                                                o.setAdresseAnimateur(txt4.getText());
                                                o.setTelAnimateur(Integer.parseInt(txt5.getText()));

                                                System.out.println(o);
                                                s.modifanimateur(o);

                                                hi1.show();

                                            }
                                        });

                                        upd.add(l1).add(txt1);
                                        upd.add(l2).add(txt2);
                                        upd.add(l3).add(txt3);
                                        upd.add(l4).add(txt4);
                                        upd.add(l5).add(txt5);
                                        upd.add(update);

                                        upd.show();
                                        upd.getToolbar().addCommandToRightBar("Retour", null, (ev) -> {
                                            Affichage h = new Affichage();
                                            h.getF().show();
                                        });

                                    }
                                });

                                Label i = new Label();
                                i.setIcon(background);
                                i.setHeight(500);

                                hi1.add(i);
                                hi1.add(lb);
                                hi1.add(lb1);
                                hi1.add(lb2);
                                hi1.add(lb3);
                                hi1.add(lb4);
                                hi1.add(lb5);
                                hi1.add(mdf);

                                hi1.show();
                                hi1.getToolbar().addCommandToRightBar("Retour", null, (ev) -> {
                                    Affichage h = new Affichage();
                                    h.getF().show();
                                });

                            }*/

                        });

                        c.add(i);

                        c.add(lid);
                        c.add("nom: " + n);
                        c.add(b).add(s);

                        cmp[iter] = c;

                    }
                    return cmp;
                }
            };

            add(ic);

        }

        getToolbar().addCommandToRightBar("Ajouter", null, (ev) -> {
            AjouterAnimateurForm h = new AjouterAnimateurForm(res);
            h.showBack();
        });

        getToolbar().addCommandToRightBar("Retour", null, (ev) -> {
            ActiviteForm h = new ActiviteForm(res);
            h.show();

        });
        /////////////////////////////////////////////////////////////////////////////////////////////////////////
        
       
        setupSideMenu(res);
    }
    private void showToast(String text) {
        Image errorImage = FontImage.createMaterial(FontImage.MATERIAL_ERROR, UIManager.getInstance().getComponentStyle("Title"), 4);
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage(text);
        status.setIcon(errorImage);
        status.setExpires(2000);
        status.show();
    }

    @Override
    protected void showOtherForm(Resources res,String formName) {
        
        this.resources=res;
        switch (formName) {
            case "Inscrivez vous":
                new Loader(res);
                new InscriptionForm(res).show();
                break;
            case "Activité":
                new Loader(res);
                new ActiviteForm(res).show();
                break;
            case "Dons":
                new Loader(res);
                new DonsForm(res).show();
                break;
            case "Actions benevole":
                new Loader(res);
                new ActionForm(res).show();
                break;
            case "Evènement":
                new Loader(res);
                new EventForm(res).show();
                break;
            case "Préstation santé":
                new Loader(res);
                
                new PrestationForm(res).show();      
                break;
            case "Profil":
                new Loader(res);
                new ProfileForm(res).show();
                break;
            default:
                break;
        }
        
           
        
    }

  
}
