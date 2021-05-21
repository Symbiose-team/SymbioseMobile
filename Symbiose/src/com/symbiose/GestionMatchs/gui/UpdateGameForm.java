/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionMatchs.gui;

import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.symbiose.GestionMatchs.entities.Game;
import com.symbiose.GestionMatchs.services.ServiceGame;
import java.util.ArrayList;

/**
 *
 * @author chedly
 */
public class UpdateGameForm extends Form {
    
    
    public UpdateGameForm(Form previous,String idM) {
        setTitle("Add a new game");
        setLayout(BoxLayout.y());
        Toolbar tb = new Toolbar();
        setToolbar(tb);
        tb.setUIID("Container");
        tb.setBackCommand("", e -> previous.showBack());
        TextField tfName = new TextField("","Game name");

        Picker date = new Picker();
        date.setType(Display.PICKER_TYPE_DATE_AND_TIME);


        Button btnValider = new Button("Update Game");
        Button btnDashboard = new Button("Go back Dashboard");

   //     btnDashboard.addActionListener(e-> previous.showBack());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

        addAll(btnDashboard);
        
        
        btnValider.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) { 
                
            if (tfName.getText()== null){
                    Dialog.show("Alert","Please fill all the fields", new Command("OK"));
                }
                else
                {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
                    Game game = new Game(Integer.parseInt((idM)),tfName.getText(),format.format(date.getDate()));                
                    ServiceGame.getInstance().updateReponse(game);
                    Dialog.show("Success","Connection accepted", new Command("OK"));
                    new DeleteGameForm(previous).show();
                }
            }
        });
    addAll(tfName,date,btnValider);
    }    

   
}
   



