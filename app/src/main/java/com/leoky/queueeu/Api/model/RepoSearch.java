package com.leoky.queueeu.Api.model;

import java.util.List;

public class RepoSearch {
    private List<Doctor_id> doctor;
    private String error;

    public RepoSearch(List<Doctor_id> doctor, String error) {
        this.doctor = doctor;
        this.error = error;
    }

    public List<Doctor_id> getDoctor() {
        return doctor;
    }

    public void setDoctor(List<Doctor_id> doctor) {
        this.doctor = doctor;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
