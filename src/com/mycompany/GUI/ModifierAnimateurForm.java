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

import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entite.Animateur;
import com.mycompany.Services.ServiceAnimateur;

/**
 *
 * @author Shai Almog
 */
public class ModifierAnimateurForm extends SideMenuBaseForm {
    private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};
     public static  Resources resources;
     
    Button btnaff, btnlistan;

    public ModifierAnimateurForm(Animateur a,Resources res) {
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
                                new Label("Modifier Animateur", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
                        
        add(new Label("Today", "TodayTitle"));
        
        //////////////////////////////////////////////////////////////////////////////////////////////////////////
     
                                        Label l1 = new Label("Nom :");
                                        Label l2 = new Label("Prenom :");
                                        Label l3 = new Label("Mail :");
                                        Label l4 = new Label("Adresse :");
                                        Label l5 = new Label("Numero telephone :");

                                        TextField txt1 = new TextField(a.getNomAnimateur());
                                        TextField txt2 = new TextField(a.getPrenomAnimateur());
                                        TextField txt3 = new TextField(a.getMailAnimateur());
                                        TextField txt4 = new TextField(a.getAdresseAnimateur());
                                        TextField txt5 = new TextField(Integer.valueOf(a.getTelAnimateur()));
                                        System.out.println("a : "+a.getTelAnimateur());
                                        txt1.setUIID("edited textfield");
                                        txt2.setUIID("edited textfield");
                                        txt3.setUIID("edited textfield");
                                        txt4.setUIID("edited textfield");
                                        txt5.setUIID("edited textfield");
                                        Button update = new Button("Modifier");

                                        update.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent evt) {
                                                ServiceAnimateur s = new ServiceAnimateur();

                                                a.setNomAnimateur(txt1.getText());
                                                a.setPrenomAnimateur(txt2.getText());
                                                a.setMailAnimateur(txt3.getText());
                                                a.setAdresseAnimateur(txt4.getText());
                                                a.setTelAnimateur(Integer.parseInt(txt5.getText()));

                                            //    System.out.println(o);
                                                s.modifanimateur(a);

                                               // hi1.show();

                                            }
                                        });
                                         
                                       add(l1).add(txt1);
                                        add(l2).add(txt2);
                                        add(l3).add(txt3);
                                        add(l4).add(txt4);
                                        //add(l5).add(txt5);
                                      add(update);
                                       

                                      //  upd.show();
                                        getToolbar().addCommandToRightBar("Retour", null, (ev) -> {
                                            DetailAnimateurForm h = new DetailAnimateurForm(a,res);
                                            h.show();
                                        });
        /////////////////////////////////////////////////////////////////////////////////////////////////////////
        
       
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
