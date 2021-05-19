/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionEvents.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.symbiose.GestionEvents.entities.Event;
import com.symbiose.GestionEvents.services.ServiceEvent;

/**
 *
 * @author Mahdi
 */
public class UpdateEvent extends Form {
    

    public UpdateEvent(Form previous) {
        TextField idS = new TextField("","id");
        TextField name = new TextField("","nom");
        Button btnDashboard = new Button("Go back Dashboard");
        btnDashboard.addActionListener(e-> previous.showBack());
        Button btn = new Button("update");
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) { 
                
            if (name.getText()== null){
                    Dialog.show("Alert","Please fill all the fields", new Command("OK"));
                }
                else
                {
                    Event event = new Event(Integer.parseInt((idS.getText())),name.getText());
                                        
                    ServiceEvent.getInstance().updateReponse(event);
                    Dialog.show("Success","Connection accepted", new Command("OK"));
                    previous.showBack();
                }
    

        }
        });
        addAll(idS,name,btn,btnDashboard);    
    }

   
}

