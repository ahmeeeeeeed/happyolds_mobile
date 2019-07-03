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
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableModel;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.Validator;
import com.mycompany.Entite.Evenement;
import com.mycompany.Services.ServiceEvent;
import java.util.Date;
import com.codename1.ui.validation.Constraint;
import com.codename1.ui.validation.Validator;

/**
 *
 * @author Shai Almog
 */
public class EventForm extends SideMenuBaseForm {
    private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};
     public static  Resources resources;
     
     Picker  tdateDebut;
     
    Picker tdateFin;
    TextField theure;
    TextField tnom;
    TextField tadresse;
    TextField tdescription;
    Button btnajout,btnaff;
    Button btnmodifEvt;
    Label ldb;
    Label ldf;

    public EventForm(Resources res) {
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
                                new Label("Event", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
                        
        add(new Label("Today", "TodayTitle"));
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        tdateDebut = new Picker();tdateDebut.setUIID("edited textfield");
        tdateDebut.setType(Display.PICKER_TYPE_DATE);
        tdateDebut.setDate(new Date());
        
        tdateFin = new Picker();tdateFin.setUIID("edited textfield");
        tdateFin.setType(Display.PICKER_TYPE_DATE);
        tdateFin.setDate(new Date());
        
        theure = new TextField("","heure evenement");theure.setUIID("edited textfield");
        tnom = new TextField("","nom evenement");tnom.setUIID("edited textfield");
        tadresse = new TextField("","adresse evenement");tadresse.setUIID("edited textfield");
        tdescription = new TextField("","description evenement");tdescription.setUIID("edited textfield");
        
        ldb = new Label("Date debut:");
        ldf = new Label("Date fin:");
        
      
        btnajout = new Button("ajouter");
        btnaff=new Button("Affichage");
        add(ldb).add(tdateDebut);
        add(ldf).add(tdateFin);
        add(theure);
        add(tnom);
        add(tadresse);
        add(tdescription);
          
        add(btnajout);
        add(btnaff);
        btnajout.addActionListener((ActionEvent e) -> {
            ServiceEvent ser = new ServiceEvent();
            Evenement event = new Evenement(0,tdateDebut.getDate()
, tdateFin.getDate()
 , theure.getText(), tnom.getText(),tadresse.getText(),tdescription.getText());
            ser.ajoutEvent(event);
           
            AffichageEventForm a=new AffichageEventForm(res);
        a.show();

        });
        btnaff.addActionListener((e)->{
         AffichageEventForm a=new AffichageEventForm(res);
        a.show();

        });
         Validator validator = new Validator();
            validator.addSubmitButtons(btnajout);
            validator.addConstraint(tdescription, new Constraint() {
                @Override
                public boolean isValid(Object value) {
                    return !String.valueOf(value).equals("");
                }

                @Override
                public String getDefaultFailMessage() {
                    return "Champ vide";
                }
            });
           
            validator.addSubmitButtons(btnajout);
            validator.addConstraint(tadresse, new Constraint() {
                @Override
                public boolean isValid(Object value) {
                    return !String.valueOf(value).equals("");
                }

                @Override
                public String getDefaultFailMessage() {
                    return "Champ vide";
                }
            });
             validator.addSubmitButtons(btnajout);
            validator.addConstraint(tnom, new Constraint() {
                @Override
                public boolean isValid(Object value) {
                    return !String.valueOf(value).equals("");
                }

                @Override
                public String getDefaultFailMessage() {
                    return "Champ vide";
                }
            });
            validator.addSubmitButtons(btnajout);
            validator.addConstraint(theure, new Constraint() {
                @Override
                public boolean isValid(Object value) {
                    return !String.valueOf(value).equals("");
                }

                @Override
                public String getDefaultFailMessage() {
                    return "Champ vide";
                }
            });
            
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        
        
       
        setupSideMenu(res);
    }
    
    public EventForm(Evenement e,Resources res){
            
        
         super(BoxLayout.y());
        
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("user-picture.jpg");        
        Image tintedImage = Image.createImage(profilePic.getWidth(), profilePic.getHeight());
        Graphics g = tintedImage.getGraphics();
        g.drawImage(profilePic, 0, 0);
        g.drawImage(res.getImage("gradient-overlay.png"), 0, 0, profilePic.getWidth(), profilePic.getHeight());
        
        tb.getUnselectedStyle().setBgImage(tintedImage);
        
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(ee -> getToolbar().openSideMenu());

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
                                new Label("Event", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
                        
        add(new Label("Today", "TodayTitle"));
        
        /////////////////////////////////////////////////////////////////////////////////////////////////////
        
        
        
        tdateDebut = new Picker();tdateDebut.setUIID("edited textfield");
        tdateDebut.setType(Display.PICKER_TYPE_DATE);
        tdateDebut.setDate(e.getDateDEvenement());
        
        tdateFin = new Picker();tdateFin.setUIID("edited textfield");
        tdateFin.setType(Display.PICKER_TYPE_DATE);
        tdateFin.setDate(e.getDateFEvenement());
        System.out.println("evenement now !!!" + e.toString());
         theure = new TextField("","heure evenement");theure.setUIID("edited textfield");
        tnom = new TextField("","nom evenement");tnom.setUIID("edited textfield");
        tadresse = new TextField("","adresse evenement");tadresse.setUIID("edited textfield");
        tdescription = new TextField("","description evenement");tdescription.setUIID("edited textfield");
        theure.setText(e.getHeureEvenement());
        tnom.setText(e.getNomEvenement());
        tadresse.setText(e.getAdresseEvenement());
        tdescription.setText(e.getDescriptionEvenement());
        
        ldb = new Label("Date debut:");
        ldf = new Label("Date fin:");
        
      
        btnmodifEvt = new Button("Modifier");
        
        add(ldb).add(tdateDebut);
        add(ldf).add(tdateFin);
        add(theure);
        add(tnom);
        add(tadresse);
        add(tdescription);
         
        add(btnmodifEvt);
        btnmodifEvt.addActionListener((e1)->{
       ServiceEvent ser = new ServiceEvent();
       
            e.setAdresseEvenement(tadresse.getText());
            e.setDescriptionEvenement(tdescription.getText());
            e.setNomEvenement( tnom.getText());
            e.setHeureEvenement(theure.getText());
            e.setDateDEvenement(tdateDebut.getDate());
            e.setDateFEvenement(tdateFin.getDate());
            ser.modifierEvent(e);
           
           AffichageEventForm a=new AffichageEventForm(res);
        a.show();
        });
       
     
/////////////////////////////////////////////////////////////////////////////////////////////////////
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
