/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionCommunication.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.symbiose.GestionCommunication.entities.Publication;
import com.symbiose.GestionCommunication.entities.Commentaire;
import com.symbiose.GestionCommunication.entities.Message;
import com.symbiose.GestionCommunication.entities.Conversation;
import com.symbiose.GestionCommunication.services.ServicePublication;
import com.symbiose.GestionCommunication.services.ServiceMessage;
import java.util.ArrayList;

/**
 *
 * @author abbes
 */
public class DetailConver {
      Form f;
  ImageViewer img;
    SpanLabel description_question,titre_question;
  
    Container ctnq, ctnr;
           ArrayList<Message>  listmsg = new ArrayList<>();
    
    public DetailConver() {
    
    
     
         
              f = new Form("Detail du conversation", new FlowLayout(Component.CENTER));
        f.getAllStyles().setBgColor(0xfff2e6);
        ServiceMessage servicem=new ServiceMessage();
          ctnq = new Container();
          //ctnq.getStyle().setBgColor(0xA8FB9E);
       
                   // ctnq.getStyle().setBgTransparency(255);
                   
       
        listmsg=servicem.getDetailMessage();
        
       // System.out.println("hedhy listet reponse"+serviceReponse.getDetailQuestion().toString());
      

   
              for ( Message r :listmsg ){
               
               ctnr = new Container(BoxLayout.y());
               Container  ctn_verticale = new Container();
               Label description_rep  = new Label();
      
          description_rep.setText(r.getContenu());
          
            Label date_rep = new Label();
            
    
       
   
            
            
                  ctnr.add(description_rep);
        ctnr.add(date_rep);
       Button   deleteBtn = new Button("supprimer");
          ctn_verticale.add(deleteBtn);
      
              deleteBtn.addActionListener((evt) -> {
          if(Dialog.show("voulez vous supprimer ce message?", "", "oui", "Non")) {
                    
         servicem.deleteMessage(r.getId());
                        //methode envoie mail

                }
    
           
      
  

      
      
       DetailConver dq=new DetailConver();
    dq.getF().show();
          
       }  );
         
          
      
       
           f.add(ctnr);
          f.add(ctn_verticale);
 
       f.getToolbar().addCommandToRightBar("retour", null, (ev)->{AffichageListConver l=new AffichageListConver();
          l.getF().show();
          });
     
 }
    
            
            
            
            
            
            
     
    
        
    
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
      
      
    
}
