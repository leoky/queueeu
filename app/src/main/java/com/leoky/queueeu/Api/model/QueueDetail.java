package com.leoky.queueeu.Api.model;

public class QueueDetail {
    private Doctor doctor;
    private String total_queue;
    private String note;
    private String queue_num_now;
    private String error;

    public QueueDetail(Doctor doctor, String total_queue, String note, String queue_num_now, String error) {
        this.doctor = doctor;
        this.total_queue = total_queue;
        this.note = note;
        this.queue_num_now = queue_num_now;
        this.error = error;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getTotal_queue() {
        return total_queue;
    }

    public String getNote() {
        return note;
    }

    public String getQueue_num_now() {
        return queue_num_now;
    }

    public String getError() {
        return error;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setTotal_queue(String total_queue) {
        this.total_queue = total_queue;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setQueue_num_now(String queue_num_now) {
        this.queue_num_now = queue_num_now;
    }

    public void setError(String error) {
        this.error = error;
    }
}
