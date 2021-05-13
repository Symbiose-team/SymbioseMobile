/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.Utils;

import com.symbiose.GestionUsers.entities.User;
import com.codename1.io.Preferences;
import com.codename1.io.Storage;
import java.util.Map;

/**
 *
 * @author SkanderThabet
 */
public final class Session {
    public static final String BASE_URL="http://127.0.0.1:8000/";
    public static User u =new User() ;
    Preferences p ;
    
    private static Session instance;

   
 
    public static Session getInstace() {
            instance = new Session();     
        return instance;
    }



    public Session() {
                   Map f = (Map) Storage.getInstance().readObject("session");

                 float id = Float.parseFloat(f.get("id").toString());
             u.setId((int) id);           
             u.setFirst_name(f.get("firstName").toString());
             u.setLast_name(f.get("lastName").toString());
             u.setEmail(f.get("Email").toString());
             u.setPicture(f.get("picture").toString());
             u.setRole(f.get("role").toString());
             u.setGenre(f.get("genre").toString());
             u.setAdresse(f.get("Adresse").toString());
             u.setRegistration_token(f.get("registrationToken").toString());
             p.set("user", u.getId());

             System.out.println(u.getId());
             System.out.println(u.getPicture());

    }
    
    
}
