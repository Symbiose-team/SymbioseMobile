/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionMatchs.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.symbiose.GestionMatchs.entities.Game;
import com.symbiose.GestionMatchs.services.ServiceGame;
import java.util.ArrayList;

/**
 *
 * @author chedly
 */
public class DeleteGameForm extends Form{
    Form current;
    SpanLabel lb;

    
        
    public DeleteGameForm(Form previous) {

        
        setTitle("List of events");
        ArrayList<Game> games = new ArrayList();
        games =ServiceGame.getInstance().getAllGames();
        Toolbar tb = new Toolbar();
        setToolbar(tb);
        tb.setUIID("Container");
        tb.setBackCommand("Go back", e -> previous.showBack());
       
        for (Game g :games ) {
                    
                Container cnt1 = new Container(BoxLayout.y());
                Container cnt2 = new Container(BoxLayout.x());
                String idM = String.valueOf(g.getId());
                Button show = new Button ("Show",idM);
                Button delete = new Button ("Delete",idM);
                Button join = new Button ("Join",idM);
                Button update = new Button ("Update",idM);

                Button separator = new Button ("-----------------------------");


                SpanLabel SLnom = new SpanLabel("Name :"+g.getName());
                SpanLabel SLTime = new SpanLabel("Type :"+g.getTime());

		
                cnt1.add(SLnom);
                cnt1.add(SLTime);
                cnt1.add(update);
                cnt1.add(delete);
                cnt1.add(show);
                cnt1.add(join);
                cnt1.add(separator);
                cnt2.add(cnt1);
                
                update.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent evt){
                      //  new AddGameForm(current,show.getUIID()).show();
                        new UpdateGameForm(current,show.getUIID()).show();

                    }
                });
                
                delete.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent evt){
                        if(new ServiceGame().deleteGame(delete.getUIID())){
                            Dialog.show("Success","Connection accepted", new Command("OK"));
                            new DeleteGameForm(previous).show();
                        }
                        else
                            Dialog.show("ERROR","Server error", new Command("OK"));
                    }
                });
                  show.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent evt){
                        new GameSelectForm(current,show.getUIID()).show();
                    }
                });
           
    
            add(cnt2);
        }
    }
}