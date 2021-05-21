/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionFields.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.symbiose.GestionFields.entities.field;
import static java.lang.Float.parseFloat;
import com.symbiose.GestionFields.services.ServiceField;

/**
 *
 * @author Chaima
 */
public class consulter extends Form {

    consulter(Form current) {
         setTitle("Liste de terrain ");
         SpanLabel sp = new SpanLabel();
        TextField id = new TextField("","id terrain");
        Button btnValider = new Button("Affiche detail");
      btnValider.addActionListener(new ActionListener() {
           @Override
        public void actionPerformed(ActionEvent evt) {

         field f =new field((int) parseFloat(id.getText()));
         sp.setText(ServiceField.getInstance().getAllTaskss(f).toString());}
            });
        addAll(sp,id,btnValider);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> current.showBack()); // Revenir vers l'interface précédente
    }
        }