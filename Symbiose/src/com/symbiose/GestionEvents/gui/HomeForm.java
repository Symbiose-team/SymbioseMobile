/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionEvents.gui;
import com.symbiose.Utils.SideMenuBaseForm;
import com.codename1.capture.Capture;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.symbiose.GestionEvents.gui.charts.ChartDemosForm;
import com.symbiose.GestionEvents.gui.charts.ChartsDemo;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
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
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.symbiose.GestionUsers.entities.User;
import com.symbiose.GestionUsers.gui.Dashboard;
import com.symbiose.GestionUsers.gui.LoginForm;
import com.symbiose.GestionUsers.gui.editProfileForm;
import com.symbiose.GestionUsers.services.userService;
import com.symbiose.Utils.Session;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Mahdi
 */
public class HomeForm extends Form{
    
    static Map g;
    Form current;

    public HomeForm(Resources res){
        
        current=this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        Toolbar.setGlobalToolbar(true);

        
        Button btnAddEvent = new Button("Add event");
        Button btnListEvents = new Button("List events");
        Button btnList2Events = new Button("List 2 events");
        Button btnDeleteEvents = new Button("Delete events");
        Button btnDashboard = new Button("Go back Dashboard");
        Button btnDetails = new Button("Details event");
        Button btnUpdate = new Button("Update event");
        Button ChartsApi = new Button("Charts API");


        
        btnAddEvent.addActionListener(e-> new AddEventForm(current).show());
        btnList2Events.addActionListener(e-> new List2EventForm(current).show());
        btnDashboard.addActionListener(e-> new Dashboard(res).show());
        ChartsApi.addActionListener(e-> new ChartDemosForm().show());
        addAll(btnAddEvent,btnList2Events,ChartsApi,btnDashboard);
        
        
    }
}
