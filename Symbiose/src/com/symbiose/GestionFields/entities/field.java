/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionFields.entities;

import com.codename1.ui.TextField;
import java.util.Date;
import com.codename1.util.DateUtil;

/**
 *
 * @author Chaima
 */
public class field {
    private int id;
    private int serialNumber;
    private String name; 
    private String provider;
    private String Address;
    private Boolean status;
    public field(int id){
        this.id=id;
    }

    public field(int id, Boolean status) {
        this.id = id;
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    private String price;
    private String space;
    private Date entre  ;
    private Date end;

    public field(String name, int serialNumber, String provider, String address, String price, Date entre, Date end) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.provider = provider;
        this.Address = address;
        this.price = price;
        this.entre = entre;
        this.end = end;    }

   

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public field(String name ,int serialNumber,  String provider, String Address, String price, String space, Date entre, Date end) {
       
        this.serialNumber = serialNumber;
        this.name = name;
        this.provider = provider;
        this.Address = Address;
        this.price = price;
        this.space = space;
        this.entre = entre;
        this.end = end;
    }
   

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public Date getEntre() {
        return entre;
    }

    public void setEntre(Date entre) {
        this.entre = entre;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    
    

    public field() {
        
    }

    public field(int parseInt, String text) {
        this.id=parseInt;
        this.name=text;
    }

    public field(String text) {
      this.name=text;
    }

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  "name=" + name +"    ,   "   +"address=" + Address ;
    }
    
    public field(int id, int serialNumber, String name) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.name = name;
    }

   

  
   
    

    
    
}
