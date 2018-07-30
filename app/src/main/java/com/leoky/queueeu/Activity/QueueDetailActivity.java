package com.leoky.queueeu.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.leoky.queueeu.Api.model.QueueDetail;
import com.leoky.queueeu.R;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QueueDetailActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    public static String KEY_NAME = "KEY_NAME";
    public static String KEY_QUEUE_ID = "KEY_QUEUE_ID";
    public static String KEY_MODE = "KEY_MODE";
    public static String KEY_DOCTOR_ID = "KEY_DOCTOR_ID";
    public static int MODE_QUEUE = 0;
    public static int MODE_ON_QUEUE = 1;

    private TextView tvCName,tvAddress,tvNumNow,tvTotalqueue,tvEta;
    private ImageView img;
    private Button btn;
    private EditText editText;
    private ProgressDialog loading;
    private SwipeRefreshLayout swipe;

    private QueueDetail queueDetail;
    private boolean isQueue;
    private boolean isRefresh= false;
    public String queueID=null;

    private void initID(){
        tvCName = findViewById(R.id.tvCName);
        tvAddress = findViewById(R.id.tvAddress);
        tvNumNow = findViewById(R.id.tvNumNow);
        tvTotalqueue =findViewById(R.id.tvTotalQueueNum);
        tvEta = findViewById(R.id.tvETANum);
        img = findViewById(R.id.imgNow);
        btn = findViewById(R.id.btnQueue);
        editText = findViewById(R.id.editTextNote);
        swipe = findViewById(R.id.swipeRefresh);

        swipe.setOnRefreshListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra(KEY_NAME));

        initID();
        initMode();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isQueue){
                    loading = ProgressDialog.show(QueueDetailActivity.this, null, "Please wait", true, false);
                    queue();
                }else{
                    dialog();
                }
            }
        });
    }
    private void initMode(){
        if(getIntent().getIntExtra(KEY_MODE,0) == MODE_QUEUE){
            isQueue = true;
        }else {
            isQueue = false;
        }

        getData(isQueue);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
    private void setBtnOn(boolean mode){
       if(!queueDetail.getDoctor().getClinic().getStatus().equals("Closed")){
           btn.setClickable(true);
           btn.setText((mode)? R.string.btn_queue : R.string.btn_cancel);
           btn.setBackground((mode)? getDrawable(R.drawable.custom_button_green) : getDrawable(R.drawable.custom_button_red));
           editText.setEnabled(mode);
       }else{
           btn.setText(R.string.offline);
           btn.setBackground(getDrawable(R.drawable.custom_button_grey));
           btn.setClickable(false);
       }
    }

    private void updateView(){
        int estimate;
        Picasso.get().load(queueDetail.getDoctor().getPhoto()).into(img);
        tvCName.setText(queueDetail.getDoctor().getClinic().getClinic_name());
        tvAddress.setText(queueDetail.getDoctor().getClinic().getLocation());
        tvTotalqueue.setText(queueDetail.getTotal_queue());
        estimate = convertToInt(queueDetail.getTotal_queue())* convertToInt(queueDetail.getDoctor().getClinic().getEstimate());
        tvEta.setText(estimate+" min");
        editText.setText(queueDetail.getNote());
        if(isQueue){
            tvNumNow.setText("");
        }else {
            estimate = convertToInt(queueDetail.getDoctor().getClinic().getEstimate())* convertToInt(queueDetail.getQueue_num_now());
            tvNumNow.setText((queueDetail.getQueue_num_now().equals("0")? "Your Turn" : queueDetail.getQueue_num_now() ));
            tvEta.setText(estimate+" min");
        }
        setBtnOn(isQueue);
    }

    private void getData(boolean isQueue){
        if(isQueue){
            Call<QueueDetail> call = MainActivity.userService.getQueueFrom(getIntent().getStringExtra(KEY_DOCTOR_ID));
            call.enqueue(new Callback<QueueDetail>() {
                @Override
                public void onResponse(Call<QueueDetail> call, Response<QueueDetail> response) {
                    queueDetail = response.body();
                    if (queueDetail.getError() == null) {
                        updateView();
                        getSupportActionBar().setSubtitle(queueDetail.getDoctor().getCategory());
                        setRefresh();
                    }

                }

                @Override
                public void onFailure(Call<QueueDetail> call, Throwable t) {
                    Toast.makeText(QueueDetailActivity.this,""+t,Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Call<QueueDetail> call = MainActivity.userService.getQueueNum((queueID == null)?getIntent().getStringExtra(KEY_QUEUE_ID):queueID,
                    getIntent().getStringExtra(KEY_DOCTOR_ID));
            call.enqueue(new Callback<QueueDetail>() {
                @Override
                public void onResponse(Call<QueueDetail> call, Response<QueueDetail> response) {
                    queueDetail = response.body();
                    if (queueDetail.getError() == null) {
                        updateView();
                        getSupportActionBar().setSubtitle(queueDetail.getDoctor().getCategory());
                        setRefresh();

                    }

                }

                @Override
                public void onFailure(Call<QueueDetail> call, Throwable t) {
                    Toast.makeText(QueueDetailActivity.this,""+t,Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
    private void queue(){
        Call<QueueDetail> call = MainActivity.userService.setQueue(
                MainActivity.sp.getSpName(),
                MainActivity.sp.getSpId(),
                queueDetail.getDoctor().getName(),
                queueDetail.getDoctor().get_id(),
                editText.getText().toString()
        );
        call.enqueue(new Callback<QueueDetail>() {
            @Override
            public void onResponse(Call<QueueDetail> call, Response<QueueDetail> response) {
                queueDetail = response.body();
//                if (queueDetail.getError() == null) {
                    isQueue = false;
                    queueID = queueDetail.getQueue_id();
                    updateView();
                    setBtnOn(isQueue);
                    loading.dismiss();
//                }
            }

            @Override
            public void onFailure(Call<QueueDetail> call, Throwable t) {
                Toast.makeText(QueueDetailActivity.this,""+t,Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        });
    }
    private void cancel(){
        Call<QueueDetail> call = MainActivity.userService.cancelQueue(queueDetail.getQueue_id(),MainActivity.sp.getSpName(),"ada la");
        call.enqueue(new Callback<QueueDetail>() {
            @Override
            public void onResponse(Call<QueueDetail> call, Response<QueueDetail> response) {
                if(response.isSuccessful()) {
//                    queueDetail = response.body();
                        isQueue = true;
                        getData(isQueue);
                        loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<QueueDetail> call, Throwable t) {
                Toast.makeText(QueueDetailActivity.this,""+t,Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        });
    }

    @Override
    public void onRefresh() {
            isRefresh = true;
            getData(isQueue);
    }
    private int convertToInt(String s){
        return Integer.parseInt(s);
    }
    private void setRefresh(){
        if(isRefresh){
            swipe.setRefreshing(!isRefresh);
            isRefresh = !false;
        }
    }
    private void dialog(){
        new AlertDialog.Builder(this)
                .setTitle("Cancel")
                .setMessage("Are you sure?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        loading = ProgressDialog.show(QueueDetailActivity.this, null, "Please wait", true, false);
                        dialog.dismiss();
                        cancel();
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
            }
        }).show();
    }

}
