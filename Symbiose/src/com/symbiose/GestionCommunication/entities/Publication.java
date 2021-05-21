/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionCommunication.entities;

/**
 *
 * @author bhk
 */
public class Publication {
   private int id;
   private String description;
    private String username_question;
      private String date_question ;
      	
         private int user_id;
    public Publication() {
    }


    public Publication( String description) {
        this.description = description;
        
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

  
   
    public String getDate_question() {
        return date_question;
    }

    public void setDate_question(String date_question) {
        this.date_question = date_question;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername_question() {
        return username_question;
    }

    public void setUsername_question(String username_question) {
        this.username_question = username_question;
    }

    @Override
    public String toString() {
        return "Publication{" + "id=" + id + ", description=" + description + '}';
    }

    

 

    
}
