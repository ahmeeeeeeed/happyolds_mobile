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

/**
 *
 * @author Shai Almog
 */
public class AffichageMaisonForm extends SideMenuBaseForm {
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

    public AffichageMaisonForm(Resources res) {
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
        talzheimer = new TextField("", "Alzheimer");
        talzheimer = new TextField("", "Alzheimer");
        tmaladie = new TextField("", "Maladie");
        tresponsable = new TextField("", "Responsable");
        ttelephone = new TextField("", "Téléphone");
        tsexe = new TextField("", "Sexe");
        Button btnajout = new Button("Inscrivez Vous");

        ServiceMaison serviceTask = new ServiceMaison();
        ArrayList<Maison> listmai = serviceTask.getList2();

        for (Maison m : listmai) {
            Label Nom = new Label("Nom:          ");
            Label Adresse = new Label("Adresse:");
//            Adresse.getAllStyles().setMargin(10, 10, 150, 10);

            Nom.getAllStyles().setFgColor(444);
            Adresse.getAllStyles().setFgColor(444);

            Container c2 = new Container(new BorderLayout());
            c2.add(BorderLayout.WEST, Nom);
            c2.add(BorderLayout.CENTER, Adresse);

            Label lb = new Label("", "");
            lb.setText(m.getNomMaison());

            Label lb2 = new Label("", "");
            lb2.setText(m.getAdresseMaison());
            lb2.getAllStyles().setMargin(10, 10, 200, 10);
            Label lb3 = new Label("", "");
            lb3.setText(m.getMailMaison());
            Label lb4 = new Label("", "");
            lb4.setText(m.getTelephoneMaison());
            Label lb5 = new Label("", "");
//            lb5.setText(m.getNbrPersonne());
            //lb2.getAllStyles().setFgColor(444);
            Container c = new Container(new BorderLayout());
            Container c3 = new Container(new BorderLayout());

            c.add(BorderLayout.WEST, lb);
            c.add(BorderLayout.CENTER, lb2);

            add(c2);
           add(c);
            info = new Button("more info");
            Button inscrire = new Button("Inscrire");

            info.addActionListener(d -> {
                Dialog.show("Maison " + m.getNomMaison(), "\n" + "Adresse: " + m.getAdresseMaison() + "\n" + "Telephone: " + m.getTelephoneMaison() + "\n" + "Mail: " + m.getMailMaison() + "\n" + "Nombre de place: " + m.getNbrPersonne(), "OK", null);

            });

            add(inscrire);

            add(info);
            inscrire.addActionListener(d -> {
                int f = m.getIdMaison();
                AjouterResidentForm a = new AjouterResidentForm(res,m);
//                System.out.println(f);

                a.show();

//        F2.add(tnom);
//        F2.add(tprenom);
//        F2.add(tage);
//        F2.add(tsexe);
//        F2.add(talzheimer);
//        F2.add(tmaladie);
//        F2.add(tresponsable);
//        F2.add(ttelephone);
//        F2.add(btnajout);
            });
        }

        getToolbar().addCommandToRightBar("back", null, (ev) -> {
            InscriptionForm h = new InscriptionForm(res);
            h.show();
        });
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
        
        
       
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
