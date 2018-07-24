package com.leoky.queueeu.Api.service;

import com.leoky.queueeu.Api.model.RepoSearch;
import com.leoky.queueeu.Api.model.UserData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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

    @FormUrlEncoded
    @POST("users/finddoctor/name")
    Call<RepoSearch> searchName(@Field("name") String name);

    @FormUrlEncoded
    @POST("users/update/email/{id}")
    Call<UserData> updateEmail(@Path("id") String id,
                               @Field("newEmail") String email);

    @FormUrlEncoded
    @POST("users/update/password/{id}")
    Call<UserData> updatePassword(@Path("id") String id,
                                  @Field("currentPassword") String pass,
                                  @Field("newPassword") String newPass);

    @FormUrlEncoded
    @POST("users/update/phone/{id}")
    Call<UserData> updatePhone(@Path("id") String id,
                               @Field("newPhone") String phone);


}
