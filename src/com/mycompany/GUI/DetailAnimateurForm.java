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
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entite.Animateur;


/**
 *
 * @author Shai Almog
 */
public class DetailAnimateurForm extends SideMenuBaseForm {
    private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};
     public static  Resources resources;
   
    
    Button btnaff, btnlistan;

    public DetailAnimateurForm(Animateur a,Resources res) {
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
                                new Label("Detail Animateur", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
                        
        add(new Label("Today", "TodayTitle"));
        
        //////////////////////////////////////////////////////////////////////////////////////////////////////////
       // Form hi1 = new Form(n, new BoxLayout(CENTER).y());

                                Label lb = new Label("Nom :" + a.getNomAnimateur());
                                Label lb1 = new Label("Prenom :" + a.getPrenomAnimateur());
                                Label lb2 = new Label("Mail :" + a.getMailAnimateur());
                                Label lb3 = new Label("Numero telephone :" + a.getTelAnimateur());
                                Label lb4 = new Label("Adresse :" + a.getAdresseAnimateur());
                                Label lb5 = new Label("Etat :" + a.getDispo());
                                Button mdf = new Button("Modifier");

                                mdf.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evt) {
                                        
                                        ModifierAnimateurForm ma = new ModifierAnimateurForm(a,res);
                                        ma.show();
                                        /*

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
                                        });*/

                                    }
                                });

                                EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(getWidth(),getWidth() / 5, 0xffff0000), true);
                        URLImage background = URLImage.createToStorage(placeholder, a.getImage(), "http://localhost/PidevHappyoldsSymfony/web/uploads/post/" + a.getImage());
                        background.fetch();
                                Label i = new Label();
                                i.setIcon(background);
                                i.setHeight(500);

                                add(i);
                                add(lb);
                                add(lb1);
                                add(lb2);
                                add(lb3);
                                add(lb4);
                                add(lb5);
                                add(mdf);

                               // hi1.show();
                                getToolbar().addCommandToRightBar("Retour", null, (ev) -> {
                                    AffichageAnimateurForm h = new AffichageAnimateurForm(res);
                                    h.showBack();
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
