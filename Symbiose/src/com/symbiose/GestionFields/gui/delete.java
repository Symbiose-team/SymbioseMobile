/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionFields.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.symbiose.GestionFields.entities.field;
import com.symbiose.GestionFields.services.ServiceTask;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.symbiose.GestionFields.entities.field;

/**
 *
 * @author Chaima
 */
public class delete extends Form{
    
       
    public delete(Form current) {
           setLayout(BoxLayout.y());
        TextField ts = new TextField("","nom terrain");
        Button btnValider = new Button("delete");
           btnValider.addActionListener(new ActionListener() {
           @Override
      public void actionPerformed(ActionEvent evt) {       
         if (ts!= null){
        int number= (int)Float.parseFloat(ts.getText());

         ServiceTask.getInstance().deleteReponse(number); }
    

        }
        });
                     addAll(ts,btnValider);
                       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> current.showBack()); 
    }

}

           
