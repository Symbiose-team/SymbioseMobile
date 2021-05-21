/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionCommunication.gui;

import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.symbiose.GestionCommunication.entities.Commentaire;
import com.symbiose.GestionUsers.entities.User;
import com.symbiose.GestionCommunication.gui.Accueil;
import com.symbiose.GestionCommunication.services.ServicePublication;
import com.symbiose.GestionCommunication.services.ServiceCommentaire;
import com.symbiose.Utils.Session;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ch
 */
public class AjoutCommentaire {

    Form f;

    TextField tdesc;
    Button btnajout;
    User u = new User();
    private Resources res;

    public AjoutCommentaire(int id_q) {
        
        f = new Form("page d'ajout de commentaire");
        tdesc = new TextField("", "description");
        btnajout = new Button("commenter");
        f.add(tdesc);
        f.add(btnajout);
        f.getToolbar().addCommandToRightBar("Back", null, (ActionListener) ((evt) -> {
            Accueil h = new Accueil(res);
            h.getF().showBack();
        }));
        btnajout.addActionListener((e) -> {
            ServiceCommentaire ser = new ServiceCommentaire();
            ///hethi matekhdemsh ne9sa user id

            //methode eli bch ttajouti
            Commentaire rep = new Commentaire(tdesc.getText(), id_q, 1);
            if (Dialog.show("vous voulez ajouter ce commentaire?", "", "oui", "Non")) {
                ser.ajoutReponse(rep);
                Message m = new Message("votre commentaire à été ajouté avec succés");

                Display.getInstance().sendMessage(new String[]{Session.u.getEmail()}, "Alert", m);

            }

            DetailQuestion dq = new DetailQuestion(id_q);
            dq.getF().show();
            

        });
        f.getToolbar().addCommandToRightBar("Back", null, (ActionListener) ((evt) -> {
            Accueil h = new Accueil(res);
            h.getF().show();
        }));

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

    private User User() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
