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
import com.codename1.ui.Button;
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
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author SkanderThabet
 */
public class settingsForm extends SideMenuBaseForm {

    static Map g;
    Form current;
    userService u = new userService();

    public settingsForm(Resources res) {
        // super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        User us = u.profile();
        System.out.println(us);
        String role = us.getRole();
        String name = us.getFirst_name();
        String lastname = us.getLast_name();
        String email = us.getEmail();
        String adresse= us.getAdresse();
        String phone = us.getPhone_number();
        String cin = us.getCin();
        String birthday = us.getBirthday();
        String genre = us.getGenre();

        String image = us.getPicture();
        EncodedImage placeholder = EncodedImage.createFromImage(res.getImage("Image6.png"), false);
        URLImage profilePic = URLImage.createToStorage(placeholder, "http://localhost/Java/SymbioseApi/Symbiose-WEB/Symbiose/web/uploads/images/" + image,
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

        Button editA = new Button(FontImage.MATERIAL_IMAGE, "Side");
        Container rt = new Container(BoxLayout.xRight());
        rt.add(editA);
        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.center(
                        BoxLayout.encloseY(
                                new Label(name + " " + lastname, "Title")
                        )
                ).add(BorderLayout.EAST, rt)
                        .add(BorderLayout.NORTH, profilePicLabel),
                GridLayout.encloseIn(2, remainingTasks, completedTasks)
        );

        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_EDIT);
        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
        fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));

        add(new Label("Profile", "TodayTitle"));
        editA.addActionListener((evt) -> {

            if (evt != null && evt.getSource() != null) {
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
                u.updateAvatar(fm);
                //new settingsForm(res).show();
                InfiniteProgress prog = new InfiniteProgress();
                Dialog dlg = prog.showInifiniteBlocking();
                cr.setDisposeOnCompletion(dlg);
                NetworkManager.getInstance().addToQueueAndWait(cr);
                revalidate();

            }

        });

        addButtonBottom("Name: " + name, 0x5ae29d, true);
        addButtonBottom("Lastname: " + lastname, 0x5ae29d, true);
        addButtonBottom("Email: " + email, 0x5ae29d, true);
        addButtonBottom("Genre: " + genre, 0x5ae29d, true);
        addButtonBottom("Role: " + role, 0x5ae29d, true);
        addButtonBottom("Adresse: " + adresse, 0x5ae29d, true);
        addButtonBottom("Cin : " + cin, 0x5ae29d, true);
        addButtonBottom("Phone: " + phone, 0x5ae29d, true);
        addButtonBottom("Birthday: " + birthday, 0x5ae29d, true);
        

        fab.addActionListener((evt) -> {
            new editProfileForm(res).show();
        });
        setupSideMenu(res);
    }

    private void addButtonBottom(String text, int color, boolean first) {
        MultiButton finishLandingPage = new MultiButton(text);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(), first));
        finishLandingPage.setIconUIID("Container");
        add(FlowLayout.encloseIn(finishLandingPage));
    }

    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if (first) {
            y = height / 6 + 1;
        }
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 4 - height / 2, height / 6, height / 4, height / 2, 0, 360);
        return img;
    }

}
