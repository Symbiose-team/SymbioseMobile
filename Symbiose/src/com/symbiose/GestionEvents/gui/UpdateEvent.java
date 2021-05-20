/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionEvents.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.symbiose.GestionEvents.entities.Event;
import com.symbiose.GestionEvents.services.ServiceEvent;

/**
 *
 * @author Mahdi
 */
public class UpdateEvent extends Form {
    
    public UpdateEvent(Form previous,String idM) {
        Resources res = null;

        TextField name = new TextField("","nom");
        Picker date = new Picker();
        date.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        TextField tfType = new TextField("","Type");
        
        Toolbar tb = new Toolbar();
        setToolbar(tb);
        tb.setUIID("Container");
        //tb.setBackCommand("", e -> new HomeForm(res).show());
        tb.setBackCommand("", e -> new List2EventForm(previous).show());
        //Button btnDashboard = new Button("Go back Dashboard");
        //btnDashboard.addActionListener(e-> new List2EventForm(previous).show());
        
        
        Button btn = new Button("update");
        Button btncancel = new Button("cancel");
        
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) { 
                
            if (name.getText()== null){
                    Dialog.show("Alert","Please fill all the fields", new Command("OK"));
                }
                else
                {
                    Event event = new Event(Integer.parseInt((idM)),name.getText());                
                    ServiceEvent.getInstance().updateReponse(event);
                    Dialog.show("Success","Connection accepted", new Command("OK"));
                    new List2EventForm(previous).show();
                }
            }
        });
        btncancel.addActionListener(e-> new List2EventForm(previous).show());

        addAll(name,btn,btncancel);    
    }

   
}

