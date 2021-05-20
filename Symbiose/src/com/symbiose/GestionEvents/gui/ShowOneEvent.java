/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionEvents.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.symbiose.GestionEvents.entities.Event;
import com.symbiose.GestionEvents.services.ServiceEvent;

/**
 *
 * @author Mahdi-PC
 */
public class ShowOneEvent extends Form {
    Form current;          
    public ShowOneEvent(Form previous,String idM) {

        Event m =new Event();
        m= ServiceEvent.getInstance().getOneEventById(idM);

        Toolbar tb = new Toolbar();
        setToolbar(tb);
        tb.setUIID("Container");
        tb.setBackCommand("", e -> new List2EventForm(previous).showBack());
        setTitle("Event : " +m.getName());

        Container cnt1 = new Container(BoxLayout.y());
        Container cnt2 = new Container(BoxLayout.xCenter());
        cnt2.getAbsoluteX();

        SpanLabel SLnom = new SpanLabel("Name :       "+m.getName());
        SpanLabel SLtype = new SpanLabel("Type :"+m.getType());
        SpanLabel SLnumberRemaining = new SpanLabel("numberRemaining :       "+m.getNumRemaining());
        SpanLabel SLES = new SpanLabel("                ");


        cnt1.add(SLnom);
        cnt1.add(SLtype);
        cnt1.add(SLnumberRemaining);
        cnt2.add(cnt1);
        addAll(cnt2);
    }
}