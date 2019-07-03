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
import com.codename1.ui.Form;

import com.codename1.components.ToastBar;
import com.codename1.messaging.Message;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.validation.Constraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.Entite.Animateur;

import com.mycompany.Services.ServiceAnimateur;
import static org.joda.time.format.ISODateTimeFormat.date;

/**
 *
 * @author Shai Almog
 */
public class AjouterAnimateurForm extends SideMenuBaseForm {
    private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};
     public static  Resources resources;
     
    
       Label lnom, lprn, lmail, ltel, lad;
    TextField txtnom, txtprn, txtmail, txttel, txtad;
    Button btnajoutan;
    
    Button btnaff, btnlistan;

    public AjouterAnimateurForm(Resources res) {
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
                                new Label("Ajouter un animateur", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
                        
        add(new Label("Today", "TodayTitle"));
        
        //////////////////////////////////////////////////////////////////////////////////////////////////////////
        btnajoutan = new Button("Ajouter");

        lnom = new Label("Nom :");
        lprn = new Label("Prenom :");
        lmail = new Label("Mail :");
        lad = new Label("Adresse :");
        ltel = new Label("Numero telephone :");

        txtnom = new TextField();
        txtprn = new TextField();
        txtmail = new TextField();
        txttel = new TextField();
        txtad = new TextField();
        
        txtnom.setUIID("edited textfield");
        txtprn.setUIID("edited textfield");
        txtmail.setUIID("edited textfield");
        txttel.setUIID("edited textfield");
        txtad.setUIID("edited textfield");

        Picker adresse = new Picker();
        adresse.setUIID("edited textfield");
        adresse.setType(Display.PICKER_TYPE_STRINGS);
        adresse.setStrings("Ariana", "Béja", "Ben Arous", "Bizerte", "Gabes", "Gafsa", "Jendouba", "Kairouan", "Kasserine", "Kebili", "La Manouba", "Le Kef", "Mahdia", "Médenine", "Monastir", "Nabeul", "Sfax", "Sidi Bouzid", "Siliana", "Sousse", "Tataouine", "Tozeur", "Tunis", "Zaghouan,"
        );


        btnajoutan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                    

                ServiceAnimateur sa = new ServiceAnimateur();
                Animateur a = new Animateur(0, txtnom.getText(), txtprn.getText(), txtmail.getText(), Integer.parseInt(txttel.getText()), adresse.getText());
                sa.ajoutanimateur(a);
                 showToast("Animateur ajouté avec success");
                AffichageAnimateurForm x = new AffichageAnimateurForm(res);
                x.show();
                
   
        
          Message m = new Message(
                             "Votre compte a bien été créé. "
                             );
                    Display.getInstance().sendMessage(new String[]{txtmail.getText()}, "Invitation", m);
                    
                  if ( Dialog.show("Creation","Votre compte a bien été créé.","ok",null)){

            }
            }
        });
        
        
        
          Validator validator = new Validator();
        validator.addSubmitButtons(btnajoutan);
        validator.addConstraint(txtnom, new Constraint() {
            @Override
            public boolean isValid(Object value) {
                return !String.valueOf(value).equals("");
            }

            @Override
            public String getDefaultFailMessage() {
                return "Champ vide";
            }
        });
        validator.addConstraint(txtprn, new Constraint() {
            @Override
            public boolean isValid(Object value) {
                return !String.valueOf(value).equals("");
            }

            @Override
            public String getDefaultFailMessage() {
                return "Champ vide";
            }
        });

        validator.addConstraint(txtmail, new Constraint() {
            @Override
            public boolean isValid(Object value) {
                return !String.valueOf(value).equals("");
            }

            @Override
            public String getDefaultFailMessage() {
                return "Champ vide";
            }
        });

   
        validator.addConstraint(txttel, new Constraint() {
            @Override
            public boolean isValid(Object value) {
                return !String.valueOf(value).equals("");
            }

            @Override
            public String getDefaultFailMessage() {
                return "Champ vide";
            }

        });

        add(lnom).add(txtnom);
        add(lprn).add(txtprn);
        add(lmail).add(txtmail);
       add(ltel).add(txttel);
        add(lad);
        add(adresse);
        
        add(btnajoutan);

     

        getToolbar().addCommandToRightBar("Retour", null, (ev) -> {
            ActiviteForm h = new ActiviteForm(res);
            h.show();

        });
        /////////////////////////////////////////////////////////////////////////////////////////////////////////
        
       
        setupSideMenu(res);
    }
    
     private void showToast(String text) {
        Image errorImage = FontImage.createMaterial(FontImage.MATERIAL_ERROR, UIManager.getInstance().getComponentStyle("Title"), 4);
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage(text);
        status.setIcon(errorImage);
        status.setExpires(2000);
        status.show();
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
