/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionFields.gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.symbiose.GestionFields.entities.field;

import static java.lang.Float.parseFloat;

import com.symbiose.GestionFields.services.ServiceField;

/**
 *
 * @author Chaima
 */
public class update extends Form {
    

    public update(Form current) {
          setTitle("field");
        setLayout(BoxLayout.y());
  TextField idS = new TextField("","SerialNumber");
        TextField name = new TextField("","nom");

        Button btn = new Button("update");
           btn.addActionListener(new ActionListener() {
           @Override
      public void actionPerformed(ActionEvent evt) {       
         if (name.getText()!= null){

            field terrain = new field((int) parseFloat(idS.getText()),name.getText());
          ServiceField.getInstance().updateReponse(terrain); }
    

        }
        });
              addAll(idS,name,btn);      
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> current.showBack()); 
    }
}

    

