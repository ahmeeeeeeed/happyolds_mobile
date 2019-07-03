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

import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.Entity.Resident;
import com.mycompany.Services.ServiceResident;
import java.util.ArrayList;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.Entity.Maison;
import com.mycompany.Services.ServiceMaison;
import java.util.ArrayList;

import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.validation.Constraint;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
//import com.mycompany.Entite.Maison;
//import com.mycompany.Entite.Resident;
//import com.mycompany.Service.ServiceMaison;
//import com.mycompany.Service.ServiceResident;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Shai Almog
 */
public class AjouterResidentForm extends SideMenuBaseForm {
    private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};
     public static  Resources resources;
     
    
    Label lb;
    Button info;
    TextField tnom, tprenom, tsexe, talzheimer, tmaladie, tresponsable, ttelephone, tmaison;
    TextField tage;
    Maison m;
    
    //Label lb;
    
    Button btnajout,btnaff,btnlistan;

    public AjouterResidentForm(Resources res,Maison m) {
       super(BoxLayout.y());
        
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
         
        Image profilePic = res.getImage("elder-couple.png");
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
                                new Label("Inscription", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
                        
        add(new Label("Today", "TodayTitle"));
        
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      lb = new Label();
 
        
        tnom = new TextField("", "Nom ");
        tprenom = new TextField("", "Prénom ");
        tage = new TextField("", "Age ");
        tsexe = new TextField("", "Sexe résident");
        tresponsable = new TextField("", "Responsable");
        ttelephone = new TextField("", "Téléphone");
        
        tnom.setUIID("edited textfield");;
        tprenom.setUIID("edited textfield");
        tage.setUIID("edited textfield");
        tsexe.setUIID("edited textfield");
        tresponsable.setUIID("edited textfield");
        ttelephone.setUIID("edited textfield");
        
        ServiceMaison sma = new ServiceMaison();
        List<Maison> listM = new ArrayList<>();
      //  AffichageMaison a= new AffichageMaison();

        listM = sma.getList2();
        btnajout = new Button("Inscrivez Vous");
       
    
        add(tnom);
        add(tprenom);
        add(tage);
        add(tsexe);
        add(tresponsable);
        add(ttelephone);
        add(btnajout);

        
//        v.addSubmitButtons(btnajout);

  ///////////////////////////////validator ////////////////////////////
         


      
        
        
        btnajout.addActionListener((e) -> {

            ServiceResident ser = new ServiceResident();
            
            Resident r=new Resident();
            r.setNom_resident(tnom.getText());
            r.setPrenom_resident(tprenom.getText());
            r.setAge_resident(Integer.parseInt(tage.getText()));
            r.setResponsable(tresponsable.getText());
            r.setTelephoneResponsable(ttelephone.getText());
            r.setSexe_resident(tsexe.getText());
            r.setMaison(m);
            
            if (r.getMaison().getNbrPersonne()>0 && isInputValid())
            {
                 
            ser.ajoutRes(r);
           
                               // ser.Valider(r.getMaison().getIdMaison());
              showToast("Votre résident est inscrit avec succés");
            AffichageResidentForm h=new AffichageResidentForm(res);
        h.show();

} 

             else  {
                 
                 
                           Dialog.show("Erreur !", "Cette maison n'a pas des places disponibles", "OK",null);

            }
                    
        });
        
          getToolbar().addCommandToRightBar("back", null, (ev) -> {
            InscriptionForm h = new InscriptionForm(res);
            h.showBack();
        });
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
        
        
       
        setupSideMenu(res);
    }
     private boolean isInputValid() 
    {
        String errorMessage = "";

        if (tnom.getText() == null || tnom.getText() == null || tage.getText() == null || tsexe.getText() == null || tresponsable.getText() == null || ttelephone.getText() == null) 
        {
            errorMessage += "Remplissez tous les champs !\n"; 
        }
//        if(ttelephone.getText().length()!=8)
//        {
//            
//        }
        if (errorMessage.length() == 0) 
        {
            return true;
        }
         else 
        {
            Dialog.show("Remplissez tous les champs", errorMessage, "OK", null);
            return false;
        }
    }
      private void showToast(String text) {
 Image errorImage = FontImage.createMaterial(FontImage.MATERIAL_ERROR, UIManager.getInstance().getComponentStyle("Title"), 4);
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage(text);
        status.setIcon(errorImage);
        status.setExpires(2000);
        status.show();    }

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
