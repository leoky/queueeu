package com.leoky.queueeu.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.leoky.queueeu.Api.ApiService;
import com.leoky.queueeu.Api.model.UserData;
import com.leoky.queueeu.Api.service.LoginService;
import com.leoky.queueeu.Api.service.UserService;
import com.leoky.queueeu.R;

import retrofit2.Callback;
import retrofit2.Response;

public class ChangePhone extends AppCompatActivity {


    EditText editText;
    Button btnSave;
    private String data=null;

    private ProgressDialog loading;
    private LoginService loginService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone);
        getSupportActionBar().setTitle("Change Phone");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editText = findViewById(R.id.tvEditText);
        btnSave = findViewById(R.id.btnSave);
        loginService = ApiService.getClient().create(LoginService.class);
        if (MainActivity.sp != null) {
            editText.setText(MainActivity.sp.getSpPhone());
            data = editText.getText().toString();
        } else {
            finish();
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!editText.getText().toString().equals(data) && !editText.getText().toString().equals("")) {
                    btnSave.setBackground(getDrawable(R.color.colorBtnEnable));
                    btnSave.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            loading = ProgressDialog.show(ChangePhone.this, null, "Please wait", true, false);
                            updateData();
                        }
                    });
                } else {
                    btnSave.setClickable(false);
                    btnSave.setBackground(getDrawable(R.color.colorBtnDisable));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    private void updateData(){
        retrofit2.Call<UserData> callUser = loginService.updatePhone(MainActivity.sp.getSpId(),editText.getText().toString());
        callUser.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(retrofit2.Call<UserData> call, Response<UserData> response) {
                UserData u = response.body();

                if(u!=null){
                    MainActivity.sp.saveSpPhone(editText.getText().toString());
                    loading.dismiss();
                    finish();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<UserData> call, Throwable t) {
                System.out.println("erorr "+ t);
                loading.dismiss();
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


}
