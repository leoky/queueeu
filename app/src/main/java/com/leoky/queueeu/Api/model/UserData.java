package com.leoky.queueeu.Api.model;

import java.util.Date;

//@Entity(tableName = "userData")
public class UserData {
    private String _id;
    private String name;
    private String email;
    private String password;
    private String photo;
    private String phone;
    private String gender;
    private Date dob;

    public UserData(String _id, String name, String email, String password, String photo, String phone, String gender, Date dob) {
        this._id = _id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.photo = photo;
        this.phone = phone;
        this.gender = gender;
        this.dob = dob;
    }

    public String get_id() {

        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
