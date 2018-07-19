package com.leoky.queueeu.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.leoky.queueeu.Api.model.Doctor;
import com.leoky.queueeu.R;

import java.util.List;

public class SearchAdapter extends ArrayAdapter<Doctor>{

    public SearchAdapter(Context context, List<Doctor> doctors) {
        super(context,0, doctors);
    }

    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        Doctor doctor = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_search, parent, false);
        }
        TextView tvName = convertView.findViewById(R.id.tvName);
        tvName.setText(doctor.getName());
        return convertView;
    }

}
