package com.leoky.queueeu.Api.service;

import com.leoky.queueeu.Api.model.QueueDetail;
import com.leoky.queueeu.Api.model.RepoSearch;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @FormUrlEncoded
    @POST("users/finddoctor/name")
    Call<RepoSearch> searchName(@Field("name") String name);

    @GET("users/queue/{doctor_id}")
    Call<QueueDetail> getQueueFrom(@Path("doctor_id") String id);

    @FormUrlEncoded
    @POST("queue/add")
    Call<QueueDetail> setQueue(@Field("name") String patientName,
                               @Field("patient_id") String patId,
                               @Field("doctor") String doctorName,
                               @Field("doctor_id") String dcotorId,
                               @Field("note") String note);

}
