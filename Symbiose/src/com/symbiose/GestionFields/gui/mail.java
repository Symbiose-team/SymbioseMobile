/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionFields.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.symbiose.GestionFields.entities.field;
import com.symbiose.GestionFields.services.ServiceTask;

/**
 *
 * @author Chaima
 */
public class mail extends Form {
Form current; 
    private Form form ;
    private Toolbar tb;
    private Container evenements;
    public mail() {
        ComboBox box =new ComboBox();
        box.addItem("Client");
        box.addItem("Fournisseur");
        Button mail=new Button("mail");

        box.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           new addTaskForm(current).show();
            }
        });

         current = this;
        setTitle("field");
        setLayout(BoxLayout.y());
        add(new Label(""));
       
        Button maile=new Button("mail");
       maile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                     ServiceTask.getInstance().envoie();
                     
                    
                }
                
                
            });
    

        addAll(maile);
        
    }


}
