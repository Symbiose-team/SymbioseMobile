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
import com.symbiose.GestionCommunication.entities.Commentaire;
import com.symbiose.GestionCommunication.services.ServicePublication;
import com.symbiose.GestionCommunication.services.ServiceCommentaire;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author abbes
 */
public class UpdateCommentaire {
        Form f;
   
    TextField tdesc;
    Button btnajout;

    public UpdateCommentaire(int id_q, int id_rep) {
          f = new Form("page de modification de commentaire");
        tdesc = new TextField("","description");
        btnajout = new Button("Modifier");
         f.add(tdesc);
          f.add(btnajout);
          
      
          
             Date actuelle= new Date();
        DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        String date=dateFormat.format(actuelle);
        String dc= date;
     
            btnajout.addActionListener((e)->{ 
                            ServiceCommentaire ser = new ServiceCommentaire();

                            
                            
                            
                Commentaire rep = new Commentaire();
                rep.setId_rep(id_rep);
                rep.setContenu(tdesc.getText());
if(Dialog.show("enregistrer changement?", "", "oui", "Non")) {
                    ser.updateReponse(rep);
                }
        

                  DetailQuestion dq=new DetailQuestion(id_q);
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
