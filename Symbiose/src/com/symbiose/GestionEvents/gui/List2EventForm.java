/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionEvents.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.symbiose.GestionEvents.entities.Event;
import com.symbiose.GestionEvents.services.ServiceEvent;
import java.util.ArrayList;

/**
 *
 * @author Mahdi-PC
 */
public class List2EventForm extends Form{
    Form current;
    SpanLabel lb;

    
        
    public List2EventForm(Form previous) {

        
        setTitle("List of events");
        System.out.println(ServiceEvent.getInstance().getAllEvents());
        ArrayList<Event> events = new ArrayList();
        events =ServiceEvent.getInstance().getAllEvents();
        Toolbar tb = new Toolbar();
        setToolbar(tb);
        tb.setUIID("Container");
        tb.setBackCommand("back", e -> previous.showBack());
        for (Event m :events ) {
                    
                Container cnt1 = new Container(BoxLayout.y());
                Container cnt2 = new Container(BoxLayout.x());
                String idM = String.valueOf(m.getId());
                Button show = new Button ("Show",idM);
                Button delete = new Button ("Delete",idM);
                Button join = new Button ("Join",idM);
                Button update = new Button ("Update",idM);
                Button separator = new Button ("-----------------------------");


                SpanLabel SLnom = new SpanLabel("Name :"+m.getName());
                SpanLabel SLType = new SpanLabel("Type :"+m.getType());
                SpanLabel SLnumberRemaining = new SpanLabel("numberRemaining :"+m.getNumRemaining());

		
                cnt1.add(SLnom);
                cnt1.add(SLType);
                cnt1.add(SLnumberRemaining);
                cnt1.add(update);
                cnt1.add(delete);
                cnt1.add(join);
                cnt1.add(separator);
                cnt2.add(cnt1);
                
                update.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent evt){
                        new UpdateEvent(current,show.getUIID()).show();
                    }
                });
                
                delete.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent evt){
                        if(new ServiceEvent().deleteEvent(delete.getUIID())){
                            Dialog.show("Success","Connection accepted", new Command("OK"));
                            new List2EventForm(previous).show();
                        }
                        else
                            Dialog.show("ERROR","Server error", new Command("OK"));
                    }
                });
                
                join.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent evt){
                        if(new ServiceEvent().joinEvent(join.getUIID())){
                            Dialog.show("Success","Connection accepted", new Command("OK"));
                            new List2EventForm(previous).show();
                        }
                        else
                            Dialog.show("ERROR","Server error", new Command("OK"));
                    }
                });
    
            add(cnt2);
        }
    }
}
