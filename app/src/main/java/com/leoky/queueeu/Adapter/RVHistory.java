package com.leoky.queueeu.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leoky.queueeu.Api.model.Queue;
import com.leoky.queueeu.R;

import java.util.List;

public class RVHistory extends RecyclerView.Adapter<RVHistory.ViewHolder> {
    private ClickListener clickListener = null;
    private Activity activity;
    private List<Queue> list;

    public RVHistory(List<Queue> list, Activity activity){
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_history,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(list.get(position).getDoctor().getName());
        holder.tvDate.setText(list.get(position).getQueue_at());
        holder.tvStatus.setText(list.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface ClickListener{
        void itemClicked(View view, int position);
    }

    public void setClickListener(ClickListener clicklistener) {
        this.clickListener = clicklistener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tvName,tvStatus,tvDate;
        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            tvName = v.findViewById(R.id.tvName);
            tvStatus = v.findViewById(R.id.tvStatus);
            tvDate = v.findViewById(R.id.tvDate);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) {
                clickListener.itemClicked(view, getAdapterPosition());
            }
        }
    }
}
