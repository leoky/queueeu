package com.leoky.queueeu.Api.model;

public class QueueDetail {
    private String queue_id;
    private Doctor doctor;
    private String total_queue;
    private String note;
    private String queue_num_now;
    private String error;

    public QueueDetail(String queue_id, Doctor doctor, String total_queue, String note, String queue_num_now, String error) {
        this.queue_id = queue_id;
        this.doctor = doctor;
        this.total_queue = total_queue;
        this.note = note;
        this.queue_num_now = queue_num_now;
        this.error = error;
    }

    public String getQueue_id() {
        return queue_id;
    }

    public void setQueue_id(String queue_id) {
        this.queue_id = queue_id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getTotal_queue() {
        return total_queue;
    }

    public void setTotal_queue(String total_queue) {
        this.total_queue = total_queue;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getQueue_num_now() {
        return queue_num_now;
    }

    public void setQueue_num_now(String queue_num_now) {
        this.queue_num_now = queue_num_now;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
