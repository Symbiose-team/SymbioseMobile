/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionMatchs.gui;


import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.symbiose.GestionMatchs.services.ServiceGame;

/**
 *
 * @author chedly
 */
public class ListGameForm extends Form{
public ListGameForm(Form previous) {
        setTitle("List Games");
        
        SpanLabel sp = new SpanLabel();
        sp.setText(ServiceGame.getInstance().getAllGames().toString());
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
}
