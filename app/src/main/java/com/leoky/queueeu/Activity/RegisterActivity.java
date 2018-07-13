package com.leoky.queueeu.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.leoky.queueeu.Api.ApiService;
import com.leoky.queueeu.Api.model.UserData;
import com.leoky.queueeu.Api.service.UserService;
import com.leoky.queueeu.R;
import com.leoky.queueeu.Session.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    Button register;
    EditText email,password;

    private UserService userService;
    private ProgressDialog loading;
    private SessionManager sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


    }
}
