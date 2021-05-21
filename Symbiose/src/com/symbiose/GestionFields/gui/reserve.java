/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionFields.gui;

/**
 *
 * @author Mahdi
 */
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
public class reserve extends Form{

    reserve(Form current) {
        setTitle("field");
        setLayout(BoxLayout.y());
        TextField idS = new TextField("","SerialNumber");
     //   TextField status = new TextField("","status");

        Button btn = new Button("Reserver");
           btn.addActionListener(new ActionListener() {
           @Override
      public void actionPerformed(ActionEvent evt) {


            field terrain = new field((int) parseFloat(idS.getText()),true);
          ServiceField.getInstance().updateReponsee(terrain); }

        });
              addAll(idS,btn);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> current.showBack()); 
    }    
}