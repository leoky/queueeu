package com.leoky.queueeu.Activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.leoky.queueeu.Fragment.HistoryFrag;
import com.leoky.queueeu.Fragment.HomeFrag;
import com.leoky.queueeu.Fragment.ListFrag;
import com.leoky.queueeu.R;
import com.leoky.queueeu.Fragment.SettingFrag;
import com.leoky.queueeu.helper.BottomNavigationViewHelper;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.nav);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, new HomeFrag(),HomeFrag.class.getSimpleName()).commit();
        }

        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment frag = null;
                switch (item.getItemId()){
                    case R.id.nav_1:
                        frag = HomeFrag.newInstance();
                        break;
                    case R.id.nav_2:
                        frag = ListFrag.newInstance();
                        break;
                    case R.id.nav_3:
                        frag = HistoryFrag.newInstance();
                        break;
                    case R.id.nav_4:
                        frag = SettingFrag.newInstance();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,frag).commit();
                return true;
            }
        });
    }
}
