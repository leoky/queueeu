package com.leoky.queueeu.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.leoky.queueeu.Activity.MainActivity;
import com.leoky.queueeu.Activity.QueueDetailActivity;
import com.leoky.queueeu.Adapter.RVOrder;
import com.leoky.queueeu.Api.model.Queue;
import com.leoky.queueeu.Api.model.RepoQueue;
import com.leoky.queueeu.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFrag extends Fragment implements RVOrder.ClickListener{
    private RecyclerView rv;
    private ProgressBar pb;

    private RVOrder myAdapter;
    List<Queue> lists;

    public static ListFrag newInstance() {
        // Required empty public constructor

        Bundle args = new Bundle();

        ListFrag fragment = new ListFrag();
        fragment.setArguments(args);
        return fragment;
    }
    private void initId(View v){
        rv = v.findViewById(R.id.rvNow);
        pb = v.findViewById(R.id.pb);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_list, container, false);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Order");
        setHasOptionsMenu(true);
        initId(v);
        pb.setVisibility(View.VISIBLE);
        getData();
        return v;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.ab_history,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== R.id.history){
            Toast.makeText(getContext(),"HIST",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
    private void initRecyclerView(){
        //recycle view
        rv.setHasFixedSize(true);
//        rv.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        //use linear layout manager
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        // adapter
        myAdapter = new RVOrder(lists,getActivity());
        rv.setAdapter(myAdapter);
        myAdapter.setClickListener((RVOrder.ClickListener) this);
    }
    @Override
    public void itemClicked(View view, int position) {
        Intent i = new Intent(getContext(), QueueDetailActivity.class);
        i.putExtra(QueueDetailActivity.KEY_NAME,lists.get(position).getDoctor().getName());
        i.putExtra(QueueDetailActivity.KEY_DOCTOR_ID,lists.get(position).getDoctor().getId());
        i.putExtra(QueueDetailActivity.KEY_QUEUE_ID,lists.get(position).get_id());
        i.putExtra(QueueDetailActivity.KEY_MODE,QueueDetailActivity.MODE_ON_QUEUE);
        startActivity(i);
    }
    private void getData(){
        Call<RepoQueue> call = MainActivity.userService.getQueue(MainActivity.sp.getSpId());
        call.enqueue(new Callback<RepoQueue>() {
            @Override
            public void onResponse(Call<RepoQueue> call, Response<RepoQueue> response) {
                RepoQueue u  = response.body();
                lists = u.getQueue();
                if(lists!=null ){
                    initRecyclerView();
                }
                pb.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<RepoQueue> call, Throwable t) {
                Toast.makeText(getContext(),""+t,Toast.LENGTH_SHORT).show();
                pb.setVisibility(View.GONE);
            }
        });
    }
}
