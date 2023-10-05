package com.pucmm.tarea_3;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<Task> tasks;
    TaskViewModel taskViewModel;

    public RecyclerViewAdapter(Context context, ArrayList<Task> tasks, TaskViewModel taskViewModel) {
        this.context = context;
        this.tasks = tasks;
        this.taskViewModel = taskViewModel;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item, parent, false);

        return new RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        Task task = tasks.get(position);

        holder.checkBox.setText(task.getMatter());
        holder.checkBox.setOnCheckedChangeListener(null);

        if (task.isCompleted()) {
            setPaintFlags(holder.cardView, holder.checkBox, true);
        } else {
            setPaintFlags(holder.cardView, holder.checkBox, false);
        }

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                task.setCompleted(b);
                taskViewModel.update(task);
                setPaintFlags(holder.cardView, holder.checkBox, b);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void updateTasks(ArrayList<Task> updatedTasks) {
        tasks = updatedTasks;
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        CheckBox checkBox;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardView);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }

    public void setPaintFlags(CardView cardView, CheckBox checkBox, boolean completed) {
        int color;

        if (completed) {
            checkBox.setChecked(true);
            checkBox.setPaintFlags(checkBox.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            color = ContextCompat.getColor(context, R.color.green);
        } else {
            checkBox.setChecked(false);
            checkBox.setPaintFlags(0);
            color = ContextCompat.getColor(context, R.color.red);
        }

        cardView.setBackgroundTintList(ColorStateList.valueOf(color));
    }
}
