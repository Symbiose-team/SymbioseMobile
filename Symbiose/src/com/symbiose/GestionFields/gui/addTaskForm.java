/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionFields.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.symbiose.GestionFields.entities.field;
import static java.lang.Float.parseFloat;
import java.util.Date;
import com.symbiose.GestionFields.services.ServiceField;

/**
 *
 * @author Chaima
 */
public class addTaskForm extends Form {
    public addTaskForm (Form add){
        setTitle("ajouter un nouveau terrain");

        setLayout(BoxLayout.y());

        TextField tfName = new TextField("","nom terrain");
        TextField serial = new TextField("","nom serielNumber");
        TextField price = new TextField("","nom price");
        TextField space = new TextField("","nom space");
        TextField address = new TextField("","nom address");
        TextField provider = new TextField("","nom provider");





        Button btnValider = new Button("Add task");
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        Picker datePicker1 = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {

                        field t = new field( tfName.getText(), (int) parseFloat(serial.getText()), price.getText(),"ssdsd","dss",datePicker.getDate(),datePicker1.getDate());
                        System.out.println(t.getEntre());
                        if( ServiceField.getInstance().addTask(t))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }


            }
        });
      addAll(tfName,serial,price,address,provider,space ,datePicker,datePicker1,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> add.showBack()); // Revenir vers l'interface précédente
    }
}
