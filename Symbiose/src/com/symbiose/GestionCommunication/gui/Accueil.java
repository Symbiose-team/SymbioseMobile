/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionCommunication.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.symbiose.GestionCommunication.entities.Publication;
import com.symbiose.GestionCommunication.services.ServicePublication;
import com.symbiose.GestionCommunication.services.ServiceCommentaire;
import com.symbiose.GestionUsers.gui.Dashboard;
import java.util.ArrayList;
import com.symbiose.Utils.SideMenuBaseForm;


/**
 *
 * @author abbes
 */
 public class Accueil {
     Form f;
    SpanLabel splb, nom, date, prix, stock, l,a,b,c;
    Container ctn, ctn1, ctn2;
    Button btn_list,btn_ajout;
    
//.getAllStyles().setBgImage(your Image) 
    public Accueil(Resources res) {

       
      
     
     f = new Form( new FlowLayout(Component.CENTER));
  
     

      
        ctn = new Container(BoxLayout.y());
        
       btn_list =new Button("Afficher");
         ctn.add(btn_list);
        btn_list.addActionListener( (evt) -> {
       
            AffichageListPublication aff =new AffichageListPublication(res);
        aff.getF().show();
          
       }
       
       );
       
       
         
       
        btn_ajout =new Button("ajouter");
         ctn.add(btn_ajout);
        btn_ajout.addActionListener( (evt) -> {
     
            AjoutPub aj =new AjoutPub(res);
        aj.getF().show();
        
       }

      
       );

    
          
          
       
       btn_list =new Button("Afficher");
       btn_list.getAllStyles().setBgColor(0x000000);
       
         ctn.add(btn_list);
        btn_list.addActionListener( (evt) -> {
       
            AffichageListConver aff =new AffichageListConver(res);
        aff.getF().show();
          
       }
       
       );
        
         Button piechartbtn =new Button("Piechart");
         ctn.add(piechartbtn);
        piechartbtn.addActionListener( (evt) -> {
        
  PieChartMobile p = new PieChartMobile();
  p.createPieChartForm().show();
    
       }
       
       );
        
        f.getToolbar().addCommandToRightBar("Back", null, (ActionListener) ((evt) -> {
            new Dashboard(res).show();
        }));
        
  
        f.add(ctn);  
      
  

        
  
    }
    
    
     
        


    
       public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
