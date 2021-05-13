/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionUsers.gui;

import com.symbiose.GestionUsers.entities.User;
import com.symbiose.GestionUsers.services.userService;
import static com.symbiose.GestionUsers.services.userService.getResponse;
import com.symbiose.Utils.Session;
import com.symbiose.Utils.SideMenuBaseForm;
import com.codename1.capture.Capture;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author SkanderThabet
 */
public class editProfileForm extends SideMenuBaseForm {

    static Map g;
    Form current;
    userService u = new userService();

    public editProfileForm(Resources res) {
        // super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        User us = u.profile();

        
        String name = us.getFirst_name();
        String lastname = us.getLast_name();
//        String password = us.getHash();
        String image = us.getPicture();
        String email = us.getEmail();
        String role = us.getRole();
        String genre = us.getGenre();
        String adresse = us.getAdresse();
        String cin= us.getCin();
        String phone=us.getPhone_number();
        String birthday = us.getBirthday();
   

        System.out.println(image);
        EncodedImage placeholder = EncodedImage.createFromImage(res.getImage("Image6.png"), false);
        Image profilePic = URLImage.createToStorage(placeholder, "http://localhost/Java/SymbioseApi/Symbiose-WEB/Symbiose/web/uploads/images/" + image,
                "http://localhost/Java/SymbioseApi/Symbiose-WEB/Symbiose/web/uploads/images/" + image);

        ImageViewer img = new ImageViewer(profilePic);
        Image mask = res.getImage("round-mask.png");
        //profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
        profilePicLabel.setMask(mask.createMask());

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container remainingTasks = BoxLayout.encloseY(
                new Label("12", "CenterTitle"),
                new Label("Produits", "CenterSubTitle")
        );
        remainingTasks.setUIID("RemainingTasks");
        Container completedTasks = BoxLayout.encloseY(
                new Label("32", "CenterTitle"),
                new Label("Teams", "CenterSubTitle")
        );
        completedTasks.setUIID("CompletedTasks");

        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label(name + " " + lastname, "Title"),
                                new Label(role, "SubTitle")
                        )
                ).add(BorderLayout.WEST, profilePicLabel),
                GridLayout.encloseIn(2, remainingTasks, completedTasks)
        );

        add(new Label("Edit Profile", "TodayTitle"));

        TextField Name = new TextField(name, "Name");
        TextField LastName = new TextField(lastname, "Lastname");
        //TextField Role = new TextField(role, "Role");
        TextField Email = new TextField(email, "Email");
        //TextField Genre = new TextField(genre, "Genre");
        TextField Cin = new TextField(cin,"C.I.N");
        TextField Phone = new TextField(phone,"Phone");
        TextField Adresse = new TextField(adresse,"Adresse");
        Picker Birthday = new Picker();
        ComboBox Genre = new ComboBox<>();
        String[] lesTYpes = {"Homme", "Femme"};
        for (int i = 0; i < 2; i++) {
            Genre.addItem(lesTYpes[i]);}
        ComboBox Role = new ComboBox<>();
        String[] lesroles = {"Client", "Fournisseur"};
        for (int i = 0; i < 2; i++) {
            Role.addItem(lesroles[i]);}
//        Birthday.setDate(new Date());
      

        Button btnValider = new Button("Save ");
        Button editPass = new Button("Reset Password");
        btnValider.setUIID("LoginButton");
//        editPass.setUIID("ActionButton");

        btnValider.addActionListener((e) -> {
            User user = new User(Session.u.getId(), Name.getText(), LastName.getText(),Email.getText(),Role.getSelectedItem().toString(),Genre.getSelectedItem().toString(),Adresse.getText(),Phone.getText(),Cin.getText(),new SimpleDateFormat("yyyy-MM-dd").format(Birthday.getDate()));
            if (u.update(user)) {
                new settingsForm(res).show();
            } else {
                System.out.println("error update");
            }
        });

//        editPass.addActionListener((evt) -> {
//            new resetPassword(res).show();
//        });

        add(FlowLayout.encloseCenterMiddle(BoxLayout.encloseY(Name, LastName,Email,Role,Genre,Adresse,Cin,Phone,Birthday, btnValider)));
        getToolbar().setTitleComponent(titleCmp);

        setupSideMenu(res);
    }

}
