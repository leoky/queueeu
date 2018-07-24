package com.leoky.queueeu.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.leoky.queueeu.Adapter.SearchAdapter;
import com.leoky.queueeu.Api.model.RepoSearch;
import com.leoky.queueeu.R;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    private ListView lvSearch;
    private ProgressBar pb;
    private TextView tvError;
    private SearchView searchView;

    private SearchAdapter adapter;
    private RepoSearch repoSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lvSearch = findViewById(R.id.lv_search);
        tvError = findViewById(R.id.tvError);
        pb = findViewById(R.id.progressBar);

        lvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                searchView.setFocusable(false);
                Intent i = new Intent(SearchActivity.this,QueueDetailActivity.class);
                i.putExtra(QueueDetailActivity.KEY_NAME,repoSearch.getDoctor().get(position).getName());
                i.putExtra(QueueDetailActivity.KEY_DOCTOR_ID,repoSearch.getDoctor().get(position).getId());
                i.putExtra(QueueDetailActivity.KEY_MODE,QueueDetailActivity.MODE_QUEUE);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ab_search, menu);

        MenuItem item = menu.findItem(R.id.sv);
        searchView = (SearchView) item.getActionView();

        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.setQueryHint("Search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                tvError.setVisibility(View.GONE);
                if(newText.length()>=2){
                    search(newText);
                }else if(newText.length() == 0 && adapter!=null){
                    adapter.clear();
                    adapter.notifyDataSetChanged();
                }
                return true;
            }
        });
        return true;
    }

    private void search(final String newText){

        tvError.setVisibility(View.GONE);
        pb.setVisibility(View.VISIBLE);

        Call<RepoSearch> c = MainActivity.userService.searchName(newText);
        c.enqueue(new Callback<RepoSearch>() {
            @Override
            public void onResponse(Call<RepoSearch> call, Response<RepoSearch> response) {
                repoSearch = response.body();
                if(repoSearch!=null) {
                    if (repoSearch.getError() == null) {
                        adapter = new SearchAdapter(SearchActivity.this, repoSearch.getDoctor());
                        lvSearch.setAdapter(adapter);

                        if(repoSearch.getDoctor().size()==0){
                            tvError.setVisibility(View.VISIBLE);
                        }
                    }
                }
                pb.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<RepoSearch> call, Throwable t) {
                Toast.makeText(SearchActivity.this, " "+t, Toast.LENGTH_SHORT).show();
                pb.setVisibility(View.GONE);
                if(t instanceof SocketTimeoutException){
                    search(newText);
                }

            }
        });
    }
}
