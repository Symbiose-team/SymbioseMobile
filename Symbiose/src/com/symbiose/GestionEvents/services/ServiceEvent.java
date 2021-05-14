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
    public ArrayList<Event> event;
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
          
        String url = Statics.BASE_URL+"/addEventJSON/"+ev.getName()
                //+"/"+ev.getDate()
                +"/"+ev.getNumParticipants()+"/"+ev.getNumRemaining()+"/"+ev.getType()+"/"+ev.getState();
        System.out.println(url);
        req.setUrl(url);
        /*
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            public void actionPerformed(NetworkEvent evt){
                resultOK = req.getResponseCode()==200;
                req.addResponseListener(this);
            }
        });
        */
        req.addResponseListener((e)->{
            String str = new String(req.getResponseData());
            System.out.println("data ="+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;
    }
      
    public ArrayList<Event> getAllEvent(){
        
        ArrayList<Event> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/AllEvents/";
        req.setUrl(url);
        
        //req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt){
                //event = parseEvent(new String(req.getResponseData()));
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try{
                    Map<String,Object>mapEvents = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>) mapEvents.get("root");
                    
                    for(Map<String,Object> obj:listOfMaps){
                        Event ev = new Event();
                        
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String name = obj.get("Name").toString();
                        
                        float numParticip = Float.parseFloat(obj.get("num_participants").toString());
                        
                        float numRemaining = Float.parseFloat(obj.get("num_remianing").toString());
                        
                        String type = obj.get("type").toString();
                        
                        String supplier = obj.get("supplier").toString();

                        
                        float state = Float.parseFloat(obj.get("state").toString());
                        
                        ev.setId((int)id);
                        ev.setName(name);
                        ev.setNumParticipants((int)numParticip);
                        ev.setNumRemaining((int)numRemaining);
                        ev.setType(type);
                        ev.setState((int)state);
                        ev.setSupplier(supplier);
                        
                        //date
                        String DateConverter = obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10, obj.get("obj").toString().lastIndexOf("}"));
                        
                        Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                        
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM--dd");
                        String dateString = formatter.format(currentTime);
                        ev.setDate(dateString);
                        
                        result.add(ev);
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                //req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
    
    /*
    public ArrayList<Event> parseEvent(String jsonText){
        try {
            event=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String,Object> eventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            java.util.List<Map<String,Object>> list = (java.util.List<Map<String,Object>>)eventsListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Event e = new Event();
                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int)id);
                //e.setSupplier(obj.get("supplier").toString());
                e.setName(obj.get("name").toString());
                //e.setDate((Date) obj.get("date"));
                //e.setNumParticipants(((int)Float.parseFloat(obj.get("num_participants").toString())));
                //e.setNumRemaining(((int)Float.parseFloat(obj.get("num_remaining").toString())));
                //e.setType(obj.get("type").toString());
                //e.setState(((int)Float.parseFloat(obj.get("state").toString())));
                event.add(e);
            }
            
            
        } catch (IOException ex) {
            
        }
       
        return event;
    }
    */
    
    
}
