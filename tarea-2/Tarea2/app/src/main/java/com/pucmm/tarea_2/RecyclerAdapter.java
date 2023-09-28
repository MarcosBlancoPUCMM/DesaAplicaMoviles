package com.pucmm.tarea_2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    Context context;
    ArrayList<TaskRecycler> taskRecyclerArrayList;

    public RecyclerAdapter(Context context, ArrayList<TaskRecycler> taskRecyclerArrayList) {
        this.context = context;
        this.taskRecyclerArrayList = taskRecyclerArrayList;
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item_option2, parent, false);

        return new RecyclerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        TaskRecycler taskRecycler = taskRecyclerArrayList.get(position);

        holder.checkBox.setText(taskRecycler.matter);
        holder.imageView.setImageResource(taskRecycler.image);

        setPaintFlags(holder.checkBox, taskRecycler.completed, holder.cardView, context);

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                taskRecycler.completed = !taskRecycler.completed;
                setPaintFlags(holder.checkBox, taskRecycler.completed, holder.cardView, context);
            }
        });

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);
                builder.setTitle("Remove Task?");
                builder.setMessage(taskRecycler.matter);
                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int adapterPosition = holder.getAdapterPosition();
                        if (adapterPosition != RecyclerView.NO_POSITION) {
                            taskRecyclerArrayList.remove(adapterPosition);
                            notifyItemRemoved(adapterPosition);
                        }
                    }
                });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskRecyclerArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        CheckBox checkBox;
        ImageView imageView;
        CardView cardView;
        Button button;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            checkBox = itemView.findViewById(R.id.checkBox2);
            imageView = itemView.findViewById(R.id.imageView);
            cardView = itemView.findViewById(R.id.cardView2);
            button = itemView.findViewById(R.id.button6);
        }
    }

    public void setPaintFlags(CheckBox checkBox, boolean completed, CardView cardView, Context context) {
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

        ColorStateList colorStateList = ColorStateList.valueOf(color);
        cardView.setBackgroundTintList(colorStateList);
    }
}
