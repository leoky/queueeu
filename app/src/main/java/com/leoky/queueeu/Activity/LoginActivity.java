package com.leoky.queueeu.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.leoky.queueeu.Api.ApiService;
import com.leoky.queueeu.Api.model.UserData;
import com.leoky.queueeu.Api.service.UserService;
import com.leoky.queueeu.R;
import com.leoky.queueeu.Session.SessionManager;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button loginuser,register;
    EditText email,password;

    private UserService userService;
    private ProgressDialog loading;
    private SessionManager sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeNoAB);
        setContentView(R.layout.activity_login);
        loginuser=findViewById(R.id.loginuser);
        register=findViewById(R.id.register);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);

        userService = ApiService.getClient().create(UserService.class);
        sp =  new SessionManager(this);

        if(sp.isLogin()){
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
            finish();
        }

        loginuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading = ProgressDialog.show(LoginActivity.this, null, "Please wait", true, false);
                requestLogin();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(getApplication(),RegisterActivity.class));
            }
        });
    }

    private void requestLogin(){
        Call<UserData> callUser = userService.userLogin(email.getText().toString(),password.getText().toString());

        callUser.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                UserData u = response.body();
                boolean isSuccess= false;
                if( u!=null) {
                    if (u.getError() == null) {
                        sp.createUserSession(u.get_id(),u.getEmail(),u.getName(),u.getPhone(),u.getGender(),u.getPassword());
                        Intent i = new Intent(getApplication(), MainActivity.class);
                        startActivity(i);
                        isSuccess= true;
                    }
                }
                loading.dismiss();
                if(isSuccess){
                    finish();
                }
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(LoginActivity.this, " "+t, Toast.LENGTH_SHORT).show();
                if(t instanceof SocketTimeoutException){
                    requestLogin();
                }
            }
        });
    }
}
