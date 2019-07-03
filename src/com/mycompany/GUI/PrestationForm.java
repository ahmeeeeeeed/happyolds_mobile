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
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.mycompany.myapp.*;
import com.mycompany.SideMenu.SideMenuBaseForm;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.XYMultipleSeriesDataset;
import com.codename1.charts.models.XYSeries;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer;
import com.codename1.charts.renderers.XYSeriesRenderer;
import com.codename1.charts.views.CubicLineChart;
import com.codename1.charts.views.PointStyle;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.table.TableModel;
import com.codename1.ui.util.Resources;
import com.mycompany.Entity.Dossier;
import com.mycompany.Entity.Fiche_medicale;
import com.mycompany.Entity.Resident;
import com.mycompany.Services.ServiceDossier;
import com.mycompany.Services.ServiceFicheMedicale;
import com.mycompany.Services.ServicePrestation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Shai Almog
 */
public class PrestationForm extends SideMenuBaseForm {
    private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};
      public static  Resources resources;

    public PrestationForm(Resources res) {
                
         super(BoxLayout.y());
         
         this.resources=res;
        
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        //Image profilePic = res.getImage("user-picture.jpg");    
        Image profilePic = res.getImage("elder-couple.png");
        Image tintedImage = Image.createImage(profilePic.getWidth(), profilePic.getHeight());
        Graphics g = tintedImage.getGraphics();
        g.drawImage(profilePic, 0, 0);
        g.drawImage(res.getImage("gradient-overlay.png"), 0, 0, profilePic.getWidth(), profilePic.getHeight());
        
        tb.getUnselectedStyle().setBgImage(tintedImage);
        
         //tb.addSearchCommand(e -> {});
        
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Button settingsButton = new Button("");
        settingsButton.setUIID("Title");
        FontImage.setMaterialIcon(settingsButton, FontImage.MATERIAL_SETTINGS);
       /* settingsButton.addActionListener((evt) -> {
            //sms 
            Twilio.init("ACcfc24b31c59bdeea6c13e08746710932", "4400020cabfd4fe3f78f1721272564a7");

        Message message = Message
                .creator(new PhoneNumber("+21622148971"), // to
                        new PhoneNumber("+12512415658"), // from
                        "patron!!")
                .create();

        System.out.println(message.getSid());
        //end
        });*/
        Label space = new Label("", "TitlePictureSpace");
        space.setShowEvenIfBlank(true);
         
        Button searchButton = new Button("");
        TextField searchField = new TextField("", "Search",9, TextArea.LEFT); 
         searchField.setUIID("edited textfield");
         FontImage.setMaterialIcon(searchButton, FontImage.MATERIAL_SEARCH);
         searchButton.setUIID("Title");

        Container titleComponent = 
                BorderLayout.north(
                    BorderLayout.west(menuButton).add(BorderLayout.EAST, settingsButton)
                ).
                add(BorderLayout.CENTER, FlowLayout.encloseIn(
                               space
                        )).
                add(BorderLayout.SOUTH, 
                        FlowLayout.encloseIn(
                                //new Label("  Jennifer ", "WelcomeBlue"),
                                new Label("Préstation santé", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
                        
   
        
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        

   
    
    

 
/*searchField.getHintLabel().setUIID("new textfield");
searchField.setUIID("new textfield");
searchField.getAllStyles().setAlignment(Component.LEFT);
 Button searchIcon = new Button("");
      
        FontImage.setMaterialIcon(searchIcon, FontImage.MATERIAL_MENU);*/
        
       // add(searchField);
//getToolbar().setTitleComponent(searchField);


       AutoCompleteTextField ac = new AutoCompleteTextField("Short", "Shock", "Sholder", "Shrek");
ac.setMinimumElementsShownInPopup(5);

//add(ac);
ac.setUIID("edited textfield");
      add(new Label("Liste des résidents :", "TodayTitle"));
     // Container container = new Container(BoxLayout.x());
      
      RadioButton rb1 = new RadioButton("Nom");
        RadioButton rb2 = new RadioButton("Prénom");
        new ButtonGroup(rb1, rb2);
      
     // container.add(searchField).add(searchButton).add(ComponentGroup.encloseHorizontal(rb1, rb2));
      // add(container);
      add(ComponentGroup.encloseHorizontal(searchField,searchButton,rb1, rb2));
        ServicePrestation sp=new ServicePrestation();
        ArrayList<Resident> listresidents = sp.getListResident();
      
      fillListResident(listresidents);
      
      //recherche par nom
      rb1.addActionListener((evt) -> {
          searchField.addActionListener((i1) -> {
            Dialog ip = new InfiniteProgress().showInifiniteBlocking();
           
            for(Component cmp : getContentPane()) {
                 if((cmp instanceof MultiButton)){
                    System.out.println(cmp.getClass());
                //    cmp.setHidden(true);
                  //  cmp.setVisible(false);
                    cmp.remove();
                }
                if(!(cmp instanceof Label)&& !(cmp instanceof ComponentGroup)){
           //         System.out.println(cmp.getClass());
                    cmp.setHidden(true);
                    cmp.setVisible(false);
//                    cmp.remove();
                }
        }
            
            if(searchField.getText().length()<1)
                fillListResident(listresidents);
            else
            {
                ArrayList<Resident> sortedListResident = new ArrayList<>();
                
                for (int j=0; j<listresidents.size(); j++) {
                  if (listresidents.get(j).getNom_resident().contains(searchField.getText()) ) {
                            sortedListResident.add(listresidents.get(j));
                        }

                    }

                     fillListResident(sortedListResident);
            }
            
            
        ip.dispose();
        });    
      });
      
      //recherche par prenom
      rb2.addActionListener((evt) -> {
          searchField.addActionListener((i1) -> {
            Dialog ip = new InfiniteProgress().showInifiniteBlocking();
           
            for(Component cmp : getContentPane()) {
                 if((cmp instanceof MultiButton)){
                    System.out.println(cmp.getClass());
                //    cmp.setHidden(true);
                  //  cmp.setVisible(false);
                    cmp.remove();
                }
                if(!(cmp instanceof Label)&& !(cmp instanceof ComponentGroup)){
           //         System.out.println(cmp.getClass());
                    cmp.setHidden(true);
                    cmp.setVisible(false);
//                    cmp.remove();
                }
        }
            
            if(searchField.getText().length()<1)
                fillListResident(listresidents);
            else
            {
                ArrayList<Resident> sortedListResident = new ArrayList<>();
                
                for (int j=0; j<listresidents.size(); j++) {
                  if ( listresidents.get(j).getPrenom_resident().contains(searchField.getText())) {
                            sortedListResident.add(listresidents.get(j));
                        }

                    }

                     fillListResident(sortedListResident);
            }
            
            
        ip.dispose();
        });   
      });
      
      //  searchField.addDataChangedListener( (i1,l2) ->{
        
       //recherche normale
        searchField.addActionListener((i1) -> {
            Dialog ip = new InfiniteProgress().showInifiniteBlocking();
           
            for(Component cmp : getContentPane()) {
                 if((cmp instanceof MultiButton)){
                    System.out.println(cmp.getClass());
                //    cmp.setHidden(true);
                  //  cmp.setVisible(false);
                    cmp.remove();
                }
                if(!(cmp instanceof Label)&& !(cmp instanceof ComponentGroup)){
           //         System.out.println(cmp.getClass());
                    cmp.setHidden(true);
                    cmp.setVisible(false);
//                    cmp.remove();
                }
        }
            
            if(searchField.getText().length()<1)
                fillListResident(listresidents);
            else
            {
                ArrayList<Resident> sortedListResident = new ArrayList<>();
                
                for (int j=0; j<listresidents.size(); j++) {
                  if (listresidents.get(j).getNom_resident().contains(searchField.getText()) || listresidents.get(j).getPrenom_resident().contains(searchField.getText())) {
                            sortedListResident.add(listresidents.get(j));
                        }

                    }

                     fillListResident(sortedListResident);
            }
            
            
        ip.dispose();
        });    
    
        setupSideMenu(res);

    }

    private Image colorCircle(int color) {
        int size = Display.getInstance().convertToPixels(3);
        Image i = Image.createImage(size, size, 0);
        Graphics g = i.getGraphics();
        g.setColor(color);
        g.fillArc(0, 0, size, size, 0, 360);
        return i;
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
    
      
    private void addButtonBottom(Image arrowDown, String text, int color, boolean first,String idResident,String resident,Resident r) {
       ServiceFicheMedicale sp=new ServiceFicheMedicale();
       ServiceDossier sd = new ServiceDossier();
        ArrayList<Fiche_medicale> listfiches = sp.getListFicheByResident(idResident);
        ArrayList<Dossier> dossier = sd.getDossierByResident2(idResident);
        
        MultiButton finishLandingPage = new MultiButton(text);
        finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(),  first));
        finishLandingPage.setIconUIID("Container");
        add(FlowLayout.encloseIn(finishLandingPage));
        
        finishLandingPage.addActionListener(al -> {
      //  ToastBar.showMessage("clicked : ",FontImage.MATERIAL_GRAIN);
                  Dialog d = new Dialog(resident);
                  
                  Button afficherFiches = new Button("Afficher Fiches");
                   Button ajouterFiches = new Button("Ajouter Fiche");
                    Button afficherDossier = new Button("Afficher Dossier");
                     Button ajouterDossier = new Button("Ajouter Dossier");
                     d.add(ajouterDossier).add(afficherDossier).add(ajouterFiches).add(afficherFiches);
                     
                     if(listfiches.size()==0){
                        afficherFiches.setHidden(true);
                        afficherFiches.setVisible(false);
                     }
                     else if(listfiches.size()!=0){
                        ajouterFiches.setHidden(true);
                        ajouterFiches.setVisible(false);
                     }
                     
                     if(dossier.size()==0){
                         afficherDossier.setHidden(true);
                         afficherDossier.setVisible(false);
                     }
                     else if(dossier.size()!=0){
                         ajouterDossier.setHidden(true);
                         ajouterDossier.setVisible(false);
                     }
                     
                     afficherFiches.addActionListener(af ->{
                        new Loader(this.resources);
                             new FicheMedicaleForm( this.resources,idResident,r).show();
                     });
                     ajouterFiches.addActionListener(af ->{
                            new Loader(this.resources);
                            new NouvelleFicheMedicaleForm(this.resources, idResident,r).show();
                     });
                     afficherDossier.addActionListener(af ->{
                          new Loader(this.resources);
                             new DossierMedicalForm( this.resources,idResident,r).show();
                     });
                     ajouterDossier.addActionListener(af ->{
                             new Loader(this.resources);
                             new AjouterDossierMedicalForm(this.resources, idResident,r).show();
                     });
                       
                    d.showPopupDialog(finishLandingPage);
      
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
    private Map<String, Object> createListEntry(String name, String date) {
    Map<String, Object> entry = new HashMap<>();
    entry.put("Line1", name);
    entry.put("Line2", date);
    return entry;
}
    
    public void fillListResident(ArrayList<Resident> listresidents){
        
         for (int j=0; j<listresidents.size(); j++) {
    //    listresidents.forEach(e->{
        Container cont = new Container();
        Label l = new Label();
        l.setText(listresidents.get(j).getNom_resident());
        
        String resident =  listresidents.get(j).getNom_resident() +" "+listresidents.get(j).getPrenom_resident();
               String idResident = String.valueOf(listresidents.get(j).getId_resident());
         addButtonBottom(null, resident, 0x4dc2ff, true,idResident,resident,(Resident) listresidents.get(j));
        
        
        l.addPointerPressedListener((evt) -> {
            
            
        });
      }
     //   });
        /*
           InfiniteContainer ic = new InfiniteContainer() {
        @Override
        public Component[] fetchComponents(int index, int amount) {

            amount = listresidents.size() - index ;  
            
            Component[] cmp = new Component[amount];
            
            for(int iter = 0 ; iter < amount ; iter++) {
                
               Container c  = new Container(BoxLayout.y());
               
               String resident =  String.valueOf(listresidents.get(iter).getNom_resident() +" "+
                                                 listresidents.get(iter).getPrenom_resident());
               String idResident = String.valueOf(listresidents.get(iter).getId_resident());
         addButtonBottom(null, resident, 0x4dc2ff, true,idResident,resident);
         
         
       //  c.add(new Label(resident));
                
               cmp[iter] = c;          
            }
            return cmp;
        }
};
           
            add(ic);
            return ic;*/
    }
        
        
/*
    private XYMultipleSeriesRenderer createChartMultiRenderer() {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        for(int color : COLORS) {
            XYSeriesRenderer r = new XYSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
            r.setFillPoints(false);
            XYSeriesRenderer.FillOutsideLine outline = new XYSeriesRenderer.FillOutsideLine(XYSeriesRenderer.FillOutsideLine.Type.BELOW);
            outline.setColor(color);
            r.addFillOutsideLine(outline);
            r.setLineWidth(5);
        }
        renderer.setPointSize(5f);
        renderer.setLabelsColor(0);
        renderer.setBackgroundColor(0xffffffff);
        renderer.setApplyBackgroundColor(true);
        renderer.setAxesColor(COLORS[0]);

        renderer.setXTitle("");
        renderer.setYTitle("");
        renderer.setAxesColor(0xcccccc);
        renderer.setLabelsColor(0);
        renderer.setXLabels(5);
        renderer.setYLabels(5);
        renderer.setShowGrid(true);
        
        renderer.setMargins(new int[] {0, 0, 0, 0});
        renderer.setMarginsColor(0xffffff);

        renderer.setShowLegend(false);
        
        renderer.setXAxisMin(3);
        renderer.setXAxisMax(8);
        renderer.setYAxisMin(0);
        renderer.setYAxisMax(10);
        return renderer;
    }
*/
    
    
}
/*
 SimpleDateFormat sd = new SimpleDateFormat("yyyy-MMM-dd-kk-mm");
                String fileName =sd.format(new Date());
*/
