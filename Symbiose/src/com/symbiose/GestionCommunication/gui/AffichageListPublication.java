/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionCommunication.gui;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.symbiose.GestionCommunication.entities.Publication;
import com.symbiose.GestionCommunication.services.ServicePublication;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Map;
import java.lang.String;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author bhk
 */
public class AffichageListPublication {

    Form f;
    SpanLabel lb;
    Container ctn, ctn1;
    ArrayList<Publication>  listpub = new ArrayList<>();
private Resources theme;

  
    public AffichageListPublication(Resources res) {
                        theme = UIManager.initFirstTheme("/a");

           f = new Form("List des publications", new FlowLayout(Component.CENTER));
           
        f.getAllStyles().setBgColor(0xfff2e6);
        ctn = new Container(BoxLayout.y());
        ctn1 = new Container(BoxLayout.y());
        ctn.setScrollableY(true);
          ctn1.setScrollableY(true);
          TextField filter = new TextField();
          filter.addDataChangedListener((type, index) -> {
              ArrayList<Publication > filtred_questions = new ArrayList<>();
              for (Publication q : listpub){
                  if (q.getDescription().startsWith(filter.getText()))
                      filtred_questions.add(q);
              }
              ctn.removeAll();
              setQuestions(filtred_questions);
              
              f.revalidate();
          });
          ctn1.add(filter);
  
  
  
    
        ServicePublication Servicepublication=new ServicePublication();

   //   lb.setText(serviceQuestion.getListQuestion().toString());//hatina fl lb resultat mtaa lmethode getList2()
     
 
   listpub=Servicepublication.getListPub();
   setQuestions(listpub);

       f.add(ctn1);
         f.add(ctn);
          f.getToolbar().addCommandToRightBar("retour", null, (ev)->{Accueil h=new Accueil(res);
          h.getF().show();
          });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    private void setQuestions(ArrayList<Publication> listQuestions) {
    
       for (Publication q :listQuestions ){
     
          MultiButton mb= new MultiButton(q.getDescription());
             
           
           mb.setTextLine2(q.getUsername_question());
           mb.getAllStyles().setPaddingRight(0);
                      mb.getAllStyles().setPaddingLeft(0);

   /*
       String date =q.getDate_question() ;
    String delimiter = "T";
    StringTokenizer fruitsTokenizer = new StringTokenizer(date, delimiter);
    while (fruitsTokenizer.hasMoreTokens()) {
            String newdate = fruitsTokenizer.toString();
         
    }
       */   
          
  /*String date= q.getDate_question().substring(0, 10);
  String time= q.getDate_question().substring(11, 19);
       
     mb.setTextLine4(date+"/"+time);
*/
     
        //methode oncklick aal element de liste
       mb.addActionListener( (evt) -> {
           
           varGlobales.setId(q.getId());
          int id_q= q.getId();
             DetailQuestion dq=new DetailQuestion(id_q);
        dq.getF().show();
          
       }
       
       );
        ctn.add(mb);
       Button btn_comment = new Button("commenter");
         ctn.add(btn_comment);
    
         
        btn_comment.addActionListener( (evt) -> {
           int id_q= q.getId();
            System.out.println(id_q);
       AjoutCommentaire ajtCm=new AjoutCommentaire(id_q);
    ajtCm.getF().show();
  
          
       }  );
        
       }
    }

    
    
}
