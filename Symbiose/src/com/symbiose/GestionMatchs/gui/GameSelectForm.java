/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionMatchs.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.symbiose.GestionMatchs.entities.Game;
import com.symbiose.GestionMatchs.services.ServiceGame;

/**
 *
 * @author chedly
 */
public class GameSelectForm extends Form {
    Form current;          
    public GameSelectForm(Form previous,String idM) {

        Game g =new Game();
        g= ServiceGame.getInstance().getOneEventById(idM);

        Toolbar tb = new Toolbar();
        setToolbar(tb);
        tb.setUIID("Container");
        tb.setBackCommand("", e -> new DeleteGameForm(previous).showBack());
        setTitle("Game : " +g.getName());

        Container cnt1 = new Container(BoxLayout.y());
        Container cnt2 = new Container(BoxLayout.xCenter());
        cnt2.getAbsoluteX();

        SpanLabel SLnom = new SpanLabel("Name :  "+g.getName());
        SpanLabel SLTime = new SpanLabel("Time :"+g.getTime());



        cnt1.add(SLnom);
        cnt1.add(SLTime);
        cnt2.add(cnt1);
        addAll(cnt2);
    }
}