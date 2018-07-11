package com.leoky.queueeu.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class LoginActivity extends AppCompatActivity {

    Button loginuser;
    EditText email,password;

    private UserService userService;
    private ProgressDialog loading;
    private SessionManager sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginuser=findViewById(R.id.loginuser);
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
    }

    private void requestLogin(){
        Call<UserData> callUser = userService.loginRequest(email.getText().toString(),password.getText().toString());

        callUser.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
//                UserData u = response.body();
                UserData u = response.body();
                if(u !=null){
                    sp.createUserSession(u.getId(),u.getEmail(),u.getName(),u.getPassword(),u.getPhoto(),u.getDob(),u.getPhone(),u.getGender(),
                            u.getClinic().getClinic_name(),u.getClinic().getLocation(),u.getClinic().getEstimate(),u.getClinic().getStatus());
                    Intent i = new Intent(getApplication(),MainActivity.class);
                    startActivity(i);
                    loading.dismiss();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                loading.dismiss();
                System.out.println("erorr "+t);
            }
        });
    }
}
