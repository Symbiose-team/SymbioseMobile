/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionEvents.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.symbiose.GestionEvents.services.ServiceEvent;

/**
 *
 * @author Mahdi
 */
public class ListEventsForm extends Form{

    public ListEventsForm(Form previous) {
        setTitle("Add a new Event");
        
        SpanLabel sp = new SpanLabel();
        sp.setText(ServiceEvent.getInstance().getAllEvent().toString());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
    
    
    
}
