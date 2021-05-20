/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionFields.gui;

import static com.codename1.charts.compat.Paint.Join.MITER;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.symbiose.GestionFields.entities.field;
import static java.util.Collections.list;
import java.util.List;
import com.symbiose.GestionFields.services.ServiceTask;
public class test extends Form{
    private List<field> listprom;
    private Form form ;
    private Toolbar tb;
    private Container evenements;
    Form current;
    
    public test(){
         current = this;

       
        
//        setTitle("Liste des promotions");
        
        
//        SpanLabel sp = new SpanLabel();
        List<field> list = ServiceTask.getInstance().getAllTasks();
        
        for (field p : list){
        
        Container cnt1 = new Container(BoxLayout.y());   
        Container cnt2 = new Container(BoxLayout.x());
        
        SpanLabel SLnom = new SpanLabel(p.getName());
     
//        img.getAllStyles().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FIT);
//        SpanLabel SLnom = new SpanLabel(p.getNompromotion());
//        SpanLabel SLnom = new SpanLabel(p.getNompromotion());
//        cnt1.getStyle().getBgColor(0x99cccc);
        
//        cnt2.getStyle().setBgColor(0x99CCCC);
//        cnt2.getStyle().setBgTransparency(255);
//        Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN MITER, 1);
//        cnt2.getStyle().setBorder(RoundBorder.create(). rectangle(true). color(0xffffff). strokeColor(0). strokeOpacity(50));


        cnt1.add(SLnom);
        cnt1.add(cnt2);
        add(cnt1);
        
            
        
        }
//        sp.setText(PromotionService.getInstance().getAllpromo().toString());
//        add(sp);
//        getToolbar().addMaterialCommandToLeftBar("Retour", FontImage.MATERIAL_ARROW_BACK, e-> res.showBack());
       
    
    }



    

}
