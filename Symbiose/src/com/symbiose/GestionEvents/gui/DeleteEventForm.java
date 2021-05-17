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
import com.codename1.ui.layouts.BoxLayout;
import com.symbiose.GestionEvents.entities.Event;
import com.symbiose.GestionEvents.services.ServiceEvent;

/**
 *
 * @author Mahdi
 */
public class DeleteEventForm extends Form{
    public DeleteEventForm(Form previous) {
        setTitle("Delete an Event");
        setLayout(BoxLayout.y());
        
        TextField tfID = new TextField("","ID");
        //TextField tfName = new TextField("","Event name");
        
        Button btnValider = new Button("Delete Event");
        Button btnDashboard = new Button("Go back Dashboard");

        btnDashboard.addActionListener(e-> previous.showBack());

        addAll(btnDashboard);
        
        btnValider.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                if ((tfID.getText().length()==0)){
                    Dialog.show("Alert","Please fill all the fields", new Command("OK"));
                }
                else
                {
                    try {
                        Event e = new Event(Integer.parseInt((tfID.getText())));
                                       
                        
                        if(new ServiceEvent().deleteEvent(e.getId())){
                            Dialog.show("Success","Connection accepted", new Command("OK"));
                            previous.showBack();
                        }
                        else
                            Dialog.show("ERROR","Server error", new Command("OK"));
                 
                    }catch (NumberFormatException e){
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                }
            }
        });

        addAll(tfID,btnValider);
    }
}
