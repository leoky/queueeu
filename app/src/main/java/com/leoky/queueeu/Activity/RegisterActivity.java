package com.leoky.queueeu.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.leoky.queueeu.Api.ApiService;
import com.leoky.queueeu.Api.model.UserData;
import com.leoky.queueeu.Api.service.LoginService;
import com.leoky.queueeu.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    Button register,login;
    TextView error;
    EditText name,email,password,confirm,phone;
    private LoginService loginService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeNoAB);
        setContentView(R.layout.activity_register);

        login = findViewById(R.id.loginuser);
        register = findViewById(R.id.registeruser);
        error = findViewById(R.id.error);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        confirm=findViewById(R.id.confirmPassword);
        phone=findViewById(R.id.phone);

        loginService = ApiService.getClient().create(LoginService.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pass = password.getText().toString();
                String confPass = confirm.getText().toString();
                if(pass.equals(confPass)){
                    setRegister(pass);
                }
            }
        });
    }
    private void setRegister(String pass){
        Call<UserData> callUser = loginService.registerUser(
                name.getText().toString(),
                email.getText().toString(),
                pass,
                "gender",
                phone.getText().toString(),
                "dob",
                "imgurl");
        callUser.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                UserData u = response.body();
                boolean isSuccess= false;
                if( u!=null) {
                    if (u.getError() == null) {
                        isSuccess= true;
                        finish();
                    }
                }
                if(isSuccess){
                    error.setText("success");
                }
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                error.setText("Error");
                System.out.println("erorr "+t);
            }
        });
    }
}
