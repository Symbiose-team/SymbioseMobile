/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionCommunication.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.symbiose.GestionCommunication.entities.Message;
import com.symbiose.GestionUsers.entities.User;
import com.symbiose.GestionCommunication.services.ServicePublication;
import com.symbiose.GestionCommunication.services.ServiceMessage;
import com.symbiose.Utils.Session;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author abbes
 */
public class AjoutMessage {
        Form f;
   User u = new User();
    TextField tdesc;
    Button btnajout;
private Resources theme;

   
    public AjoutMessage(int id_q) {
                        theme = UIManager.initFirstTheme("/theme");

          f = new Form("Conversations");
        tdesc = new TextField("","message");
        btnajout = new Button("Envoyer un msg");
         f.add(tdesc);
          f.add(btnajout);
          
      
          
             Date actuelle= new Date();
        DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        String date=dateFormat.format(actuelle);
        String dc= date;
     
        

      
            btnajout.addActionListener((e)->{
                            ServiceMessage ser = new ServiceMessage();

      //methode eli bch ttajouti
                Message rep = new Message(id_q,Session.u.getId(),tdesc.getText());
if(Dialog.show("vous voulez envoyer ce message?", "", "oui", "Non")) {
                 ser.ajoutMessage(rep);
                }
         
          
          
          
          
          
          
          
          
          
varGlobales.setId(id_q);
                 DetailConver dq=new DetailConver();
  dq.getF().show();
              
        });
    }
   
    
       public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    
 public TextField getTdesc() {
        return tdesc;
    }

    public void setTdesc(TextField tdesc) {
        this.tdesc = tdesc;
    }
    
}
