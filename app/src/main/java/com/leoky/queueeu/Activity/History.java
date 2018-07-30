package com.leoky.queueeu.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.leoky.queueeu.Adapter.RVHistory;
import com.leoky.queueeu.Adapter.RVOrder;
import com.leoky.queueeu.Api.model.Queue;
import com.leoky.queueeu.Api.model.RepoQueue;
import com.leoky.queueeu.R;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class History extends AppCompatActivity {

    private RecyclerView history;
    private RVHistory myAdapter;
    List<Queue> lists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getSupportActionBar().setTitle("History");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        history = findViewById(R.id.rvHistory);
        getData();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void initRecyclerView(){
        //recycle view
        history.setHasFixedSize(true);
//        rv.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        //use linear layout manager
        history.setLayoutManager(new LinearLayoutManager(History.this));

        // adapter
        myAdapter = new RVHistory(lists,History.this);
        history.setAdapter(myAdapter);

    }

    private void getData(){
        Call<RepoQueue> call = MainActivity.userService.getHistory(MainActivity.sp.getSpId());
        call.enqueue(new Callback<RepoQueue>() {
            @Override
            public void onResponse(Call<RepoQueue> call, Response<RepoQueue> response) {
                RepoQueue u  = response.body();
                lists = u.getQueue();
                if(lists!=null ){
                    initRecyclerView();
                }
            }

            @Override
            public void onFailure(Call<RepoQueue> call, Throwable t) {
                Toast.makeText(History.this,""+t,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
