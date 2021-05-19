/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionEvents.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.symbiose.GestionEvents.entities.Event;
import com.symbiose.GestionUsers.entities.User;
import static com.symbiose.GestionUsers.services.userService.getResponse;
import com.symbiose.Utils.Session;
import com.symbiose.Utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mahdi
 */
public class ServiceEvent {
    public ArrayList<Event> events;
    private ConnectionRequest req;
    public static ServiceEvent instance = null  ;
    public boolean resultOK;
    
    public ServiceEvent(){
        req = new ConnectionRequest();
    }
    
    public static ServiceEvent getInstance(){
        if(instance == null)
            instance= new ServiceEvent();
        return instance;
    }
    
    public boolean addEvent(Event ev){
          
        String url = Statics.BASE_URL+"addEventJSON/new?name="+ev.getName()+"&date="+ev.getDate()
                +"&num_participants="+ev.getNumParticipants()
                +"&num_remaining="+ev.getNumRemaining()
                +"&type="+ev.getType()
                +"&state="+ev.getState();
        System.out.println(url);
        req.setUrl(url);
       
        req.addResponseListener((e)->{
            String str = new String(req.getResponseData());
            System.out.println("data ="+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;
    }
    
    public boolean joinEvent(Event ev){
        
        String url = Statics.BASE_URL+"event/join/"+ev.getId();
        req.setUrl(url);
        
        req.addResponseListener((e)->{
            String str = new String(req.getResponseData());
            System.out.println("data ="+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;
    }
    
    //update event
    public void updateReponse(Event rep) {
     
            ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
            con.setUrl("http://localhost:8000/Event/put/" +rep.getId()+ "/" +rep.getName()) ;// Insertion de l'URL de notre demande de connexion     
            NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
            String data = new String(con.getResponseData());
            JSONParser j = new JSONParser();
        try {
            System.out.println(j.parseJSON(new CharArrayReader(data.toCharArray())));
        } catch (IOException ex) {
        
        }
    }
    
    //delete event
    public boolean deleteEvent(int id){
          
        //http://127.0.0.1:8000/addEventJSON/new?name=fffffffffffffff&num_participants=100&num_remaining=100&type=football&state=1
        String url = Statics.BASE_URL+"deleteEventJSON/"+id;
        System.out.println(url);
        req.setUrl(url);
       
        req.addResponseListener((e)->{
            String str = new String(req.getResponseData());
            System.out.println("data ="+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;
    }
     
    //affichage events
    public ArrayList<Event> parseTasks(String jsonText){
        try {
            events=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

 
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            for(Map<String,Object> obj : list){
                Event t = new Event();
                
                int id = (int) Float.parseFloat(obj.get("id").toString());
                        
                String name = obj.get("Name").toString();
                        
                float numParticip = Float.parseFloat(obj.get("NumParticipants").toString());
                        
                float numRemaining = Float.parseFloat(obj.get("NumRemaining").toString());
                        
                String type = obj.get("Type").toString();
                
                             
                t.setId((int)id);
                t.setName(name);
                t.setNumParticipants((int)numParticip);
                t.setNumRemaining((int)numRemaining);
                t.setType(type);

                events.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return events;
    }
    
    public ArrayList<Event> getAllEvents(){
        String url = "http://localhost:8000/AllEvents";
               req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            
            @Override
          
            public void actionPerformed(NetworkEvent evt) {
           
              
             //System.out.println( new String (req.getResponseData()));
                events = parseTasks(new String(req.getResponseData()));
               req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }
    
    public Event getEvent(int idEvent){
        
        Event ev = new Event();
        Map m = getResponse("EventJSON/"+idEvent);
        ArrayList d = (ArrayList) m.get("root");
        System.out.println(d);
        Map n = (Map) d.get(0);
        System.out.println(n);
        
        int id = Integer.parseInt(n.get("id").toString());
        int numParticip = (int) Float.parseFloat(n.get("NumParticipants").toString());
        int numRemaining = (int) Float.parseFloat(n.get("NumRemaining").toString());
        int State = (int) Float.parseFloat(n.get("State").toString());

        ev.setId(id);
        ev.setName(n.get("Name").toString());
        ev.setNumParticipants(numParticip);
        ev.setNumRemaining(numRemaining);
        ev.setType(n.get("Type").toString());
        ev.setState(State);
       
        return ev;  
    }
}
