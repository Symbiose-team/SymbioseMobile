/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionUsers.services;

import com.symbiose.GestionUsers.entities.User;
import com.symbiose.Utils.Session;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.URLImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author SkanderThabet
 */
public class userService {

    static Map g;

    public static Map<String, Object> getResponse(String url) {
        url = "http://127.0.0.1:8000/" + url;
        System.out.println(url);
        ConnectionRequest r = new ConnectionRequest();
        r.setUrl(url);
        r.setPost(false);
        InfiniteProgress prog = new InfiniteProgress();
        Dialog dlg = prog.showInfiniteBlocking();
        r.setDisposeOnCompletion(dlg);
        r.addResponseListener((evt) -> {
            try {
                JSONParser p = new JSONParser();
                Reader targetReader = new InputStreamReader(new ByteArrayInputStream(r.getResponseData()));
                g = p.parseJSON(targetReader);

            } catch (IOException ex) {
                //Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(r);
        return g;
    }

    public boolean connecting(String login, String password) {

        Map m = getResponse("LoginJSON/?email=" + login + "&hash=" + password);

        ArrayList d = (ArrayList) m.get("root");

        String n = d.get(0).toString();
        System.out.println(n);
        if (n.equals("false")) {
            return false;
        } else {
            Object data = d.get(0);

            System.out.println(data);
            Storage session = new Storage();
            session.writeObject("session", data);
            Map f = (Map) Storage.getInstance().readObject("session");
            float id = Float.parseFloat(f.get("id").toString());
            Session s = new Session();

            System.out.println((Map) Storage.getInstance().readObject("session"));
            return true;

        }
    }

    public int registering(User u) {
        Map m= getResponse("RegistrationJSON/?first_name="+u.getFirst_name() +"&last_name="+u.getLast_name()+"&genre="+u.getGenre()+"&role="+u.getRole()+"&picture="+u.getPicture()+"&adresse="+u.getAdresse()+"&hash="+u.getHash()+"&email="+u.getEmail()+"&cin="+u.getCin()+"&phone_number="+u.getPhone_number()+"&birthday="+u.getBirthday());
        ArrayList d = (ArrayList) m.get("root");
        System.out.println(d);
        Map n = (Map) d.get(0);
        System.out.println(n);
        if (n.equals("false")) {
            return 0;
        } else {

            int id = (int) Float.parseFloat(n.get("id").toString());

            return id;

        }
    }

    public Boolean update(User u) {
        //String p = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt(13));
        Map m = getResponse("UpdateUserJSON/" + Session.u.getId() + "?first_name=" + u.getFirst_name() + "&last_name="+u.getLast_name()+"&email="+u.getEmail()+"&role="+u.getRole()+"&genre="+u.getGenre()+"&adresse="+u.getAdresse()+"&cin="+u.getCin()+"&phone_number="+u.getPhone_number()+"&birthday="+u.getBirthday());
        ArrayList d = (ArrayList) m.get("root");
        System.out.println(d);
        Map n = (Map) d.get(0);
        System.out.println(n);
        if (n.equals("false")) {
            return false;
        } else {

            int id = (int) Float.parseFloat(n.get("id").toString());

            return true;

        }
    }
 public Boolean updateAvatar(String img) {
        //String p = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt(13));
        Map m = getResponse("UpdateUserPicJSON/" + Session.u.getId() + "?picture=" + img );
        ArrayList d = (ArrayList) m.get("root");
        System.out.println(d);
        Map n = (Map) d.get(0);
        System.out.println(n);
        if (n.equals("false")) {
            return false;
        } else {

            int id = (int) Float.parseFloat(n.get("id").toString());

            return true;

        }
    }
    public User profile() {
        User u = new User();
        Map m = getResponse("UserJSON/"+Session.u.getId());
//        +Session.u.getId()
        ArrayList d = (ArrayList) m.get("root");
        System.out.println(d);
        Map n = (Map) d.get(0);
        System.out.println(n);
        if (n.equals("false")) {
            return null;
        } else {

            int id = (int) Float.parseFloat(n.get("id").toString());
            u.setId(id);
            u.setFirst_name(n.get("firstName").toString());
            u.setLast_name(n.get("lastName").toString());
            u.setEmail(n.get("Email").toString());
            u.setPicture(n.get("picture").toString());
            u.setAdresse(n.get("Adresse").toString());
            u.setCin(n.get("Cin").toString());
            u.setRole(n.get("role").toString());
            u.setGenre(n.get("genre").toString());
            u.setBirthday(n.get("dob").toString());
            u.setPhone_number(n.get("phoneNumber").toString());
            return u;

        }
    }

}
