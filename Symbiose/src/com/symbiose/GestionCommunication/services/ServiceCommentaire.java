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
import com.symbiose.GestionCommunication.gui.varGlobales;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceCommentaire { 
    
        ArrayList<Commentaire>  listc = new ArrayList<>();

    
        public void ajoutReponse(Commentaire rep) {
     
            ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
            String Url ="http://localhost:8000/AddComment/" + rep.getContenu()+ "/" + rep.getUser_id()+"/"+ rep.getId_quest();// création de l'URL
          
            con.setUrl(Url);// Insertion de l'URL de notre demande de connexion     
            NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
            String data = new String(con.getResponseData());
            JSONParser j = new JSONParser();
      
    }
    
         public ArrayList<Commentaire> getlistc(){       
 ConnectionRequest con = new ConnectionRequest();//Appel au service web (demande de connexion).
   con.setUrl("http://localhost:8000/affichecomMobile/"+varGlobales.getId()+"/");         

        con.addResponseListener(new ActionListener<NetworkEvent>() {
           
             @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceCommentaire ser = new ServiceCommentaire();
 listc = ser.parseDetailReponseJson(new String(con.getResponseData()));//Récupération de la réponse du serveur
                System.out.println("taw bch nchoufou liste message");
                    System.out.println(listc);
            }

        });

        NetworkManager.getInstance().addToQueueAndWait(con);

        return  listc;
    }

            public void updateReponse(Commentaire rep) {
     
            ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
            con.setUrl("http://localhost:8000/updateCommentaireM/"+rep.getId_rep()+"/"+rep.getContenu());// Insertion de l'URL de notre demande de connexion     
            NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
            String data = new String(con.getResponseData());
            JSONParser j = new JSONParser();
       
    }
    
    
    
    
   
       public void deleteReponse(int id_rep) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
     String Url ="http://localhost:8000/removeCommentMobile/" +id_rep+"/";

    
       con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
  
    
    
    
      public ArrayList<Commentaire> parseDetailReponseJson(String json) {

        ArrayList<Commentaire>  listReponses= new ArrayList<>();

   try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParsr permettant le parsing du résultat jsone

          Map<String, Object>  Reponses = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
            
       List<Map<String, Object>> list = (List<Map<String, Object>>) Reponses.get("comments");
       

       for (Map<String, Object> repObj : list){
           Commentaire reponse = new Commentaire();
                     String a=(String) repObj.get("id");

           //ajouter l'objet reponse
           reponse.setId_rep(Integer.valueOf(a));                 
           reponse.setContenu((String)repObj.get("contenu"));
           reponse.setDateQuestion((String) repObj.get("date"));
       //ajouter l'objet question
         //  reponse.setId_quest((repObj.get("publication_id")));
            


           
           listReponses.add(reponse);
       }
       
       
      } catch (IOException ex) {
       }
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return listReponses;

    }
    
    
    
    
    

    
    
  ArrayList<Commentaire>  listDetailReponses = new ArrayList<>();
    

    public ArrayList<Commentaire> getDetailQuestion(){       
        ConnectionRequest con = new ConnectionRequest();//Appel au service web (demande de connexion).
   con.setUrl("http://localhost:8000/affichecomMobile/"+varGlobales.getId()+"/");        

        con.addResponseListener(new ActionListener<NetworkEvent>() {
           
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceCommentaire ser = new ServiceCommentaire();
 listDetailReponses = ser.parseDetailReponseJson(new String(con.getResponseData()));//Récupération de la réponse du serveur
                System.out.println("taw bch nchoufou liste commentaire");
                    System.out.println(listDetailReponses);
            }

        });

        NetworkManager.getInstance().addToQueueAndWait(con);

        return  listDetailReponses;
    
    }
    
    
  
   

}
