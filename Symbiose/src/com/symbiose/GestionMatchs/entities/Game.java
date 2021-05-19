/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.symbiose.GestionMatchs.entities;

import java.util.Date;


/**
 *
 * @author Chedly
 */
public class Game {

    private Integer id;

    private Integer userid;

    private String userId;

    private String name;

    private Date  time;


    public Game(Integer id, String name, Date time) {
        this.id = id;
        this.name=name;
        this.time=time;
    }
    public Game(Integer id, String name, Date time, String owner) {
        this.id = id;
        this.name=name;
        this.time=time;
        this.userId=owner;
    }

    public Game(Integer id,Integer userid) {
        this.id=id;
        this.userid = userid;

    }

    public Game() {

    }

    public Integer getUserid() {
        return userid;
    }

    public Game(String name) {
        this.name=name;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return this.time;
    }

    public void setTime(Date time) {
        this.time = time;
    }


    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", time=" + time +
                '}';
    }
}

