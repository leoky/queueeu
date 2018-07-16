package com.leoky.queueeu.Api.service;

import com.leoky.queueeu.Api.model.RepoSearch;
import com.leoky.queueeu.Api.model.UserData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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

    @FormUrlEncoded
    @POST("users/finddoctor/name")
    Call<RepoSearch> searchName(@Field("name") String name);
}
