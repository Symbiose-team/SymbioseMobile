/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionFields.entities;

/**
 *
 * @author Lenovo
 */
public class User {

    private int id;

    private String roles;

    private String userName;
    private String lastname;
    private String pseudoName;
    private String userMail;
    private String userPassword;
    private String userPhone;
    private String userCin;
    private String userAddress;
    private String userPhoto;
    private String userDayOfBirth;
    private String userSite;
    // private Date createdAt;
    private String nationality;
    private String speciality;
    private String bio;

    public User() {
    }

    public User(int id, String userName, String lastname, String pseudoName, String userMail, String userPassword, String userPhone, String userCin, String userAddress, String userDayOfBirth, String userSite, String nationality, String speciality, String bio, String roles) {
        this.id = id;

        // this.passwordRequestedAt = passwordRequestedAt;
        this.userName = userName;
        this.lastname = lastname;
        this.pseudoName = pseudoName;
        this.userMail = userMail;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.userCin = userCin;
        this.userAddress = userAddress;
         this.userDayOfBirth = userDayOfBirth;
        this.userSite = userSite;
        //  this.createdAt = createdAt;
        this.nationality = nationality;
        this.speciality = speciality;
        this.bio = bio;
        this.roles = roles;

    }

    public User(String userName, String lastname, String pseudoName, String userMail, String userPassword, String userPhone, String userCin, String userAddress,String userDayOfBirth, String userSite, String nationality, String speciality, String bio, String roles) {

        this.userName = userName;
        this.lastname = lastname;
        this.pseudoName = pseudoName;
        this.userMail = userMail;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.userCin = userCin;
        this.userAddress = userAddress;
         this.userDayOfBirth = userDayOfBirth;
        this.userSite = userSite;
        //  this.createdAt = createdAt;
        this.nationality = nationality;
        this.speciality = speciality;
        this.bio = bio;
        this.roles = roles;

    }

    public User(String userName, String lastname, String pseudoName, String userMail, String userPassword, String userPhone, String userCin, String userAddress,String userDayOfBirth, String userSite, String nationality, String speciality, String bio) {

        this.userName = userName;
        this.lastname = lastname;
        this.pseudoName = pseudoName;
        this.userMail = userMail;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.userCin = userCin;
        this.userAddress = userAddress;
         this.userDayOfBirth = userDayOfBirth;
        this.userSite = userSite;
        //  this.createdAt = createdAt;
        this.nationality = nationality;
        this.speciality = speciality;
        this.bio = bio;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   

//    public LocalDate getPasswordRequestedAt() {
//        return passwordRequestedAt;
//    }
//
//    public void setPasswordRequestedAt(LocalDate passwordRequestedAt) {
//        this.passwordRequestedAt = passwordRequestedAt;
//    }
    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPseudoName() {
        return pseudoName;
    }

    public void setPseudoName(String pseudoName) {
        this.pseudoName = pseudoName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserCin() {
        return userCin;
    }

    public void setUserCin(String userCin) {
        this.userCin = userCin;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

//    public Date getUserDayOfBirth() {
//        return userDayOfBirth;
//    }
//
//    public void setUserDayOfBirth(Date userDayOfBirth) {
//        this.userDayOfBirth = userDayOfBirth;
//    }

    public String getUserDayOfBirth() {
        return userDayOfBirth;
    }

    public void setUserDayOfBirth(String userDayOfBirth) {
        this.userDayOfBirth = userDayOfBirth;
    }
    
    
    
    public String getUserSite() {
        return userSite;
    }

    public void setUserSite(String userSite) {
        this.userSite = userSite;
    }
//
//    public Date getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", roles=" + roles + ", userName=" + userName + ", lastname=" + lastname + ", pseudoName=" + pseudoName + ", userMail=" + userMail + ", userPassword=" + userPassword + ", userPhone=" + userPhone + ", userCin=" + userCin + ", userAddress=" + userAddress + ", userPhoto=" + userPhoto + ", userDayOfBirth=" + userDayOfBirth + ", userSite=" + userSite + ", nationality=" + nationality + ", speciality=" + speciality + ", bio=" + bio + '}';
    }


}
