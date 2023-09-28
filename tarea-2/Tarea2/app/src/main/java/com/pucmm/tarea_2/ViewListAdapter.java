package com.pucmm.tarea_2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class ViewListAdapter extends ArrayAdapter<TaskList> {

    public ViewListAdapter(Context context, ArrayList<TaskList> taskListArrayList) {
        super(context, R.layout.list_item_option1, taskListArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_option1, parent, false);
        }

        TaskList taskList = getItem(position);

        CheckBox checkBox = convertView.findViewById(R.id.checkBox);
        checkBox.setText(taskList.matter);

        CardView cardView = convertView.findViewById(R.id.cardView);

        setPaintFlags(checkBox, taskList.completed, cardView);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                taskList.completed = !taskList.completed;

                setPaintFlags(checkBox, taskList.completed, cardView);
            }
        });

        Button button2 = convertView.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCancelable(true);
                builder.setTitle("Remove Task?");
                builder.setMessage(taskList.matter);
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                TaskList taskList = getItem(position);
                                remove(taskList);

                                checkBox.setChecked(false);

                                notifyDataSetChanged();
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

        return convertView;
    }

    public void setPaintFlags(CheckBox checkBox, boolean completed, CardView cardView) {
        int color;

        if (completed) {
            checkBox.setChecked(true);
            checkBox.setPaintFlags(checkBox.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            color = ContextCompat.getColor(getContext(), R.color.green);
        } else {
            checkBox.setChecked(false);
            checkBox.setPaintFlags(0);
            color = ContextCompat.getColor(getContext(), R.color.red);
        }

        ColorStateList colorStateList = ColorStateList.valueOf(color);
        cardView.setBackgroundTintList(colorStateList);
    }
}
