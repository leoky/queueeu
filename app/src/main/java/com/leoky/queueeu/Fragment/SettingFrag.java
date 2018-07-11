package com.leoky.queueeu.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leoky.queueeu.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFrag extends Fragment {


    public static SettingFrag newInstance() {
        // Required empty public constructor

        Bundle args = new Bundle();

        SettingFrag fragment = new SettingFrag();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

}
