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
import com.symbiose.GestionCommunication.entities.Publication;
import com.symbiose.GestionCommunication.entities.Commentaire;
import com.symbiose.GestionCommunication.entities.Message;
import com.symbiose.GestionCommunication.gui.varGlobales;
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
public class ServiceMessage {
    
    
    User u = new User();
        public void ajoutMessage(Message msg) {
     
            ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
            String Url ="http://localhost:8000/AjoutMessageM/" + msg.getConversation_id()+ "/" + Session.u.getId()+ "/" + msg.getContenu();// création de l'URL
          
            con.setUrl(Url);// Insertion de l'URL de notre demande de connexion     
            NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
            String data = new String(con.getResponseData());
            JSONParser j = new JSONParser();
       
        
    }
    
        
      
    
    
    
   
       public void deleteMessage(int id_rep) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
     String Url ="http://localhost:8000/deletemessageM/" +id_rep+"/";

    
       con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
  
    
    
    
      public ArrayList<Message> parseDetailReponseJson(String json) {

        ArrayList<Message>  listmsg= new ArrayList<>();

   try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParsr permettant le parsing du résultat jsone

          Map<String, Object>  messages = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
            
       List<Map<String, Object>> list = (List<Map<String, Object>>) messages.get("comments");
       

       for (Map<String, Object> repObj : list){
           Message msg = new Message();
           
           //ajouter l'objet reponse
          String a=(String) repObj.get("id");
msg.setId(Integer.valueOf(a));
             String b=(String) repObj.get("conversation_id");
             String c=(String) repObj.get("user_id");

           msg.setConversation_id(Integer.valueOf(b));
           msg.setUser_id(Integer.valueOf(c));
           msg.setContenu((String)repObj.get("contenu"));
       //ajouter l'objet question
            /* Map<String ,Object > question = (Map)repObj.get("question");
               reponse.setId_quest((int)((double)question.get("id")));
                reponse.setContenuPub((String)question.get("titreQuestion"));
                reponse.setDateQuestion((String)question.get("dateQuestion"));
             Map<String ,Object > user = (Map)question.get("user"); */
          

           
           listmsg.add(msg);
       }
       
       
      } catch (IOException ex) {
       }
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return listmsg;

    }
    
    
    
    
    

    
    
  ArrayList<Message>  listDetailMessages = new ArrayList<>();
    

    public ArrayList<Message> getDetailMessage(){       
        ConnectionRequest con = new ConnectionRequest();//Appel au service web (demande de connexion).
   con.setUrl("http://localhost:8000/affichemsgMobile/"+varGlobales.getId()+"/");        

        con.addResponseListener(new ActionListener<NetworkEvent>() {
           
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceMessage ser = new ServiceMessage();
 listDetailMessages = ser.parseDetailReponseJson(new String(con.getResponseData()));//Récupération de la réponse du serveur
                System.out.println("taw bch nchoufou liste message");
                    System.out.println(listDetailMessages);
            }

        });

        NetworkManager.getInstance().addToQueueAndWait(con);

        return  listDetailMessages;
    
    }
    
    
  
   

}
