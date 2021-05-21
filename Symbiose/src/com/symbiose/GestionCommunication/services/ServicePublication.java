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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServicePublication {

    public void ajoutQuestion(Publication pub) {
     
            ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
            String Url ="http://localhost:8000/Addpub/" + pub.getDescription() +"/";// création de l'URL
            
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
 
    public ArrayList<Publication> parseListpublicationJson(String json) {

        ArrayList<Publication>  listpublication = new ArrayList<>();

   try {
            JSONParser j = new JSONParser();

          Map<String, Object>  Publications = j.parseJSON(new CharArrayReader(json.toCharArray()));
           System.out.println(Publications);            
            
       List<Map<String, Object>> list = (List<Map<String, Object>>) Publications.get("publications");
       System.out.print("*****"+list);

       for (Map<String, Object> o : list){
           Publication pub = new Publication();
           String a=(String) o.get("id");
pub.setId(Integer.valueOf(a));
         
         pub.setDescription((String)o.get("contenu"));
                      System.out.print("///////"+pub);

           listpub.add(pub);
           System.out.print("*****"+listpub);
       }
       
       
      } catch (IOException ex) {
       }
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return listpub;

    }
    
    
    ArrayList<Publication>  listpub = new ArrayList<>();
    
    
    //hedhy la methode callRequest eli fl tuto
   
    public ArrayList<Publication> getListPub(){       
        ConnectionRequest con = new ConnectionRequest();//Appel au service web (demande de connexion).
   con.setUrl("http://localhost:8000/GetPub");        

        con.addResponseListener(new ActionListener<NetworkEvent>() {
           
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServicePublication ser = new ServicePublication();
             listpub = ser.parseListpublicationJson(new String(con.getResponseData()));
                System.out.println(listpub);//Récupération de la réponse du serveur
            }

        });

        NetworkManager.getInstance().addToQueueAndWait(con);

        return  listpub;
    }

}
