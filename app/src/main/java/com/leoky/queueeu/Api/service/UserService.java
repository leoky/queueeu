package com.leoky.queueeu.Api.service;

import com.leoky.queueeu.Api.model.UserData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {



    @FormUrlEncoded
    @POST("users/add")
    Call<UserData> registerUser (
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("gender") String gender,
            @Field("phone") String phone,
            @Field("dob") String dob,
            @Field("photo") String imgurl
    );

    @FormUrlEncoded
    @POST("users/login")
    Call<UserData> userLogin(@Field("email") String email,
                                @Field("password") String password);

//    @FormUrlEncoded
//    @POST("doctor/done/{queue_id}")
//    Call<RepoQueue> queueCancel(@Path("queue_id") String id,
//                              @Field("result") String result);

}
