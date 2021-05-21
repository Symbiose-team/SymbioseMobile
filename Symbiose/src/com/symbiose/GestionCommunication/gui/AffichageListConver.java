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
import com.symbiose.GestionCommunication.entities.Conversation;
import com.symbiose.GestionUsers.entities.User;
import com.symbiose.GestionCommunication.services.ServiceConversation;
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
public class AffichageListConver {

    Form f;
    SpanLabel lb;
    Container ctn, ctn1;
    ArrayList<Conversation> listpub = new ArrayList<>();
    ArrayList<User> listuser = new ArrayList<>();
    private Resources theme;
    private Resources res;

    public AffichageListConver(Resources res) {
                        theme = UIManager.initFirstTheme("/theme");

        f = new Form("List des convers", new FlowLayout(Component.CENTER));

        f.getAllStyles().setBgColor(0xfff2e6);
        ctn = new Container(BoxLayout.y());
        ctn1 = new Container(BoxLayout.y());
        ctn.setScrollableY(true);
        ctn1.setScrollableY(true);
        TextField filter = new TextField();
        filter.addDataChangedListener((type, index) -> {
            ArrayList<Conversation> filtred_questions = new ArrayList<>();
            for (Conversation q : listpub) {
                if (q.getId() != 0) {
                    filtred_questions.add(q);
                }
            }
            ctn.removeAll();
            setQuestions(filtred_questions);

            f.revalidate();
        });
        ctn1.add(filter);

        ServiceConversation sc = new ServiceConversation();
        //   lb.setText(serviceQuestion.getListQuestion().toString());//hatina fl lb resultat mtaa lmethode getList2()

        listpub = sc.getListPub();
        setQuestions(listpub);

        f.add(ctn1);
        f.add(ctn);
        f.getToolbar().addCommandToRightBar("retour", null, (ev) -> {
            Accueil h = new Accueil(res);
            h.getF().show();
        });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    private void setQuestions(ArrayList<Conversation> listQuestions) {

        for (Conversation q : listQuestions) {

            MultiButton mb = new MultiButton((Integer.toString(q.getUser1_id())));

            final FontImage placeholderImage = FontImage.createMaterial(FontImage.MATERIAL_PERSON, "label", 6);
            ServiceConversation sc = new ServiceConversation();
            String a = null;
            mb.setIcon(placeholderImage);
            mb.setTextLine1(q.getEmail());

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
            mb.addActionListener((evt) -> {

                varGlobales.setId(q.getId());

                DetailConver dq = new DetailConver(res);
                dq.getF().show();

            }
            );
            ctn.add(mb);
            Button btn_comment = new Button("Envoyer un message");
            ctn.add(btn_comment);

            btn_comment.addActionListener((evt) -> {
                int id_q = q.getId();
                System.out.println(id_q);
                AjoutMessage ajtCm = new AjoutMessage(id_q);
                ajtCm.getF().show();

            });

        }
    }

}
