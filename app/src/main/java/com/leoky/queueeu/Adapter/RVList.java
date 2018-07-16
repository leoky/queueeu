package com.leoky.queueeu.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leoky.queueeu.R;

import java.util.List;

public class RVList {
    private ClickListener clickListener = null;
    private Activity activity;

    public interface ClickListener{
        void itemClicked(View view, int position);
    }

    public void setClickListener(ClickListener clicklistener) {
        this.clickListener = clicklistener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tvName,tvNumb,tvNote;
        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            tvName = (TextView)v.findViewById(R.id.tvName);
//            tvNote = (TextView)v.findViewById(R.id.tvNote);
//            tvNumb = (TextView)v.findViewById(R.id.tvNumber);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) {
                clickListener.itemClicked(view, getAdapterPosition());
            }
        }
    }
}
