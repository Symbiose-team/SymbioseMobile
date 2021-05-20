/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Product;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Oussema Mestiri
 */
public class ServiceProduct {
    public ArrayList<Product> products;
    public static ServiceProduct instance  = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceProduct() {
        req = new ConnectionRequest();
    }
    
    public static ServiceProduct getInstance(){
        if (instance == null)
            instance = new ServiceProduct();
        return instance;
    }
    
   
    public boolean addProduct(Product ev){

        String url = Statics.BASE_URL+"addProductJSON/new?name="+ev.getName()+"&description="+ev.getDescription()+"&state="+ev.getState()+"&price="+ev.getPrice()+"&type="+ev.getType();                
        System.out.println(url);
        req.setUrl(url);

        req.addResponseListener((e)->{
            String str = new String(req.getResponseData());
            System.out.println("data ="+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;
    }
    
     public void updateReponse(Product rep) {
     
            ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
            con.setUrl("http://localhost:8000/Product/put/" +rep.getId()+ "/" +rep.getName()+ "/" +rep.getState()+ "/" +rep.getPrice()+ "/" +rep.getType()) ;// Insertion de l'URL de notre demande de connexion     
            NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
            String data = new String(con.getResponseData());
            JSONParser j = new JSONParser();
        try {
            System.out.println(j.parseJSON(new CharArrayReader(data.toCharArray())));
        } catch (IOException ex) {
        
        }
    }
     
    
    public boolean deleteProduct(String id){
          
        String url = Statics.BASE_URL+"deleteProductJSON/"+id;
        System.out.println(url);
        req.setUrl(url);
       
        req.addResponseListener((e)->{
            String str = new String(req.getResponseData());
            System.out.println("data ="+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;
    }
    
    
   public ArrayList<Product> parseTasks(String jsonText){
        try {
            products=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

 
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            for(Map<String,Object> obj : list){
                Product t = new Product();
                
                int id = (int) Float.parseFloat(obj.get("id").toString());
                        
                String name = obj.get("Name").toString();
                                
                int type = (int) Float.parseFloat(obj.get("Type").toString());
                
                float price = (float) Float.parseFloat(obj.get("Price").toString());

                String description = obj.get("Description").toString();
                        
                
                t.setId((int)id);
                t.setName(name);
                t.setType((int)type);
                t.setPrice((float)price);
                t.setDescription(description);


                products.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return products;
    }
   
     public ArrayList<Product> getAllProducts(){
        String url = "http://localhost:8000/getAllProducts";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            
            @Override
          
            public void actionPerformed(NetworkEvent evt) {
           
              
             //System.out.println( new String (req.getResponseData()));
                products = parseTasks(new String(req.getResponseData()));
               req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return products;
    }
     

}
