/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionEvents.gui;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.symbiose.GestionEvents.entities.Event;
import com.symbiose.GestionEvents.services.ServiceEvent;
import com.symbiose.GestionUsers.entities.User;
import com.symbiose.GestionUsers.gui.Dashboard;
import com.symbiose.GestionUsers.services.userService;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author Mahdi
 */
public class ListEventsForm extends Form{
    public ListEventsForm(Form previous) {
        TextField tfId = new TextField("","Event id for joining");
        SpanLabel sp = new SpanLabel();
        sp.setText(ServiceEvent.getInstance().getAllEvents().toString());
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
        //super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        
        Button btnValider = new Button("Join Event");
        
        btnValider.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                if ((tfId.getText().length()==0)){
                    Dialog.show("Alert","Please fill all the fields", new Command("OK"));
                }
                else
                {
                    SimpleDateFormat format = new SimpleDateFormat();  
                    
                    try {
                        Event e = new Event(Integer.parseInt((tfId.getText())));
                        
                        if(new ServiceEvent().joinEvent(e))
                            Dialog.show("Success","Connection accepted", new Command("OK"));
                        else
                            Dialog.show("ERROR","Server error", new Command("OK"));
                 
                    }catch (NumberFormatException e){
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                }
            }
        });

        Button btnDashboard = new Button("Go back Dashboard");
        btnDashboard.addActionListener(e-> previous.showBack());

        addAll(sp,tfId,btnValider,btnDashboard);
    }
}
