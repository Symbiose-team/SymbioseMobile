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
public class DetailsEvent extends Form{
    userService u = new userService();
    ServiceEvent e = new ServiceEvent();
    public DetailsEvent(Form previous) {
        //setTitle("Add a new Event");
        
        //SpanLabel sp = new SpanLabel();
        //sp.setText(ServiceEvent.getInstance().getAllEvent().toString());
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
        // super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        User us = u.profile();
        System.out.println(us);
        String role = us.getRole();
        String name = us.getFirst_name();
        String lastname = us.getLast_name();
        String email = us.getEmail();
        String adresse= us.getAdresse();
        String phone = us.getPhone_number();
        String cin = us.getCin();
        String birthday = us.getBirthday();
        String genre = us.getGenre();
        Button btnDashboard = new Button("Go back Dashboard");
        TextField idEvent = new TextField("","Event id");
        Button showDetail = new Button("Show Event");
        SpanLabel sp = new SpanLabel();
        
        Event ev = e.getEvent("220");
        System.out.println(ev);


        btnDashboard.addActionListener(e-> previous.showBack());
        
        showDetail.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                if ((idEvent.getText().length()==0)){
                    Dialog.show("Alert","Please fill all the fields", new Command("OK"));
                }
                else
                {
                    //sp.setText(ServiceEvent.getInstance().getEvent(idEvent.getText()).toString());
                   
                    String ev_name = ev.getName();
                    String type = ev.getType();
                    int num_participant = ev.getNumParticipants();
                    int num_remaining = ev.getNumRemaining();
                    int state = ev.getState();
                    
                    addButtonBottom("Name: " + ev_name, 0x5ae29d, true);
                    addButtonBottom("Lastname: " + type, 0x5ae29d, true);
                    addButtonBottom("num_participant: " + num_participant, 0x5ae29d, true);
                    addButtonBottom("num_remaining: " + num_remaining, 0x5ae29d, true);
                    addButtonBottom("State: " + state, 0x5ae29d, true);

                }
            }
        });

        addAll(idEvent,btnDashboard,showDetail);
       

        addButtonBottom("Name: " + name, 0x5ae29d, true);
        addButtonBottom("Lastname: " + lastname, 0x5ae29d, true);
        addButtonBottom("Email: " + email, 0x5ae29d, true);
        addButtonBottom("Genre: " + genre, 0x5ae29d, true);
        addButtonBottom("Role: " + role, 0x5ae29d, true);
        addButtonBottom("Adresse: " + adresse, 0x5ae29d, true);
        addButtonBottom("Cin : " + cin, 0x5ae29d, true);
        addButtonBottom("Phone: " + phone, 0x5ae29d, true);
        addButtonBottom("Birthday: " + birthday, 0x5ae29d, true);
        
  
    }
    
    private void addButtonBottom(String text, int color, boolean first) {
        MultiButton finishLandingPage = new MultiButton(text);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(), first));
        finishLandingPage.setIconUIID("Container");
        add(FlowLayout.encloseIn(finishLandingPage));
    }

    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if (first) {
            y = height / 6 + 1;
        }
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 4 - height / 2, height / 6, height / 4, height / 2, 0, 360);
        return img;
    }
}