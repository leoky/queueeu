package com.leoky.queueeu.Api.model;

import java.util.List;

public class RepoSearch {
    private List<Doctor> doctor;
    private String error;

    public RepoSearch(List<Doctor> doctor, String error) {
        this.doctor = doctor;
        this.error = error;
    }

    public List<Doctor> getDoctor() {
        return doctor;
    }

    public void setDoctor(List<Doctor> doctor) {
        this.doctor = doctor;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
