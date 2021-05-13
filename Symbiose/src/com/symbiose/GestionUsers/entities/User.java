/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionUsers.entities;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author SkanderThabet
 */
public class User {
    int id;
    String first_name;
    String last_name ,hash,email,picture,role,genre,adresse,registration_token,phone_number,cin;
    String birthday;
    
   

    public User(String first_name, String last_name,String hash, String email, String picture, String role, String genre, String adresse, String phone_number, String cin ) {
        this.phone_number = phone_number;
        this.cin = cin;
        this.first_name = first_name;
        this.last_name = last_name;
        this.hash = hash;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.genre = genre;
        this.adresse = adresse;
    }

//    public User(String first_name, String last_name, String hash, String email, String picture, String role, String genre, String adresse, String registration_token, String birthday) {
//        this.first_name = first_name;
//        this.last_name = last_name;
//        this.hash = hash;
//        this.email = email;
//        this.picture = picture;
//        this.role = role;
//        this.genre = genre;
//        this.adresse = adresse;
//        this.registration_token=registration_token;
//        this.birthday=birthday;
//    }

    public User(String first_name, String last_name, String hash, String email, String picture, String role, String genre, String adresse) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.hash = hash;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.genre = genre;
        this.adresse = adresse;
       
    }
    public User(int id, String first_name, String last_name, String hash, String email, String picture, String role, String genre, String adresse) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.hash = hash;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.genre = genre;
        this.adresse = adresse;
    }
    

    public User() {
        
    }

    public User(int id, String text, String text0, String text1, String text2, String text3) {
        
    }

    public User(int id, String text, String text0, String text1, String text2) {
        
    }

    public User(int id,String first_name, String last_name, String email, String role, String genre, String adresse, String phone_number, String cin, String birthday) {
        this.id=id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.role = role;
        this.genre = genre;
        this.adresse = adresse;
        this.phone_number = phone_number;
        this.cin = cin;
        this.birthday = birthday;
    }
    public User(String first_name, String last_name,String hash ,String email,String picture, String role, String genre, String adresse, String phone_number, String cin, String birthday) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.hash=hash;
        this.email = email;
        this.picture=picture;
        this.role = role;
        this.genre = genre;
        this.adresse = adresse;
        this.phone_number = phone_number;
        this.cin = cin;
        this.birthday = birthday;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getRegistration_token() {
        return registration_token;
    }

    public void setRegistration_token(String registration_token) {
        this.registration_token = registration_token;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    
    
    
    
    
}
