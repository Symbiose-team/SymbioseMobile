/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionMatchs.gui;


import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.symbiose.GestionUsers.gui.Dashboard;

/**
 *
 * @author chedly
 */
public class GameForm extends Form {

    Form current;
    /*Garder traçe de la Form en cours pour la passer en paramètres 
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/
    
    public GameForm(Resources res) {
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btnDashboard = new Button("Go back Dashboard");

        Button btnAddTask = new Button("Add Game");
        // Button btnUpdate = new Button("Update event");
        Button btnListTasks = new Button("List Games");

        
        btnDashboard.addActionListener(e-> new Dashboard(res).show());
        btnAddTask.addActionListener(e -> new AddGameForm(current).show());
        Button extra = new Button("Extra options list");

       //btnUpdate.addActionListener(e -> new UpdateGameForm(current).show());
        extra.addActionListener(e-> new DeleteGameForm(current).show());

        btnListTasks.addActionListener(e -> new ListGameForm(current).show());
        addAll(btnDashboard,btnAddTask,extra ,btnListTasks);

    }

}

