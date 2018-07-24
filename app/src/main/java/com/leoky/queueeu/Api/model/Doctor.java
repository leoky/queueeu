package com.leoky.queueeu.Api.model;

public class Doctor {
    private String id;
    private String name;
    private String photo;
    private String phone;
    private String category;

    private Clinic clinic;
    private String error;

    public Doctor(String id, String name, String photo, String phone, String category, Clinic clinic, String error) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.phone = phone;
        this.category = category;
        this.clinic = clinic;
        this.error = error;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
