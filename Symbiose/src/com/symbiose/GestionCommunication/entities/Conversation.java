/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionCommunication.entities;

/**
 *
 * @author medal
 */
public class Conversation {
    int id;
    int user1_id;
    int user2_id;
    String email;

    public Conversation() {
    }

    public Conversation(int id, int user1_id, int user2_id, String email) {
        this.id = id;
        this.user1_id = user1_id;
        this.user2_id = user2_id;
        this.email = email;
    }

    public Conversation(int id, int user1_id, int user2_id) {
        this.id = id;
        this.user1_id = user1_id;
        this.user2_id = user2_id;
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

    public int getUser1_id() {
        return user1_id;
    }

    public void setUser1_id(int user1_id) {
        this.user1_id = user1_id;
    }

    public int getUser2_id() {
        return user2_id;
    }

    public void setUser2_id(int user2_id) {
        this.user2_id = user2_id;
    }

    @Override
    public String toString() {
        return "Conversation{" + "id=" + id + ", user1_id=" + user1_id + ", user2_id=" + user2_id + '}';
    }
    
}
