/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionEvents.entities;

import java.util.Date;

/**
 *
 * @author Mahdi
 */
public class Event {
    
    private int id;
    private String name,supplier;
    private String date;
    private int numParticipants,numRemaining;
    private String type;
    private int state;

    public Event(String name, 
            //String supplier, 
            String date, 
            int numParticipants, 
            int numRemaining, 
            String type, 
            int state) {
        this.name = name;
        //this.supplier = supplier;
        this.date = date;
        this.numParticipants = numParticipants;
        this.numRemaining = numRemaining;
        this.type = type;
        this.state = state;
    }
    
    public Event(int id){
        this.id = id;
        //this.name = name;
    }
    
    public Event(int id, String name){
        this.id = id;
        this.name = name;
    }
    
    public Event(int id, String name, String date, String type){
        this.id = id;
        this.name = name;
        this.date = date;
        this.type = type;
    }
    
    public Event() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public int getNumParticipants() {
        return numParticipants;
    }

    public void setNumParticipants(int numParticipants) {
        this.numParticipants = numParticipants;
    }

    public int getNumRemaining() {
        return numRemaining;
    }

    public void setNumRemaining(int numRemaining) {
        this.numRemaining = numRemaining;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    

    @Override
    public String toString() {
        return "Event{" + "name=" + name + ", "
                //+ "date=" + date + ","
                + " numParticipants=" + numParticipants + ", numRemaining=" + numRemaining + ", state=" + state + '}';
    }
    
}
