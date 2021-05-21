/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionMatchs.services;


import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.symbiose.GestionMatchs.entities.Game;
import com.symbiose.Utils.Statics;
import java.io.IOException;
import java.*;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import com.symbiose.Utils.Session;

/**
 *
 * @author chedly
 */
public class ServiceGame {

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
    
    public ArrayList<Game> games;
    private Game game;

    
    
    
    public static ServiceGame instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceGame() {
         req = new ConnectionRequest();
    }

    public static ServiceGame getInstance() {
        if (instance == null) {
            instance = new ServiceGame();
        }
        return instance;
    }

    public boolean addGame(Game g){
          
        String url = Statics.BASE_URL+"addGameJSON/new?name="+g.getName()+"&time="+g.getTime();
        System.out.println(url);
        req.setUrl(url);
       
        req.addResponseListener((e)->{
            String str = new String(req.getResponseData());
            System.out.println("data ="+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;
    }
    
    
    public boolean deleteGame(String id){
        Session s = new Session();
        System.out.println(s.u.getId());
     
        
        String url = Statics.BASE_URL+"deleteGameJSON/"+id;
        System.out.println(url);
        req.setUrl(url);
       
        req.addResponseListener((e)->{
            String str = new String(req.getResponseData());
            System.out.println("data ="+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;
    }
    
          public void updateReponse(Game rep) {
     
            ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
         con.setUrl("http://localhost:8000/updateGameJSON/"+rep.getId()+"?name="+rep.getName()+"&time="+rep.getTime()) ;
              //      + "addGameJSON/new?name="+g.getName()+"&time="+g.getTime()
            NetworkManager.getInstance().addToQueueAndWait(con);
            String data = new String(con.getResponseData());
            JSONParser j = new JSONParser();
        try {
            System.out.println(j.parseJSON(new CharArrayReader(data.toCharArray())));
        } catch (IOException ex) {
        
        }
    
    }
        
        
        

    public ArrayList<Game> parseTasks(String jsonText){
        try {
            games=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String,Object> gamesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            */
            List<Map<String,Object>> list = (List<Map<String,Object>>)gamesListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Game t = new Game();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setName(obj.get("name").toString());
                t.setTime(obj.get("time").toString());
                //String datestring=(new SimpleDateFormat("yyyy-MM-dd")).format(t.getTime());
               // t.setTime((Date) obj.get(datestring));
              //  System.out.println(datestring);
                //Ajouter la tâche extraite de la réponse Json à la liste
                games.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return games;
    }
    
   
    public ArrayList<Game> getAllGames(){
        String url = "http://localhost:8000/AllGames";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                games = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return games;
    }
    
    
      public Game getOneEventById(String id){
        String url = Statics.BASE_URL+"GameJSON/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                game = parseEvent(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return game;
    }
      
         public Game parseEvent(String jsonText){
            JSONParser j = new JSONParser();
            Game m = new Game();             
            String str = jsonText;
            JSONObject jsonObject = new JSONObject(str);
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            m = gson.fromJson(str,Game.class);
        return m;
         }
      /*  req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                games = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return games;
      
    }*/
}


