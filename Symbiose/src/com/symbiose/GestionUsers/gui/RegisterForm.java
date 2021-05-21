/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionUsers.gui;

import com.symbiose.GestionUsers.entities.User;
import com.symbiose.GestionUsers.services.userService;
import static com.symbiose.GestionUsers.services.userService.getResponse;
import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.symbiose.Utils.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author SkanderThabet
 */
public class RegisterForm extends Form {

    static Map g;
    Form current;
    userService u = new userService();
   
    

    public RegisterForm(Resources theme) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        Container c = FlowLayout.encloseCenter(new ImageViewer(theme.getImage("symbiose-logo.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth() / 4, Display.getInstance().getDisplayHeight() / 10)));

        Container welcome = BoxLayout.encloseYCenter(
                c,
                new Label("   Welcome Symbiose, ", "WelcomeWhite")
        );
        current = this;

        getTitleArea().setUIID("Container");

        TextField name = new TextField("", "Name", 20, TextField.ANY);
        TextField lastname = new TextField("", "Lastname", 20, TextField.ANY);
        TextField email = new TextField("", "Email", 20, TextField.EMAILADDR);
        Label path = new Label("Choose avatar                       ");
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        TextField password2 = new TextField("", "Confirm Password", 20, TextField.PASSWORD);
        ComboBox genre = new ComboBox<>();
        String[] lesTYpes = {"Homme", "Femme"};
        for (int i = 0; i < 2; i++) {
            genre.addItem(lesTYpes[i]);}
        ComboBox role = new ComboBox<>();
        String[] lesroles = {"Client", "Fournisseur"};
        for (int i = 0; i < 2; i++) {
            role.addItem(lesroles[i]);}
        Picker birthday = new Picker();
        TextField adresse = new TextField("", "Adresse", 20, TextField.ANY);
        TextField phone = new TextField("", "Phone", 20, TextField.ANY);
        TextField cin = new TextField("", "C.I.N", 20, TextField.ANY);
        

        name.setUIID("Field");
        lastname.setUIID("Field");
        email.setUIID("Field");
        adresse.setUIID("Field");
        phone.setUIID("Field");
        password.setUIID("Field");
        password2.setUIID("Field");
        cin.setUIID("Field");
        birthday.setDate(new Date());
        String datestring=(new SimpleDateFormat("yyyy-MM-dd")).format(birthday.getDate());
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
//        String strDate= formatter.format(birthday);  
//        System.out.println(strDate);

        Label error = new Label();

        Button registerButton = new Button("REGISTER");
        registerButton.setUIID("LoginButton");
        registerButton.addActionListener(e -> {
            if (name.getText().isEmpty() || lastname.getText().isEmpty() || adresse.getText().isEmpty() || email.getText().isEmpty() || password.getText().isEmpty() || password2.getText().isEmpty() || phone.getText().isEmpty() || adresse.getText().isEmpty() || cin.getText().isEmpty()) {
                error.setText("Empty fields , please fill all fields");
            } else {
                if (password.getText().equals(password2.getText())) {
                    User use = new User(name.getText(), lastname.getText(), password.getText(), email.getText(), path.getText(), role.getSelectedItem().toString(), genre.getSelectedItem().toString(), adresse.getText(), phone.getText(),cin.getText(),new SimpleDateFormat("yyyy-MM-dd").format(birthday.getDate()));
                    System.out.println(datestring);
                    int result = u.registering(use);
                    System.out.println(result);
                    
                    if (result != 0) {
                        new confirmForm(theme).show();
//                        Boolean can = Display.getInstance().canExecute("http://127.0.0.1:8000/account/"+result);
//                        if (can != null && can) {
//                            Display.getInstance().execute("http://127.0.0.1:8000/account/"+result);
//                            Map m = getResponse("account/register/done/"+result);
//
//                            ArrayList d = (ArrayList) m.get("root");

                            // String n = d.get(0).toString();
//                            System.out.println(d);
//                            if (d.equals("true")) {
//                                new LoginForm(theme).show();
//                            } else {
//                                new confirmForm(theme).show();
//                            }
//                        } else {
//                            Display.getInstance().execute("http://http://127.0.0.1:8000/");
//                        }
                    } else {
                        error.setText("verify fields");
                    }
                } else {
                    error.setText("Passwords not identical");

                }
            }

        });
        Button haveAccount = new Button("LOG IN");
        Container log = new Container(BoxLayout.xCenter());
        log.add(haveAccount);
        haveAccount.setUIID("Label");

        // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if (!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }
        Button ava = new Button();
        //ava.setUIID("ActionButton");
        Button ch = new Button();
        //ch.setUIID("ActionButton");
        FontImage.setMaterialIcon(ava, FontImage.MATERIAL_PICTURE_IN_PICTURE);

        Container bu = new Container(BoxLayout.xRight());
        bu.add(path);
        bu.add(ch);
        bu.add(ava);
        Container by = BoxLayout.encloseY(
                welcome,
                // profilePicLabel,
                spaceLabel,
                BorderLayout.center(name),
                BorderLayout.center(lastname),
                BorderLayout.center(email),
                BorderLayout.center(password),
                BorderLayout.center(password2),
                BorderLayout.center(genre),
                BorderLayout.center(role),
                BorderLayout.center(adresse),
                BorderLayout.center(cin),
                BorderLayout.center(phone),
                BorderLayout.center(birthday),
                BorderLayout.center(bu),
                error,
                registerButton,
                BorderLayout.center(log)
        );
        add(BorderLayout.CENTER, by);
        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);

        haveAccount.addActionListener((evt) -> {
            new LoginForm(theme).show();
        });

        FontImage.setMaterialIcon(ch, FontImage.MATERIAL_CAMERA);

        //choose image from gallery
        ava.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Display.getInstance().openGallery(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e != null && e.getSource() != null) {
//                            
                            MultipartRequest cr = new MultipartRequest();
                            String filePath = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
                            String url = "http://localhost/Java/SymbioseApi/Symbiose-WEB/Symbiose/web/s.php";

                            cr.setUrl(url);
                            cr.setPost(true);
                            String mime = "image/png";
                            try {
                                cr.addData("file", filePath, mime);
                            } catch (IOException ex) {
                                System.out.println("error");
                            }

                            int r = new Random(63546).hashCode();
                            String fm = new String("av-" + r + ".png");
                            cr.setFilename("file", fm);//any unique name you want
                            path.setText(fm);
                            InfiniteProgress prog = new InfiniteProgress();
                            Dialog dlg = prog.showInifiniteBlocking();
                            cr.setDisposeOnCompletion(dlg);
                            NetworkManager.getInstance().addToQueueAndWait(cr);

                        }
                    }
                }, Display.GALLERY_IMAGE);
            }
        });
        //capture image
        ch.addActionListener((evt) -> {

            if (evt != null && evt.getSource() != null) {
//                            
                MultipartRequest cr = new MultipartRequest();
                String filePath = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
                String url = "http://localhost/Java/SymbioseApi/Symbiose-WEB/Symbiose/web/s.php";
//                if (filePath != null) {
//                    try {
//                        Util.copy(FileSystemStorage.getInstance().openInputStream(filePath), Storage.getInstance().createOutputStream("C"));
//                    } catch (IOException ex) {
//                        Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
                cr.setUrl(url);
                cr.setPost(true);
                String mime = "image/png";
                try {
                    cr.addData("file", filePath, mime);
                } catch (IOException ex) {
                    System.out.println("error");
                }

                int r = new Random(63546).hashCode();
                String fm = new String("av-" + r + ".png");
                cr.setFilename("file", fm);//any unique name you want
                path.setText(fm);
                InfiniteProgress prog = new InfiniteProgress();
                Dialog dlg = prog.showInifiniteBlocking();
                cr.setDisposeOnCompletion(dlg);
                NetworkManager.getInstance().addToQueueAndWait(cr);

            }

        });

    }
}
