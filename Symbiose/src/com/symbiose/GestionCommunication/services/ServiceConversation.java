/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionCommunication.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.symbiose.GestionCommunication.entities.Conversation;
import com.symbiose.GestionCommunication.entities.Publication;
import com.symbiose.GestionUsers.entities.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.symbiose.Utils.Session;

/**
 *
 * @author bhk
 */
public class ServiceConversation {
    User u = new User();
    public void ajoutConver(Conversation conv) {
     
            ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
            String Url ="http://localhost:8000/AddConversation/" + Session.u.getId() + "/"+conv.getUser2_id();// création de l'URL
            
            con.setUrl(Url);// Insertion de l'URL de notre demande de connexion     
            NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
            String data = new String(con.getResponseData());
            JSONParser j = new JSONParser();
            /*
        try {
            System.out.println(j.parseJSON(new CharArrayReader(data.toCharArray())));
        } catch (IOException ex) {
        
        }
            */
    }

   
     
      
    //C’est la méthode qui fait le parsing pour retourner une liste de Question
  public ArrayList<Conversation> parseListconversationJson(String json) {

        ArrayList<Conversation>  listconver = new ArrayList<>();

   try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParsr permettant le parsing du résultat jsone

          Map<String, Object>  Conversations = j.parseJSON(new CharArrayReader(json.toCharArray()));
           System.out.println(Conversations);            
            
       List<Map<String, Object>> list = (List<Map<String, Object>>) Conversations.get("conversations");
       

       for (Map<String, Object> o : list){
           Conversation conv = new Conversation();
           String a=(String) o.get("id");
           
           conv.setId(Integer.valueOf(a));
                      String b=(String) o.get("user1_id");
 conv.setUser1_id(Integer.valueOf(b));
  String c=(String) o.get("user2_id");
   conv.setUser1_id(Integer.valueOf(c));
     String email=(String) o.get("email");
     conv.setEmail(email);
   

         
         
           
           listconver.add(conv);
       }
       
       
      } catch (IOException ex) {
       }
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return listconver;

    }
    public ArrayList<User> parseListuserJson(String json) {

        ArrayList<User>  listuser = new ArrayList<>();

   try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParsr permettant le parsing du résultat jsone

          Map<String, Object>  users = j.parseJSON(new CharArrayReader(json.toCharArray()));
           System.out.println(users);            
            
       List<Map<String, Object>> list = (List<Map<String, Object>>) users.get("names");
       

       for (Map<String, Object> o : list){
           User u=new User();
           Conversation conv = new Conversation();
           String a=(String) o.get("email");
           u.setEmail(a);
                   

         
         
           
           listuser.add(u);
       }
       
       
      } catch (IOException ex) {
       }
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return listuser;

    }
    String name=null;
    
    ArrayList<Conversation>  listconver = new ArrayList<>();
    
    
    //hedhy la methode callRequest eli fl tuto
   
    public ArrayList<Conversation> getListPub(){       
        ConnectionRequest con = new ConnectionRequest();//Appel au service web (demande de connexion).
   con.setUrl("http://localhost:8000/GetConv");        

        con.addResponseListener(new ActionListener<NetworkEvent>() {
           
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceConversation ser = new ServiceConversation();
             listconver = ser.parseListconversationJson(new String(con.getResponseData()));//Récupération de la réponse du serveur
            }

        });

        NetworkManager.getInstance().addToQueueAndWait(con);

        return  listconver;
    }
              ArrayList<User>  listuser = new ArrayList<>();

      public ArrayList<User>  getname(int id){    
        ConnectionRequest con = new ConnectionRequest();//Appel au service web (demande de connexion).
   con.setUrl("http://localhost:8000/Getname/"+id+"");        

        con.addResponseListener(new ActionListener<NetworkEvent>() {
           
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceConversation ser = new ServiceConversation();
             listuser = ser.parseListuserJson(new String(con.getResponseData()));//Récupération de la réponse du serveur
            }

        });

        NetworkManager.getInstance().addToQueueAndWait(con);

        return  listuser;
    }


}
