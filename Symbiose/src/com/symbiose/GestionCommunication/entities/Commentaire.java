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
public class Commentaire {
   private int id_rep;
   private int id_quest;
   private String contenu;
   private String contenuPub;
   private String dateQuestion;
   private int user_id;
    public Commentaire() {
    }
 public Commentaire(String contenu,int id_quest, int user_id) {
        this.id_quest = id_quest;
        this.contenu = contenu;
        this.user_id = user_id;
    }
    public Commentaire(int id_rep, int id_quest, String contenu, int user_id) {
        this.id_rep = id_rep;
        this.id_quest = id_quest;
        this.contenu = contenu;
        this.user_id = user_id;
    }

    public Commentaire(int id_rep, int id_quest, String contenu, String contenuPub, String dateQuestion, int user_id) {
        this.id_rep = id_rep;
        this.id_quest = id_quest;
        this.contenu = contenu;
        this.contenuPub = contenuPub;
        this.dateQuestion = dateQuestion;
        this.user_id = user_id;
    }

    public Commentaire(int id_rep, int id_quest, String contenu, String dateQuestion, int user_id) {
        this.id_rep = id_rep;
        this.id_quest = id_quest;
        this.contenu = contenu;
        this.dateQuestion = dateQuestion;
        this.user_id = user_id;
    }

    public Commentaire(int id_rep, int id_q, String text, String dc) {
        this.id_rep = id_rep;
        this.id_quest = id_quest;
        this.contenu = contenu;
        this.dateQuestion = dateQuestion;
    }

    public Commentaire(int id_quest, String text, String dc) {
        this.id_quest = id_quest;
        
    }

    public int getId_rep() {
        return id_rep;
    }

    public void setId_rep(int id_rep) {
        this.id_rep = id_rep;
    }

    public int getId_quest() {
        return id_quest;
    }

    public void setId_quest(int id_quest) {
        this.id_quest = id_quest;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getDateQuestion() {
        return dateQuestion;
    }

    public void setDateQuestion(String dateQuestion) {
        this.dateQuestion = dateQuestion;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getContenuPub() {
        return contenuPub;
    }

    public void setContenuPub(String contenuPub) {
        this.contenuPub = contenuPub;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id_rep=" + id_rep + ", id_quest=" + id_quest + ", contenu=" + contenu + ", dateQuestion=" + dateQuestion + ", user_id=" + user_id + '}';
    }

   

   

 

    
}
