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
import com.mycompany.Services.ServiceAllergie;
import com.mycompany.Services.ServiceDossier;
import com.mycompany.Services.ServiceFicheMedicale;
import com.mycompany.Services.ServicePrescription;
import com.mycompany.Services.ServicePrestation;
import java.util.ArrayList;
import java.util.Map;

/**
 * Represents a user profile in the app, the first form we open after the walkthru
 *
 * @author Shai Almog
 */
public class DossierMedicalForm extends SideMenuBaseForm {
     Resources resources;
    public DossierMedicalForm(Resources res,String idResident,Resident resident) {
        super(BoxLayout.y());
         this.resources=res;
        
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
Image profilePic = res.getImage("elder-couple.png");       
// Image profilePic = res.getImage("user-picture.jpg");        
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
                                new Label("Dossier médical", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
                        
        ServiceDossier sd = new ServiceDossier();
        ServiceAllergie sa = new ServiceAllergie();
        ServicePrescription sp = new ServicePrescription();
        Dossier dossier = sd.getDossierByResident(idResident);
        
         ArrayList<Allergie> listallergie = sa.getListAllergieByDossier(idResident);
         ArrayList<Prescription> listpresc = sp.getListPrescriptionByDossier(idResident);
      
        Button modifDonneesPersonnelles = new Button();
        FontImage.setMaterialIcon(modifDonneesPersonnelles, FontImage.MATERIAL_SETTINGS);
      Container c1  = new Container(BoxLayout.x());//pour labelle et bouton modifier
      c1.add("Données personnelle : ").add(modifDonneesPersonnelles);
      add(c1);
            
            add(ComponentGroup.enclose(new Label("nom : "+resident.getNom_resident()),
                    new Label("prenom : "+resident.getPrenom_resident()),
                    new Label("age : "+resident.getAge_resident()),
                    new Label("sexe : "+resident.getSexe_resident()),
                    new Label("nombre de visite : "+String.valueOf(dossier.getNbVisite())),
                   //  new Label(String.valueOf(dossier.getNbVisite())),
                    new Label("probleme de santé : "+dossier.getProblemesSante())
                 //   new Label(dossier.getProblemesSante())
                   
                    ));  
           
            modifDonneesPersonnelles.addActionListener((evt) -> {
                 Dialog d = new Dialog("Modification :");
        
                TextField nbVisite = new TextField(String.valueOf(dossier.getNbVisite()), "Nombre de visite", 20, TextArea.NUMERIC);
               TextField problemesSante = new TextField(dossier.getProblemesSante(), "probleme de santé", 20, TextArea.ANY);
               nbVisite.setUIID("edited textfield");
               problemesSante.setUIID("edited textfield");
               Button modify = new Button("Modifier");
               
           /*    nbVisite.addDataChangedListener((i, ii) -> {
                    if(isValidInput(nbVisite.getText())) {
                       nbVisite.putClientProperty("LastValid", nbVisite.getText());
                    } else {
                       nbVisite.stopEditing();
                       String text = (String)nbVisite.getText();
                       
                       text = text.substring(0,text.length()-1);
                       nbVisite.setText(text);
                       //  nbVisite.setText((String)nbVisite.getClientProperty("LastValid"));
                       nbVisite.startEditingAsync();
                    }
                });*/
            
               modify.addActionListener(l ->{
                   if(nbVisite.getText().equals("")){
                        Dialog.show("Attention !!", "Certains champs ne sont pas valides", "ok",null);
                   }
                    else{
                       
                   
                   float nb = Float.parseFloat(nbVisite.getText());
                   
                   dossier.setNbVisite( (int) nb);
                   dossier.setProblemesSante(problemesSante.getText());
                  //  new Loader(this.resources);
                        sd.modifierDossier(dossier);
                   
                       new DossierMedicalForm(this.resources, idResident,resident).show();
                   }//else
                      
                });
               
               d.add(nbVisite).add(problemesSante).add(modify);
               
               d.showPopupDialog(modifDonneesPersonnelles);
               
            });
            
     
            
         Button ajoutDonneesAllergie = new Button("Ajouter");
        FontImage.setMaterialIcon(modifDonneesPersonnelles, FontImage.MATERIAL_SETTINGS);
      Container c2  = new Container(BoxLayout.x());//pour labelle et bouton modifier
      
      add("--------------------------------------------");
      c2. add("Allergies et antécédants : ").add(ajoutDonneesAllergie);
      add(c2);
          
      
      //container for allergie list
       InfiniteContainer icAllergie = new InfiniteContainer() {
        @Override
        public Component[] fetchComponents(int index, int amount) {

            amount = listallergie.size() - index ;  
            
            Component[] cmp = new Component[amount];
            
            for(int iter = 0 ; iter < amount ; iter++) {
                int id = listallergie.get(iter).getId_allergie();
                 Button supprimerAllergie = new Button();
             
                 FontImage.setMaterialIcon(supprimerAllergie, FontImage.MATERIAL_DELETE);
                
               Container c  = new Container(BoxLayout.y());
                Container cc  = new Container(BoxLayout.x());
               
              //  System.out.println(listallergie.get(iter).getAntecedants());
       
                c.add(new Label("Nom de l'allergie : "+listallergie.get(iter).getDescriptionAllergie()));
                
               // c.add(new Label(listallergie.get(iter).getDescriptionAllergie()));
                cc.add(new Label("Antécédants : "+listallergie.get(iter).getAntecedants()));
                cc.add(supprimerAllergie);
                c.add(cc);
                //c.add(new Label(listallergie.get(iter).getAntecedants()));
              //  c.add(supprimerAllergie);
                c.add("--------------------------------------------");
                
               cmp[iter] = c;          
               
               supprimerAllergie.addActionListener((evt) -> {
                   new Loader(resources);
                   sa.supprimerAllergie(id);
                    new DossierMedicalForm(resources, idResident,resident).show();
               });
            }
            return cmp;
        }
};
           
            add(icAllergie);
            
             ajoutDonneesAllergie.addActionListener((evt) -> {
                 Dialog d = new Dialog("Ajouter allergie :");
        
                TextField nomallergie = new TextField("", "Nom de l'allergie ", 20, TextArea.ANY);
               TextField antecedant = new TextField("", "Antécédants ", 20, TextArea.ANY);
               nomallergie.setUIID("edited textfield");
               antecedant.setUIID("edited textfield");
               Button ajouter = new Button("Ajouter");
               
     
            
               ajouter.addActionListener(l ->{
                   
                   Allergie a = new Allergie();
                   a.setAntecedants(antecedant.getText());
                   a.setDescriptionAllergie(nomallergie.getText());
                   a.setId_dossier(dossier.getId_dossier());
                    new Loader(this.resources);
                        sa.ajoutAllergie(a);
                   
                       new DossierMedicalForm(this.resources, idResident,resident).show();
                      
                });
               
               d.add(nomallergie).add(antecedant).add(ajouter);
               
               d.showPopupDialog(modifDonneesPersonnelles);
            });


            Button ajoutMedicaments = new Button("Ajouter");
        FontImage.setMaterialIcon(modifDonneesPersonnelles, FontImage.MATERIAL_SETTINGS);
      Container c3  = new Container(BoxLayout.x());//pour labelle et bouton modifier
      c3. add("Prescription de médicaments : ").add(ajoutMedicaments);
      add(c3);


 //container for prescriptions list
       InfiniteContainer icPres = new InfiniteContainer() {
        @Override
        public Component[] fetchComponents(int index, int amount) {

            amount = listpresc.size() - index ;  
            
            Component[] cmp = new Component[amount];
            
            for(int iter = 0 ; iter < amount ; iter++) {
                 int id = listpresc.get(iter).getId_prescription();
                 Button supprimerPres = new Button();
             
                 FontImage.setMaterialIcon(supprimerPres, FontImage.MATERIAL_DELETE);
                
               Container c  = new Container(BoxLayout.y());
               Container cc  = new Container(BoxLayout.x());
               
                             
                cc.add(new Label("Descriptions : "+listpresc.get(iter).getDescriptionMedicament()));

                cc.add(supprimerPres);
                c.add(cc);
              //  c.add("--------------------------------------------");
                
               cmp[iter] = c;         
               
                 supprimerPres.addActionListener((evt) -> {
                   new Loader(resources);
                   sp.supprimerPrescription(id);
                    new DossierMedicalForm(resources, idResident,resident).show();
               });
            }
            return cmp;
        }
};
           
            add(icPres);        
            
            ajoutMedicaments.addActionListener((evt) -> {
                 Dialog d = new Dialog("Ajouter médicament :");
        
                TextField medicament = new TextField("", "Prescription de medicament ", 20, TextArea.ANY);
               medicament.setUIID("edited textfield");
              
               Button ajouter = new Button("Ajouter");
               
     
            
               ajouter.addActionListener(l ->{
                   
                 Prescription p = new Prescription();
                 p.setDescriptionMedicament(medicament.getText());
                p.setId_dossier(dossier.getId_dossier());
                    new Loader(this.resources);
                        sp.ajoutPrescription(p);
                   
                       new DossierMedicalForm(this.resources, idResident,resident).show();
                      
                });
               
               d.add(medicament).add(ajouter);
               
               d.showPopupDialog(modifDonneesPersonnelles);
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
    public boolean isValidInput(String input){

    if(input.contains("a") || input.contains("b") || input.contains("c") 
    || input.contains("d") || input.contains("e") || input.contains("f")
    || input.contains("g") || input.contains("h") || input.contains("i")
    || input.contains("j") || input.contains("k") || input.contains("l")
    || input.contains("m") || input.contains("n") || input.contains("o")
    || input.contains("p") || input.contains("q") || input.contains("r")
    || input.contains("s") || input.contains("t") || input.contains("u")
    || input.contains("v") || input.contains("w") || input.contains("x")
    || input.contains("y") || input.contains("z")) {
        return false;
    }
    else {
        return true;
    }
    }
}
