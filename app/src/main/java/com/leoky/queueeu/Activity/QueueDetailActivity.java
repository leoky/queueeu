package com.leoky.queueeu.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.leoky.queueeu.R;

public class QueueDetailActivity extends AppCompatActivity {

    public static String KEY_NAME = "KEY_NAME";
    Button queuee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra(KEY_NAME));

        queuee=findViewById(R.id.btn_queuee);

        queuee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(QueueDetailActivity.this,OnProgress.class);
                startActivity(i);
                finish();
            }
        });

    }
}
