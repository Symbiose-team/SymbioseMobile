/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionEvents.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Mahdi
 */
public class HomeForm extends Form{
    Form current;
    public HomeForm(){
        current=this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddEvent = new Button("Add event");
        Button btnListEvents = new Button("List events");
        
        btnAddEvent.addActionListener(e-> new AddEventForm(current).show());
        btnListEvents.addActionListener(e-> new ListEventsForm(current).show());
        addAll(btnAddEvent,btnListEvents);
    
    }
    
}
