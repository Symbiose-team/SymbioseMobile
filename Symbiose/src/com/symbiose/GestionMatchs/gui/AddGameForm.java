/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionMatchs.gui;

import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.symbiose.GestionMatchs.entities.Game;
import com.symbiose.GestionMatchs.services.ServiceGame;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author chedly
 */
public class AddGameForm extends Form{
    
        public static final String CaptchaUret() {
        String CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder str = new StringBuilder();
        Random rnd = new Random();
        while (str.length() < 5) {
            int index = (int) (rnd.nextFloat() * CHARS.length());
            str.append(CHARS.charAt(index));
        }
        String generatedCaptcha = str.toString();
        return generatedCaptcha;
    }
    
    
    public AddGameForm(Form previous) {
        setTitle("Add a new game");
        setLayout(BoxLayout.y());
        Toolbar tb = new Toolbar();
        setToolbar(tb);
        tb.setUIID("Container");
        tb.setBackCommand("", e -> previous.showBack());
        TextField tfName = new TextField("","Game name");

        Picker date = new Picker();
        date.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        SpanLabel captcha_label = new SpanLabel();      
        captcha_label.setText(CaptchaUret());

        TextField captcha_text = new TextField("","Insert captcha");

        Button btnValider = new Button("Add Game");
        Button btnDashboard = new Button("Go back Dashboard");

        btnDashboard.addActionListener(e-> previous.showBack());

        addAll(btnDashboard);
        
        
        btnValider.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                 String s = captcha_label.getText();
                   String x = captcha_text.getText();
                if ((tfName.getText().length()==0) || (!s.equals(x))){
                    captcha_label.setText(CaptchaUret());
                    captcha_text.clear();
                    Dialog.show("Alert","Please fill all the fields and check captcha", new Command("OK"));
                }
                else
                {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
                    
                    try {
                        Game g = new Game(tfName.getText(),format.format(date.getDate()));
                        
                        if( ServiceGame.getInstance().addGame(g))
                            Dialog.show("Success","Connection accepted", new Command("OK"));
                        else
                            Dialog.show("ERROR","Server error", new Command("OK"));
                 
                    }catch (NumberFormatException e){
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                }
            }
        });

    addAll(tfName,date,captcha_label,captcha_text,btnValider);
    }    
}

    
