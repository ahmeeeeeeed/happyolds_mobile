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
public class FicheMedicaleForm extends SideMenuBaseForm {
     Resources resources;
     Resident resident;
    public FicheMedicaleForm(Resources res,String idResident,Resident resident) {
        super(BoxLayout.y());
         this.resources=res;
         this.resident =resident;
        
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
                                new Label("Fiches Médicales", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
                        
        //add(new Label("Liste des résidents :", "TodayTitle"));
        
        
        
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fab.getStyle().setBgColor(0x4dc2ff); 
        fab.bindFabToContainer(this.getContentPane());
        
        fab.addActionListener( e ->{
            ajouterFiche( fab,idResident);
        });
        
  
         ServiceFicheMedicale sp=new ServiceFicheMedicale();
        ArrayList<Fiche_medicale> listfiches = sp.getListFicheByResident(idResident);
        //System.out.println("liste fiche : "+listfiches.toString());

       
   InfiniteContainer ic = new InfiniteContainer() {
        @Override
        public Component[] fetchComponents(int index, int amount) {

           amount = listfiches.size() - index ;  
            
            Component[] cmp = new Component[amount];
          
            
            for(int iter = 0 ; iter < amount ; iter++) {
                
               Container c  = new Container(BoxLayout.y());
               
               Container c2  = new Container(BoxLayout.x());//pour labelle et bouton modifier
               
               
                Button modifyButton = new Button();
                Fiche_medicale fiche = listfiches.get(iter);
                modifyButton.addActionListener(l->{
                    modifierFiche(modifyButton,idResident,fiche);
                });
                FontImage.setMaterialIcon(modifyButton, FontImage.MATERIAL_SETTINGS);
                //c.add(modifyButton);
               
               String nomresident = "Nom : "+resident.getNom_resident() ;
               String prenomresident = "Prenom : "+resident.getPrenom_resident() ;

               String medicaments = "médicament : " + String.valueOf(listfiches.get(iter).getMedicaments());
               String Remarques =  "Remarque : "+String.valueOf(listfiches.get(iter).getRemarques_fiche());
               String niveauSec = "niveauSec : "+ String.valueOf(listfiches.get(iter).getNiveauSuc()+"g");
               String niveauTemp = "niveauTemp : "+ String.valueOf(listfiches.get(iter).getNiveauTemp()+"°");
               String niveauTension = "niveauTension : "+ String.valueOf(listfiches.get(iter).getNiveauTension()+"cmHg");
               String poidsResident =  "poidsResident : "+String.valueOf(listfiches.get(iter).getPoids_resident()+"kg");
               String tailleResident = "tailleResident : "+ String.valueOf(listfiches.get(iter).getTaille_resident()+"cm");
               String groupeSanguin = "groupeSanguin : "+ String.valueOf(listfiches.get(iter).getGroupeSanguin());
               
            /*   String resident =  String.valueOf(listfiches.get(iter).getNom_resident() +" "+
                                                 listfiches.get(iter).getPrenom_resident());
         addButtonBottom(null, resident, 0x4dc2ff, true);*/
            c2.add("Fiche numéro "+String.valueOf(listfiches.get(iter).getId_fich())+":").add(modifyButton);
            c.add(c2);
          //  c.add("Fiche numéro "+String.valueOf(listfiches.get(iter).getId_fich())+":").
            c.add(ComponentGroup.enclose(
                    new Label(nomresident),
                    new Label(prenomresident),
                    new Label(niveauSec),
                    new Label(niveauTemp),
                    new Label(niveauTension),
                    new Label(poidsResident),
                    new Label(tailleResident),
                    new Label(groupeSanguin),
                    new Label(medicaments),
                    new Label(Remarques)
                    ));
          
       /*   c.add("-------------------------").
        add(ComponentGroup.enclose(new Label(medicaments), 
                new Label(Remarques), 
                new Label(niveauSec),
                new Label(niveauTemp),
                  new Label(niveauSec),
                  new Label(niveauTension),
                  new Label(poidsResident),
                  new Label(tailleResident),
                   new Label(groupeSanguin)).
        add("One Label").
        add(ComponentGroup.enclose(new Label("GroupElementOnly UIID"))).
        add("Three Buttons").
        add(ComponentGroup.enclose(new Button("ButtonGroupFirst UIID"), new Button("ButtonGroup UIID"), new Button("ButtonGroupLast UIID"))).
        add("One Button").
        add(ComponentGroup.enclose(new Button("ButtonGroupOnly UIID")));*/
            /*
            add(medicaments).
                    add(Remarques).
                    add(niveauSec).
                    add(niveauTemp).
                    add(niveauTension).
                    add(poidsResident).
                    add(tailleResident).
                    add(groupeSanguin);*/
                    
                
               cmp[iter] = c;          
            }
            
            
            return cmp;
        }
};
  
           add(ic);
        setupSideMenu(res);
    }
    
    private void ajouterFiche(FloatingActionButton fab,String idResident){
        ServiceFicheMedicale sp=new ServiceFicheMedicale();
        Dialog d = new Dialog("nouvelle fiche");
        
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

     
 
        d.setLayout(BoxLayout.y());

       d.add(nom).add(prenom).add(nivTemp).add(c1sliderTemp).
                        add(nivSuc).add(c2sliderSuc).
                        add(nivtension).add(c3sliderTension).
                        add(groupS).add(combo).
                        add(poids).add(c4sliderPoids).
                        add(taille).add(c5sliderTaille).
                         add( medicaments).
                         add( Remarques).
                         add(ajouter);

        
      /*  Fiche_medicale fiche = new Fiche_medicale();
        fiche.setGroupeSanguin(combo.getSelectedItem());
        fiche.setMedicaments(medicaments.getText());
        fiche.setNiveauSuc((float) 0.9);
        fiche.setNiveauTemp(37);
        fiche.setNiveauTension((float) 12.5);
        fiche.setPoids_resident(75);
        fiche.setTaille_resident(170);
        fiche.setRemarques_fiche(Remarques.getText());*/
     //   System.out.println("before : "+fiche);
   
        
                ajouter.addActionListener(l ->{
                    if(medicaments.getText().equals("") || Remarques.getText().equals(""))
                        Dialog.show("attention !!", "Certains champs ne sont pas valides", "ok",null);
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
                

          /*  InteractionDialog dlg = new InteractionDialog("Hello");
dlg.setLayout(new BorderLayout());
dlg.add(BorderLayout.CENTER, new Label("Hello Dialog"));
Button close = new Button("Close");
close.addActionListener((ee) -> dlg.dispose());
dlg.addComponent(BorderLayout.SOUTH, close);
Dimension pre = dlg.getContentPane().getPreferredSize();
dlg.show(0, 0, Display.getInstance().getDisplayWidth() - (pre.getWidth() + pre.getWidth() / 6), 0);
                
*/
          
          d.showPopupDialog(fab);
    }
    
   private void modifierFiche(Button modifyButton,String idResident,Fiche_medicale f){
       ServiceFicheMedicale sp=new ServiceFicheMedicale();
       
        Dialog d = new Dialog("Modifier fiche numéro "+idResident);
           TextField medicaments = new TextField(f.getMedicaments(), "medicaments", 20, TextArea.ANY);
        TextField Remarques = new TextField(f.getRemarques_fiche(), "Remarques", 20, TextArea.ANY);
        Remarques.setUIID("edited textfield");
        medicaments.setUIID("edited textfield");
         Slider sliderTemp = new Slider();
               sliderTemp.setMinValue(360);
               sliderTemp.setMaxValue(410);
               sliderTemp.setIncrements(1);
               sliderTemp.setProgress((int) (f.getNiveauTemp()*10));
               sliderTemp.setEnabled(true);
               sliderTemp.setEditable(true);
              // sliderTemp.setUIID("slider");
               
          Slider sliderSuc = new Slider();
               sliderSuc.setMinValue( 700);
               sliderSuc.setMaxValue( 1400);
               sliderSuc.setIncrements( 100);
               sliderSuc.setProgress((int) (f.getNiveauSuc()*1000));
               sliderSuc.setEnabled(true);
               sliderSuc.setEditable(true);
             //  slider.setUIID("slider");
          Slider sliderTension = new Slider();
               sliderTension.setMinValue( 108);
               sliderTension.setMaxValue(250);
               sliderTension.setIncrements( 01);
               sliderTension.setProgress((int) (f.getNiveauTension()*10));
               sliderTension.setEnabled(true);
               sliderTension.setEditable(true);
               
          Slider sliderPoids = new Slider();
               sliderPoids.setMinValue(600);
               sliderPoids.setMaxValue(1200);
               sliderPoids.setIncrements(1);
               sliderPoids.setProgress((int) (f.getPoids_resident()*10));
               sliderPoids.setEnabled(true);
               sliderPoids.setEditable(true);
               
          Slider sliderTaille = new Slider();
               sliderTaille.setMinValue(1500);
               sliderTaille.setMaxValue(1900);
               sliderTaille.setIncrements(1);
               sliderTaille.setProgress((int) (f.getTaille_resident()*10));
               sliderTaille.setEnabled(true);
               sliderTaille.setEditable(true);
                     
         Label nom = new Label("Nom : "+resident.getNom_resident());
            Label prenom = new Label("Prénom : "+resident.getPrenom_resident());
            Label nivTemp = new Label("*Niveau de temperature : "+f.getNiveauTemp()+"°");
            Label nivSuc = new Label("*Niveau de sucre : "+f.getNiveauSuc()+"g");
            Label nivtension = new Label("*Niveau de tension : "+f.getNiveauTension()+"cmHg");
            Label groupS = new Label("*Groupe sanguin : "+f.getGroupeSanguin());
            Label poids = new Label("*Poids : "+f.getPoids_resident()+"kg");
            Label taille = new Label("*Taille : "+f.getTaille_resident()+"cm");
            
            sliderTemp.addActionListener( ee->{
                   nivTemp.setText("*Niveau de temperature : "+(float) sliderTemp.getProgress()/10+"°");
               });
             sliderSuc.addActionListener( ee->{
                   nivSuc.setText("*Niveau de sucre : "+(float) sliderSuc.getProgress()/1000+"g");
               });
            sliderTension.addActionListener( ee->{
                   nivtension.setText("*Niveau de tension : "+(float)sliderTension.getProgress()/10+"cmHg");
               });
             sliderPoids.addActionListener( ee->{
                   poids.setText("*Poids : "+(float)sliderPoids.getProgress()/10+"kg");
               });
              sliderTaille.addActionListener( ee->{
                   taille.setText("*Taille : "+(float)sliderTaille.getProgress()/10+"cm");
               });
            Button modifier = new Button("Modifier");
            Button annuler = new Button("Annuler");
            annuler.addActionListener((evt) -> {
                d.dispose();
            });
        
      //  d.setLayout(BoxLayout.y());
      
      Container c1sliderTemp = BorderLayout.west(new Label("36°")).add(BorderLayout.EAST, new Label("41°")).add(BorderLayout.CENTER, sliderTemp);
      Container c2sliderSuc = BorderLayout.west(new Label("0.7 g")).add(BorderLayout.EAST, new Label("1.4 g")).add(BorderLayout.CENTER, sliderSuc);
      Container c3sliderTension = BorderLayout.west(new Label("10.8 cmHg")).add(BorderLayout.EAST, new Label("25 cmHg")).add(BorderLayout.CENTER, sliderTension);
      Container c4sliderPoids = BorderLayout.west(new Label("60 kg")).add(BorderLayout.EAST, new Label("120 kg")).add(BorderLayout.CENTER, sliderPoids);
      Container c5sliderTaille = BorderLayout.west(new Label("150 cm")).add(BorderLayout.EAST, new Label("190 cm")).add(BorderLayout.CENTER, sliderTaille);

     
 
        d.setLayout(BoxLayout.y());
        Container cont = new Container(BoxLayout.x());
        cont.add(modifier).add(annuler);
 
       d.add(nom).add(prenom).add(nivTemp).add(c1sliderTemp).
                        add(nivSuc).add(c2sliderSuc).
                        add(nivtension).add(c3sliderTension).
                        add(groupS).
                        add(poids).add(c4sliderPoids).
                        add(taille).add(c5sliderTaille).
                        add(new Label("Médicaments :")).
                         add( medicaments).
                         add(new Label("Remarques :")).
                         add( Remarques).
                         add(cont);

        
        
     //   System.out.println("before : "+fiche);
        
                modifier.addActionListener(l ->{
                    if(medicaments.getText().equals("") || Remarques.getText().equals(""))
                        Dialog.show("attention !!", "Certains champs ne sont pas valides", "ok",null);
                  else{
                    new Loader(this.resources);
                   // d.add(new InfiniteProgress());
                       Fiche_medicale fiche = new Fiche_medicale();
                        fiche.setId_fich(f.getId_fich());
                        fiche.setMedicaments(medicaments.getText());
                        fiche.setNiveauSuc((float) sliderSuc.getProgress()/1000);
                        fiche.setNiveauTemp((float) sliderTemp.getProgress()/10);
                        fiche.setNiveauTension((float)sliderTension.getProgress()/10);
                        fiche.setPoids_resident((float)sliderPoids.getProgress()/10);
                        fiche.setTaille_resident((float)sliderTaille.getProgress()/10);
                        fiche.setRemarques_fiche(Remarques.getText());
                        
                        sp.modifierFiche(fiche, idResident);
                      //  System.out.println("after : "+fiche);
                      new FicheMedicaleForm(this.resources, idResident,resident).show();
                        //d.dispose();
                    }//else
                });
                

          
         // d.showPopupDialog(modifyButton);
         
          d.show(150, 300, 150, 150);
         
          
          d.setScrollable(true);
          d.setScrollableX(true);
          d.setScrollableY(true);
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
