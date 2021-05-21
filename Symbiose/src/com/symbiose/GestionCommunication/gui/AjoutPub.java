/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionCommunication.gui;

import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.symbiose.GestionCommunication.services.ServicePublication;
import com.symbiose.GestionCommunication.entities.Publication;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

/**
 *
 * @author bhk
 */
public class AjoutPub {

    Form f;
    TextField ttitre;
    TextField tdesc;
    Button btnajout;

      private Resources theme;

    public AjoutPub(Resources res) {
                        theme = UIManager.initFirstTheme("/theme");
        f = new Form("Ajout de question", new FlowLayout(Component.CENTER));
        tdesc = new TextField("","description");
        btnajout = new Button("ajouter");
     
        
        f.add(tdesc);
        f.add(btnajout);
       
       
        Date actuelle= new Date();
        DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        String date=dateFormat.format(actuelle);
        String dc= date;
     
        

        btnajout.addActionListener((e) -> {
            ServicePublication ser = new ServicePublication();
          Publication q = new Publication(tdesc.getText());

          ser.ajoutQuestion(q);
         
                Dialog.show("Succée", " ajouté avec succée!", "Ok", null);
       AffichageListPublication aff = new AffichageListPublication(res);
       aff.getF().show();
        });
        
        f.getToolbar().addCommandToRightBar("Back", null, (ActionListener) ((evt) -> {
          new Accueil(res).getF().showBack();
        }));
       
    }
    //n3ayet lel forme hedha f blgetter hedha
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public TextField getTtitre() {
        return ttitre;
    }

    public void setTtitre(TextField ttitre) {
        this.ttitre = ttitre;
    }
 public TextField getTdesc() {
        return tdesc;
    }

    public void setTdesc(TextField tdesc) {
        this.tdesc = tdesc;
    }
}
