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
import com.mycompany.Entite.Evenement;

import com.mycompany.Services.ServiceEvent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.mycompany.Entite.ActionBenevole;
import com.mycompany.Services.ServiceAction;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Shai Almog
 */
public class AffichageActionForm extends SideMenuBaseForm {
    private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};
     public static  Resources resources;
    
    
     Label lb;
    Button delete, modifier;
    
    // Label lb;
    Button btnDelete,btnModif;

    public AffichageActionForm(Resources res) {
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
                                new Label("Liste des actions", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
                        
        add(new Label("Today", "TodayTitle"));
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        
       lb = new Label("");
        add(lb);
        ServiceAction serviceAction = new ServiceAction();
        ArrayList<ActionBenevole> listevent = serviceAction.getList2();

        InfiniteContainer ic = new InfiniteContainer() {
            @Override
            public Component[] fetchComponents(int index, int amount) {

                amount = listevent.size() - index;

                Component[] cmp = new Component[amount];

                for (int iter = 0; iter < amount; iter++) {

                    Container c = new Container(BoxLayout.y());
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    int id = listevent.get(iter).getIdAction();
                    String db =dateFormat.format(listevent.get(iter).getDateDAction()); 
                    String df =dateFormat.format(listevent.get(iter).getDateFAction()); 

                    String de = String.valueOf(listevent.get(iter).getDescription());
                    String cat= String.valueOf(listevent.get(iter).getType());

                 

//                    Label i = new Label();
//                    
//                    i.setHeight(500);
//                    c.add(i);

                    c.add("Date debut: " + db);
                    c.add("Date fin: " + df);

                    c.add("Description: " + de);
                   // c.add("Categorie:" + cat );

                    delete = new Button("Supprimer");
                    delete.addActionListener((ActionEvent e) -> {
                        ServiceAction ser = new ServiceAction();

                        ser.supprimerAction(id);
                        ActionForm aff = new ActionForm(res);
                        aff.show();

                        //    this.getF().show(); 
                    });
                    c.add(delete);
                    modifier = new Button("Modifier");
                    final  ActionBenevole a1 = listevent.get(iter);
                    modifier.addActionListener((e)->{
                    
                     ActionForm a=new ActionForm(a1,res);
                     a.show();
                   
                    });
                    c.add(modifier);


                    cmp[iter] = c;

                }
                return cmp;
            }
        };

        add(ic);

        getToolbar().addCommandToRightBar("back", null, (ev) -> {
            ActionForm h = new ActionForm(res);
            h.show();
        });
        ////////////////////////////////////////////////////////////////////////////////////////////
        
        
        
       
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
