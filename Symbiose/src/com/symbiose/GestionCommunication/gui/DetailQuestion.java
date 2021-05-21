/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionCommunication.gui;
import com.symbiose.GestionCommunication.gui.varGlobales;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
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
import com.codename1.ui.util.Resources;
import com.symbiose.GestionCommunication.entities.Publication;
import com.symbiose.GestionCommunication.entities.Commentaire;
import com.symbiose.GestionCommunication.services.ServicePublication;
import com.symbiose.GestionCommunication.services.ServiceCommentaire;
import java.util.ArrayList;

/**
 *
 * @author abbes
 */
public class DetailQuestion  {
      Form f;
  ImageViewer img;
    SpanLabel description_question,titre_question;
  
    Container ctnq, ctnr;
           ArrayList<Commentaire>  ListReponses = new ArrayList<>();
    private Resources res;
    
    public DetailQuestion(int id_q1) {
    
  
        f = new Form("Detail du publication", new FlowLayout(Component.CENTER));
        f.getAllStyles().setBgColor(0xfff2e6);
        ServicePublication servicec=new ServicePublication();
        ctnq = new Container();
          //ctnq.getStyle().setBgColor(0xA8FB9E);
       
                   // ctnq.getStyle().setBgTransparency(255);
                   
       ServiceCommentaire sc=new ServiceCommentaire();
                      ctnq = new Container(BoxLayout.x());

       ListReponses=sc.getlistc();
        
       // System.out.println("hedhy listet reponse"+serviceReponse.getDetailQuestion().toString());
       Label texttitre  = new Label();
       texttitre.setAutoSizeMode(true);
       texttitre.getAllStyles().setBgColor(0x00000);
       texttitre.setWidth(200);
       Label textdate = new Label();
       textdate.setAutoSizeMode(true);
    
       texttitre.setText("publication : "+(ListReponses.get(0).getContenu()));
       Label textdescription = new Label();
       textdescription.setText("publication : "+(ListReponses.get(0).getContenu()));

  textdescription.getAllStyles().setBgColor(0x0000);
  
      Button btn_commenter = new Button("commenter");
      btn_commenter.setAutoSizeMode(true);
   
      
      
               btn_commenter.addActionListener( (evt) -> {

       AjoutCommentaire ajtCm=new AjoutCommentaire(id_q1);
    ajtCm.getF().show();
 
          
       }  );
      

        ctnq.add(textdescription);
        ctnq.add(btn_commenter);
          f.add(ctnq);

   
              for ( Commentaire r :ListReponses ){
               
               ctnr = new Container(BoxLayout.x());
               Container  ctn_verticale = new Container();
               Label description_rep  = new Label();
      
          description_rep.setText(r.getContenu());
          description_rep.setAutoSizeMode(true);
            Label date_rep = new Label();
            date_rep.setText(r.getDateQuestion());
            
             
   
            
            
        ctnr.add(description_rep);
        ctnr.add(date_rep);
       Button   deleteBtn = new Button("supprimer");
          ctn_verticale.add(deleteBtn);
      
              deleteBtn.addActionListener((evt) -> {
          if(Dialog.show("voulez vous supprimer ce commentaire?", "", "oui", "Non")) {
                   
               sc.deleteReponse(r.getId_rep());
                          
                }
    
           
      
  

      
      
       DetailQuestion dq=new DetailQuestion(id_q1);
    dq.getF().show();
          
       }  );
         
          
             Button editBtn = new Button("modifier");
      ctn_verticale.add(editBtn);
      
                  editBtn.addActionListener( (evt) -> {
int id_q= ListReponses.get(0).getId_quest();
     UpdateCommentaire updatepage=new UpdateCommentaire(id_q,r.getId_rep());
  updatepage.getF().show();
          
       }  );
       
           f.add(ctnr);
          f.add(ctn_verticale);
 
       f.getToolbar().addCommandToRightBar("retour", null, (ev)->{AffichageListPublication l=new AffichageListPublication(res);
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
