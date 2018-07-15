package com.leoky.queueeu.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.leoky.queueeu.Api.model.UserData;
import com.leoky.queueeu.Api.service.UserService;
import com.leoky.queueeu.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class RegisterActivity extends AppCompatActivity {

    Button register,login;
    TextView error;
    EditText ktp,name,email,password,confirm,phone;
    private UserService userService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        login = findViewById(R.id.loginuser);
        register = findViewById(R.id.registeruser);
        error = findViewById(R.id.error);
        ktp=findViewById(R.id.ktp);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        confirm=findViewById(R.id.confirmPassword);
        phone=findViewById(R.id.phone);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplication(),LoginActivity.class);
                startActivity(i);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                String id = ktp.getText().toString();
                String nama=name.getText().toString();
                String emailname=email.getText().toString();
                String pass=password.getText().toString();
//                String confirmpass=confirm.getText().toString();
                String telp=phone.getText().toString();

                Call<UserData> callUser = userService.registerUser(nama,emailname,pass,"gender",telp,"dob","imgurl");
                callUser.enqueue(new Callback<UserData>() {
                    @Override
                    public void onResponse(Call<UserData> call, Response<UserData> response) {
                        UserData u = response.body();
                        boolean isSuccess= false;
                        if( u!=null) {
                            if (u.getError() == null) {
                                isSuccess= true;
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
        });
    }
}
