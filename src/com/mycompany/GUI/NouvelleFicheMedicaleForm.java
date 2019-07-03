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
import com.codename1.components.FloatingActionButton;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.InfiniteScrollAdapter;
import com.codename1.components.InteractionDialog;
import com.codename1.components.MultiButton;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.Entity.Fiche_medicale;
import com.mycompany.Entity.Resident;
import com.mycompany.Services.ServiceFicheMedicale;
import com.mycompany.Services.ServicePrestation;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.ArrayList;
import java.util.Map;

/**
 * Represents a user profile in the app, the first form we open after the walkthru
 *
 * @author Shai Almog
 */
public class NouvelleFicheMedicaleForm extends SideMenuBaseForm {
     Resources resources;
    public NouvelleFicheMedicaleForm(Resources res,String idResident,Resident resident) {
        super(BoxLayout.y());
         this.resources=res;
        
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("elder-couple.png");
        //Image profilePic = res.getImage("user-picture.jpg");        
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
        FontImage.setMaterialIcon(settingsButton, FontImage.MATERIAL_ARROW_BACK);
        settingsButton.addActionListener( e -> {
          //  Dialog ip = new InfiniteProgress().showInfiniteBlocking();
          new Loader(this.resources);
            new PrestationForm(res).show();
           // ip.dispose();
        });
        
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
                                new Label("Nouvelle Fiches Medicales", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
                        
    
        
        
        ServiceFicheMedicale sp=new ServiceFicheMedicale();
        TextField medicaments = new TextField("", "medicaments", 20, TextArea.ANY);
        TextField Remarques = new TextField("", "Remarques", 20, TextArea.ANY);
        Remarques.setUIID("edited textfield");
        medicaments.setUIID("edited textfield");
         Slider sliderTemp = new Slider();
               sliderTemp.setMinValue(360);
               sliderTemp.setMaxValue(410);
               sliderTemp.setIncrements(1);
               sliderTemp.setProgress(370);
               sliderTemp.setEnabled(true);
               sliderTemp.setEditable(true);
              // sliderTemp.setUIID("slider");
               
          Slider sliderSuc = new Slider();
               sliderSuc.setMinValue( 700);
               sliderSuc.setMaxValue( 1400);
               sliderSuc.setIncrements( 100);
               sliderSuc.setProgress(900);
               sliderSuc.setEnabled(true);
               sliderSuc.setEditable(true);
             //  slider.setUIID("slider");
          Slider sliderTension = new Slider();
               sliderTension.setMinValue( 108);
               sliderTension.setMaxValue(250);
               sliderTension.setIncrements( 01);
               sliderTension.setProgress( 125);
               sliderTension.setEnabled(true);
               sliderTension.setEditable(true);
               
          Slider sliderPoids = new Slider();
               sliderPoids.setMinValue(600);
               sliderPoids.setMaxValue(1200);
               sliderPoids.setIncrements(1);
               sliderPoids.setProgress(750);
               sliderPoids.setEnabled(true);
               sliderPoids.setEditable(true);
               
          Slider sliderTaille = new Slider();
               sliderTaille.setMinValue(1500);
               sliderTaille.setMaxValue(1900);
               sliderTaille.setIncrements(1);
               sliderTaille.setProgress(1700);
               sliderTaille.setEnabled(true);
               sliderTaille.setEditable(true);
               
          ComboBox<String> combo = new ComboBox("A+","A-","B+","AB+","AB-","O+","O-");
      
         
          
         Label nom = new Label("Nom : "+resident.getNom_resident());
            Label prenom = new Label("Prénom : "+resident.getPrenom_resident());
            Label nivTemp = new Label("Niveau de temperature : "+(float) sliderTemp.getProgress()/10+"°");
            Label nivSuc = new Label("Niveau de sucre : "+(float) sliderSuc.getProgress()/1000+"g");
            Label nivtension = new Label("Niveau de tension : "+(float)sliderTension.getProgress()/10+"cmHg");
            Label groupS = new Label("Groupe sanguin : ");
            Label poids = new Label("Poids : "+(float)sliderPoids.getProgress()/10+"kg");
            Label taille = new Label("Taille : "+(float)sliderTaille.getProgress()/10+"cm");
            
             combo.addActionListener( ee->{
                   groupS.setText("Groupe sanguin : "+combo.getSelectedItem());
               });
             sliderTemp.addActionListener( ee->{
                   nivTemp.setText("Niveau de temperature : "+(float) sliderTemp.getProgress()/10+"°");
               });
             sliderSuc.addActionListener( ee->{
                   nivSuc.setText("Niveau de sucre : "+(float) sliderSuc.getProgress()/1000+"g");
               });
            sliderTension.addActionListener( ee->{
                   nivtension.setText("Niveau de tension : "+(float)sliderTension.getProgress()/10+"cmHg");
               });
             sliderPoids.addActionListener( ee->{
                   poids.setText("Poids : "+(float)sliderPoids.getProgress()/10+"kg");
               });
              sliderTaille.addActionListener( ee->{
                   taille.setText("Taille : "+(float)sliderTaille.getProgress()/10+"cm");
               });
            Button ajouter = new Button("Ajouter");
        
      //  d.setLayout(BoxLayout.y());
      
      Container c1sliderTemp = BorderLayout.west(new Label("36°")).add(BorderLayout.EAST, new Label("41°")).add(BorderLayout.CENTER, sliderTemp);
      Container c2sliderSuc = BorderLayout.west(new Label("0.7 g")).add(BorderLayout.EAST, new Label("1.4 g")).add(BorderLayout.CENTER, sliderSuc);
      Container c3sliderTension = BorderLayout.west(new Label("10.8 cmHg")).add(BorderLayout.EAST, new Label("25 cmHg")).add(BorderLayout.CENTER, sliderTension);
      Container c4sliderPoids = BorderLayout.west(new Label("60 kg")).add(BorderLayout.EAST, new Label("120 kg")).add(BorderLayout.CENTER, sliderPoids);
      Container c5sliderTaille = BorderLayout.west(new Label("150 cm")).add(BorderLayout.EAST, new Label("190 cm")).add(BorderLayout.CENTER, sliderTaille);

       
                add(nom).add(prenom).add(nivTemp).add(c1sliderTemp).
                        add(nivSuc).add(c2sliderSuc).
                        add(nivtension).add(c3sliderTension).
                        add(groupS).add(combo).
                        add(poids).add(c4sliderPoids).
                        add(taille).add(c5sliderTaille).
                         add( medicaments).
                         add( Remarques).
                         add(ajouter);
                
                  
        
                ajouter.addActionListener(l ->{
                    if(medicaments.getText().equals("") || Remarques.getText().equals(""))
                        Dialog.show("Attention !!", "Certains champs ne sont pas valides", "ok",null);
                  else{
                      Fiche_medicale fiche = new Fiche_medicale();
                        fiche.setGroupeSanguin(combo.getSelectedItem());
                        fiche.setMedicaments(medicaments.getText());
                        fiche.setNiveauSuc((float) sliderSuc.getProgress()/1000);
                        fiche.setNiveauTemp((float) sliderTemp.getProgress()/10);
                        fiche.setNiveauTension((float)sliderTension.getProgress()/10);
                        fiche.setPoids_resident((float)sliderPoids.getProgress()/10);
                        fiche.setTaille_resident((float)sliderTaille.getProgress()/10);
                        fiche.setRemarques_fiche(Remarques.getText());
                        
                            if(Dialog.show("Envoi d'un sms", "Voulez vous envoyer lui un sms de notification", "oui", "non")){

                                //sms 
                           Twilio.init("ACcfc24b31c59bdeea6c13e08746710932", "4400020cabfd4fe3f78f1721272564a7");

                       Message message = Message
                               .creator(new PhoneNumber("+21622148971"), // to
                                       new PhoneNumber("+12512415658"), // from
                                       "vous avez une nouvelle fiche médicale !")
                               .create();

                       System.out.println(message.getSid());
                       //end
                    }
                    new Loader(this.resources);
                        sp.ajoutFiche(fiche, idResident);
                      //  System.out.println("after : "+fiche);
                       new FicheMedicaleForm(this.resources, idResident, resident).show();
                        //d.dispose();
                    }//else
                });
                
        setupSideMenu(res);
    }
    

    
  
    
    private void addButtonBottom(Image arrowDown, String text, int color, boolean first) {
        MultiButton finishLandingPage = new MultiButton(text);
        finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(),  first));
        finishLandingPage.setIconUIID("Container");
        add(FlowLayout.encloseIn(finishLandingPage));
        
        finishLandingPage.addActionListener(al -> {
        ToastBar.showMessage("clicked : ",FontImage.MATERIAL_GRAIN);
        });
    }
    
    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if(first) {
            y = height / 6 + 1;
        }
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
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
