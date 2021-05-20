/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionFields.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.symbiose.GestionFields.services.ServiceField;

/**
 *
 * @author Chaima
 */
public class ListeForm extends Form {
    public ListeForm (Form dds){
        setTitle("Liste de terrain ");
         SpanLabel sp = new SpanLabel();
        sp.setText(ServiceField.getInstance().getAllTasks().toString());
        add(sp);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> dds.showBack()); // Revenir vers l'interface précédente
    }
    
}
