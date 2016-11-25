package com.example.green.estafeta.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.green.estafeta.ActivityDetails;
import com.example.green.estafeta.R;
import com.example.green.estafeta.classes.Task;

import java.util.List;

/**
 * Created by green on 11/25/16.
 */

public class TasksListAdapter extends RecyclerView.Adapter<TasksListAdapter.ViewHolder> {
    private final static String LOG_TAG = "TasksListAdapter";
    private List<Task> cursor;
    private Context context;

    public TasksListAdapter(List<Task> cursor, Context context){
        this.cursor = cursor;
        this.context = context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView vin;
        public TextView date;
        public TextView model;
        public TextView driverName;
        public TextView modelCode;
        private Context context;

        public ViewHolder(View v, Context context){
            super(v);
            this.context = context;
            v.setOnClickListener(this);
            vin = (TextView)v.findViewById(R.id.item_vin);
            date = (TextView)v.findViewById(R.id.item_date);
            model = (TextView)v.findViewById(R.id.item_model);
            driverName = (TextView)v.findViewById(R.id.item_driver_name);
            modelCode = (TextView)v.findViewById(R.id.item_model_code);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, ActivityDetails.class);
            intent.putExtra("position", getPosition());
            context.startActivity(intent);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tasks_list, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.vin.setText(cursor.get(position).Vin);
        holder.date.setText(cursor.get(position).ActualStartDate);
        holder.model.setText(cursor.get(position).Brand + " " + cursor.get(position).Model);
        holder.driverName.setText(cursor.get(position).Driver);
        holder.modelCode.setText(cursor.get(position).ModelCode);

    }

    @Override
    public int getItemCount() {
        if(cursor == null) {
            return 0;
        } else {
            return cursor.size();
        }
    }

    public void replaceData(List<Task> tasks){
        this.cursor = tasks;
        notifyDataSetChanged();
    }
}
