/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionFields.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.contacts.Contact;
import com.codename1.contacts.ContactsManager;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.location.Geofence;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.messaging.Message;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.symbiose.GestionFields.gui.GeofenceListenerImpl;

import com.symbiose.GestionFields.entities.field;
import com.symbiose.Utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author bhk
 */
public class ServiceField {

    public ArrayList<field> fields;
    
    public static ServiceField instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceField() {
         req = new ConnectionRequest();
    }

    public static ServiceField getInstance() {
        if (instance == null) {
            instance = new ServiceField();
        }
        return instance;
    }
     ArrayList<field>  listc = new ArrayList<>();

    
      
    public boolean addTask(field t) {
        String url = "http://localhost:8000/addFieldJSON/new?name=" +t.getName() +"&address=" +t.getAddress()  +"&provider=" +t.getProvider() +"&start=" +t.getEntre() +"&end="+t.getEnd()  +"&serial=" +t.getSerialNumber()  ;
     //   String url = statics.BASE_URL + "/android/new?name=" + t.getName() + "/" ; //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

     public ArrayList<field> parseTasks(String jsonText){
        try {
            fields=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

 
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            for(Map<String,Object> obj : list){
                field t = new field();
              
     
                t.setName(obj.get("name").toString());

                //Ajouter la tâche extraite de la réponse Json à la liste
                fields.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return fields;
    }
    public ArrayList<field> getlistc(){       
        ConnectionRequest con = new ConnectionRequest();//Appel au service web (demande de connexion).
   con.setUrl("http://localhost:8000/androidaff");        

        con.addResponseListener(new ActionListener<NetworkEvent>() {
           
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceField ser = new ServiceField();
             listc = ser.parseDetailReponseJson(new String(con.getResponseData()));
                System.out.println(listc);//Récupération de la réponse du serveur
            }

        });

        NetworkManager.getInstance().addToQueueAndWait(con);

        return  listc;
    }
    
    public void envoie(){
    
Message m = new Message("<html><body>Check out <a href=\"https://www.codenameone.com/\">Codename One</a></body></html>");
m.setMimeType(Message.MIME_HTML);

// notice that we provide a plain text alternative as well in the send method
boolean success = m.sendMessageViaCloudSync("Codename One", "cheima.sassi@gmail.com", "Name Of User", "Message Subject",
                            "Check out Codename One at https://www.codenameone.com/");}
    
    public ArrayList<field> getAllTasks(){
        String url = "http://localhost:8000/androidaff";
               req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            
            @Override
          
            public void actionPerformed(NetworkEvent evt) {
           
              
             //   System.out.println( new String (req.getResponseData()));
                fields = parseTasks(new String(req.getResponseData()));
               req.removeResponseListener(this);
               
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
           
    
        return fields;
    }

    @Override
    public String toString() {
        return "ServiceTask{" + "fields=" + fields + '}';
    }
 
    
  
      public ArrayList<field> parseDetailReponseJson(String json) {

        ArrayList<field>  listReponses= new ArrayList<>();

   try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParsr permettant le parsing du résultat jsone

          Map<String, Object>  Reponses = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
            
       List<Map<String, Object>> list = (List<Map<String, Object>>) Reponses.get("comments");
       

       for (Map<String, Object> repObj : list){
           field reponse = new field();
           
           //ajouter l'objet reponse
           reponse.setName((String)repObj.get("contenu"));
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
    
    
    
          

    
    
  ArrayList<field>  listDetailReponses = new ArrayList<>();
    

    public ArrayList<field> getDetailQuestion(){       
        ConnectionRequest con = new ConnectionRequest();//Appel au service web (demande de connexion).
   con.setUrl("http://localhost:8000/androidaff");        

        con.addResponseListener(new ActionListener<NetworkEvent>() {
           
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceField ser = new ServiceField();
 listDetailReponses = ser.parseDetailReponseJson(new String(con.getResponseData()));//Récupération de la réponse du serveur
                System.out.println("taw bch nchoufou liste commentaire");
                    System.out.println(listDetailReponses);
            }

        });

        NetworkManager.getInstance().addToQueueAndWait(con);

        return  listDetailReponses;
    
    }
         public void deleteReponse(int id_rep) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
     String Url ="http://localhost:8000/deleteandroid/" +id_rep;

    
       con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
                public void updateReponse(field rep) {
     
            ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
            con.setUrl("http://localhost:8000/put/" +rep.getId()+ "/" +rep.getName()) ;// Insertion de l'URL de notre demande de connexion     
            NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
            String data = new String(con.getResponseData());
            JSONParser j = new JSONParser();
        try {
            System.out.println(j.parseJSON(new CharArrayReader(data.toCharArray())));
        } catch (IOException ex) {
        
        }
    }
     public void Message(){
      Form hi = new Form("Contacts", new BoxLayout(BoxLayout.Y_AXIS));
      hi.add(new InfiniteProgress());
      int size = Display.getInstance().convertToPixels(5, true);
      FontImage fi = FontImage.createFixed("" + FontImage.MATERIAL_PERSON, FontImage.getMaterialDesignFont(), 0xff, size, size);

Display.getInstance().scheduleBackgroundTask(() -> {
    Contact[] contacts = Display.getInstance().getAllContacts(true, true, false, true, false, false);
    Display.getInstance().callSerially(() -> {
        hi.removeAll();
        for(Contact c : contacts) {
            MultiButton mb = new MultiButton(c.getDisplayName());
            mb.setIcon(fi);
            mb.setTextLine2(c.getPrimaryPhoneNumber());
            hi.add(mb);
            mb.putClientProperty("id", c.getId());
            Display.getInstance().scheduleBackgroundTask(() -> {
                Contact cc = ContactsManager.getContactById(c.getId(), false, true, false, false, false);
                Display.getInstance().callSerially(() -> {
                    Image photo = cc.getPhoto();
                    if(photo != null) {
                        mb.setIcon(photo.fill(size, size));
                        mb.revalidate();
                    }
                });
            });
        }
        hi.getContentPane().animateLayout(150);
    });
});
                }
}
