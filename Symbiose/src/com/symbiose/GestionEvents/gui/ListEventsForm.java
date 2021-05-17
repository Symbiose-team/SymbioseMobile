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
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
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
        setTitle("Add a new Event");
        
        SpanLabel sp = new SpanLabel();
        sp.setText(ServiceEvent.getInstance().getAllEvents().toString());
        add(sp);
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
        //super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        
        
        Button btnDashboard = new Button("Go back Dashboard");
        btnDashboard.addActionListener(e-> previous.showBack());

        addAll(sp,btnDashboard);
    }
}
