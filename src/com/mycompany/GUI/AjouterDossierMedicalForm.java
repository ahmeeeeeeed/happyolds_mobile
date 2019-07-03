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
import com.mycompany.Entity.Allergie;
import com.mycompany.Entity.Dossier;
import com.mycompany.Entity.Fiche_medicale;
import com.mycompany.Entity.Prescription;
import com.mycompany.Entity.Resident;
import com.mycompany.Services.ServiceDossier;
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
public class AjouterDossierMedicalForm extends SideMenuBaseForm {
     Resources resources;
    public AjouterDossierMedicalForm(Resources res,String idResident,Resident resident) {
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
                                new Label("Nouveau Dossier", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
                        
        
      add("Données personnelle : ");
           TextField probleme = new TextField("", "Problémes de santé", 20, TextArea.ANY);
           TextField nbvisite = new TextField("", "Nombre de visite", 2, TextArea.NUMERIC);
           probleme.setUIID("edited textfield");
           nbvisite.setUIID("edited textfield");
            add(ComponentGroup.enclose(new Label("nom : "+resident.getNom_resident()),
                    new Label("prenom : "+resident.getPrenom_resident()),
                    new Label("age : "+resident.getAge_resident()),
                    new Label("sexe : "+resident.getSexe_resident()),
                  //  new Label("nombre de visite : "),
                   // new Label("Problémes de santé :"),
                    probleme,
                    nbvisite
                    ));  
         add("Allergies et antécédants : ");
           TextField allergie = new TextField("", "Nom de l'allergie", 20, TextArea.ANY);
            TextField antecedant = new TextField("", "Antécédants", 20, TextArea.ANY);
            allergie.setUIID("edited textfield");
            antecedant.setUIID("edited textfield");
            
            add(ComponentGroup.enclose(new Label("Nom de l'allergie :"),
                    allergie,
                    new Label("Antécédants : "),
                    antecedant
                    )); 
         add("Prescription de médicaments : ");
           TextField desc = new TextField("", "Descriptions ", 20, TextArea.ANY);
           desc.setUIID("edited textfield");
          
            add(ComponentGroup.enclose(
                    new Label("Descriptions  : "),
                    desc
                    )); 
          Button ajouter = new Button("Ajouter");
          add(ajouter);
          
        
          
          ajouter.addActionListener( e ->{
              if(nbvisite.getText().equals("") || 
                      probleme.getText().equals("") || 
                      desc.getText().equals("")|| 
                      antecedant.getText().equals("")||
                      allergie.getText().equals("")
                )
                  Dialog.show("attention !!", "Certains champs ne sont pas valides", "ok",null);
                  else{
                  
              
                  
              
                Dossier d = new Dossier();
                Allergie a =new Allergie();
             
                Prescription p = new Prescription();
                
                ServiceDossier sd = new ServiceDossier();
                
                float nb = Float.parseFloat(nbvisite.getText());
                d.setNbVisite((int) nb);
                d.setProblemesSante(probleme.getText());
                
                a.setAntecedants(antecedant.getText());
                a.setDescriptionAllergie(allergie.getText());
                
                p.setDescriptionMedicament(desc.getText());
                
                if(Dialog.show("Envoi d'un sms", "Voulez vous envoyer lui un sms de notification", "oui", "non")){
                    
                            //sms 
                       Twilio.init("ACcfc24b31c59bdeea6c13e08746710932", "4400020cabfd4fe3f78f1721272564a7");

                   Message message = Message
                           .creator(new PhoneNumber("+21622148971"), // to
                                   new PhoneNumber("+12512415658"), // from
                                   "votre dossier médicale a été créé !")
                           .create();

                   System.out.println(message.getSid());
                   //end
                }
                // new Loader(res);
                sd.ajoutDossier(d, a, p, idResident);
                new DossierMedicalForm(res, idResident,resident);
                  
              }//else
                
          });
          
      
        setupSideMenu(res);
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
