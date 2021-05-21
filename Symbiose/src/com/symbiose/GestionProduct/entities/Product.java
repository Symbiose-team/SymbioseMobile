/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionProduct.entities;

/**
 *
 * @author Oussema Mestiri
 */
public class Product {
    private int id;
    private String name;
    private String description;
    private float price;
    private int type;
    private int state;

    


    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Product( String name, int state, String description, float price, int type) {
        this.name = name;
        this.state = state;
        this.description = description;
        this.price = price;
        this.type = type;
    }
    
    public Product( int id, String name, int state, String description, float price, int type) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.description = description;
        this.price = price;
        this.type = type;
    }

    public Product() {
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
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    

    @Override
    public String toString() {
        return "Product" + "id=" + id + ", name=" + name + ", state=" + state + ", description=" + description + ", price=" + price + ", type=" + type +'\n';
    }
    
    
}
