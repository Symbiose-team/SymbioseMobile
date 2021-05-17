/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionEvents.gui;

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
import com.symbiose.GestionEvents.entities.Event;
import com.symbiose.Utils.Statics;
import com.symbiose.GestionEvents.services.ServiceEvent;
import java.util.Date;

/**
 *
 * @author Mahdi
 */
public class AddEventForm extends Form{

    public AddEventForm(Form previous) {
        setTitle("Add a new Event");
        setLayout(BoxLayout.y());
        
        TextField tfName = new TextField("","Event name");
        //TextField tfSupplier = new TextField("","Supplier");
        //Picker date = new Picker();
        //date.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        TextField tfType = new TextField("","Type");
        Integer numParticipants = 100;
        Integer numRemaining = 100;
        //int State = 0;
        //TextField tfNumParticipants = new TextField("","Num Participants");
        //TextField tfNumRemaining = new TextField("","Num Remaining");
        //TextField tfStatus = new TextField("","Status");
        Button btnValider = new Button("Add Event");
        
        btnValider.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                if ((tfName.getText().length()==0)||(tfType.getText().length()==0)){
                    Dialog.show("Alert","Please fill all the fields", new Command("OK"));
                }
                else
                {
                    try {
                        Event e = new Event(tfName.getText(), 
                                            numParticipants,
                                            numRemaining,
                                            tfType.getText());
                        
                        if(new ServiceEvent().addEvent(e))
                            Dialog.show("Success","Connection accepted", new Command("OK"));
                        else
                            Dialog.show("ERROR","Server error", new Command("OK"));
                 
                    }catch (NumberFormatException e){
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                }
            }
        });

        addAll(tfName,
                //tfSupplier,
                tfType,
                //date,
                btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }

    
}
