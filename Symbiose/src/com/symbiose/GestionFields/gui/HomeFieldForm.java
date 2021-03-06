/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionFields.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.symbiose.GestionUsers.gui.Dashboard;

/**
 *
 * @author Chaima
 */
public class HomeFieldForm extends Form {
Form current; 
    private Form form ;
    private Toolbar tb;
    private Container evenements;
    
    public HomeFieldForm() {
      
        ComboBox box =new ComboBox();
         box.addItem("Admin");
        box.addItem("Client");
        box.addItem("Fournisseur");
      
        
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
        Button btn=new Button("Ajouter Terrain");
        Button delete=new Button("Supprimer Terrain");
        Button aff=new Button("Liste de Terrain");
        Button modif=new Button("Modifier Terrain ");
        Button reserve=new Button(" Reserver un Terrain ");
        Button Contact=new Button("Contact Fournisseur");
        reserve.addActionListener(e -> new reserve(current).show());

        Button consulter=new Button("Consulter Detail");
        consulter.addActionListener(e -> new consulter(current).show());


        btn.addActionListener(e -> new addTaskForm(current).show());
        Contact.addActionListener(e -> new Contacte(current).show());
        delete.addActionListener(e -> new delete(current).show());
        aff.addActionListener(e -> new ListeForm(current).show());
        modif.addActionListener(e -> new update(current).show());


        addAll(box,btn,aff,delete,modif,consulter,reserve,Contact);
        
    }


    
     

   
    
}
