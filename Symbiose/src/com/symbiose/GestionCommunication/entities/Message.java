package com.symbiose.GestionCommunication.entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author medal
 */
public class Message {
    	
    int id;
    int conversation_id;
    int user_id;
    String contenu;
    String date;
    String email;

    public Message(int id, int conversation_id, int user_id, String contenu, String date, String email) {
        this.id = id;
        this.conversation_id = conversation_id;
        this.user_id = user_id;
        this.contenu = contenu;
        this.date = date;
        this.email = email;
    }
    public Message() {
    }

    public Message(int id, int conversation_id, int user_id, String contenu, String date) {
        this.id = id;
        this.conversation_id = conversation_id;
        this.user_id = user_id;
        this.contenu = contenu;
        this.date = date;
    }
 public Message( int conversation_id, int user_id, String contenu) {
        this.conversation_id = conversation_id;
        this.user_id = user_id;
        this.contenu = contenu;
    }
    public Message(int id, int conversation_id, int user_id, String contenu) {
        this.id = id;
        this.conversation_id = conversation_id;
        this.user_id = user_id;
        this.contenu = contenu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConversation_id() {
        return conversation_id;
    }

    public void setConversation_id(int conversation_id) {
        this.conversation_id = conversation_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Message{" + "id=" + id + ", conversation_id=" + conversation_id + ", user_id=" + user_id + ", contenu=" + contenu + ", date=" + date + '}';
    }
    
    
}
