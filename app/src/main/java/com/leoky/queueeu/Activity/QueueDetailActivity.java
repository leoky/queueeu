package com.leoky.queueeu.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.leoky.queueeu.R;

public class QueueDetailActivity extends AppCompatActivity {

    public static String KEY_NAME = "KEY_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra(KEY_NAME));
    }
}
