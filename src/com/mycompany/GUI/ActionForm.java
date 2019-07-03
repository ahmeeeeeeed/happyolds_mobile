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
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.table.TableModel;
import com.codename1.ui.util.Resources;
import com.mycompany.Entite.Evenement;
import com.mycompany.Entity.Resident;
import com.mycompany.Services.ServicePrestation;
import java.util.ArrayList;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.validation.Constraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.Entite.ActionBenevole;
import com.mycompany.Services.ServiceAction;

import java.util.Date;

/**
 *
 * @author Shai Almog
 */
public class ActionForm extends SideMenuBaseForm {
    private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};
    
     public static  Resources resources;
     
    
     TextField description;
    Button btnajout, btnaff;
    Button btnmodifier;
    Label de;
    Label dad;
    Label daf;
    Label Categorie;
    Picker datedebut;
    Picker datefin;
    ComboBox type;

    public ActionForm(Resources res) {
      
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
                                new Label("Action bénévole", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
                        
        add(new Label("Today", "TodayTitle"));
        
        
        
        //////////////////////////////////////////////////////////////////////////////////////////////////////////
           type = new ComboBox("Prestation de santé", "visites en établissement", "Enseignement-Formation", "Animation culturelle", "Services directes aux résidents");
        Categorie = new Label("Categorie");
        datedebut = new Picker();
        datedebut.setUIID("edited textfield");
        datedebut.setType(Display.PICKER_TYPE_DATE);
        datedebut.setDate(new Date());
        datefin = new Picker();
        datefin.setType(Display.PICKER_TYPE_DATE);
        datefin.setDate(new Date());
        datefin.setUIID("edited textfield");

        de = new Label("Description");
        dad = new Label("Date debut :");
        daf = new Label("Date fin :");

        description = new TextField("", "description");
        description.setUIID("edited textfield");
        btnajout = new Button("ajouter");
        btnaff = new Button("Affichage");
        add(dad).add(datedebut);

        add(daf).add(datefin);
        add(de);
        add(description);

        add(Categorie);
        add(type);

        add(btnajout);
        add(btnaff);
        btnajout.addActionListener((ActionEvent e) -> {

            ServiceAction ser = new ServiceAction();
            ActionBenevole t = new ActionBenevole(0, datedebut.getDate(), datefin.getDate(), description.getText());
            ser.ajoutAction(t);
           
       //     Affichage a = new Affichage();
         //   a.getF().show();

        });
        btnaff.addActionListener((e) -> {
            AffichageActionForm a = new AffichageActionForm(res);
            a.show();
            
        });
         Validator validator = new Validator();
            validator.addSubmitButtons(btnajout);
            validator.addConstraint(description, new Constraint() {
                @Override
                public boolean isValid(Object value) {
                    return !String.valueOf(value).equals("");
                }

                @Override
                public String getDefaultFailMessage() {
                    return "Champ vide";
                }
            });
        //////////////////////////////////////////////////////////////////////////////////////////////////////////
        
       
        setupSideMenu(res);
    }
    
    public ActionForm(ActionBenevole a,Resources res){
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
                                new Label("Action bénévole", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
                        
        add(new Label("Today", "TodayTitle"));
        
        
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
         type = new ComboBox("Prestation de santé", "visites en établissement", "Enseignement-Formation", "Animation culturelle", "Services directes aux résidents");
        Categorie = new Label("Categorie");
        datedebut = new Picker();
        datedebut.setUIID("edited textfield");
        

        datedebut.setType(Display.PICKER_TYPE_DATE);

        datedebut.setDate(a.getDateDAction());
        datefin = new Picker();
        datefin.setType(Display.PICKER_TYPE_DATE);

        datefin.setDate(a.getDateFAction());

        de = new Label("Description");
        dad = new Label("Date debut :");
        daf = new Label("Date fin :");
        datefin.setUIID("edited textfield");

        description = new TextField("", "description");
        description.setText(a.getDescription());
        description.setUIID("edited textfield");
        btnmodifier = new Button("Modifier");
        add(dad).add(datedebut);

        add(daf).add(datefin);
        add(de);
        add(description);

        add(Categorie);
        add(type);

        add(btnmodifier);
       
        btnmodifier.addActionListener((a1) -> {
            ServiceAction ser = new ServiceAction();
            a.setDateDAction(datedebut.getDate());
            a.setDateFAction(datefin.getDate());
            a.setDescription(description.getText());
            ser.modifier(a);
            AffichageActionForm a2 = new AffichageActionForm(res);
            a2.show();

        });
        //////////////////////////////////////////////////////////////////////////////////////////////////////////
        
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
