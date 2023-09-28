package com.pucmm.tarea_2;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Option2Fragment extends Fragment {

    private static ArrayList<TaskRecycler> taskRecyclerArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_option2, container, false);

        taskRecyclerArrayList = TaskRecycler.getTaskRecyclers();

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerAdapter = new RecyclerAdapter(getContext(), taskRecyclerArrayList);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        View constraintLayoutNewTask = view.findViewById(R.id.constraintLayoutForNewTask);
        constraintLayoutNewTask.setVisibility(View.GONE);

        Button button = view.findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                constraintLayoutNewTask.setVisibility(View.VISIBLE);
            }
        });

        EditText editText = view.findViewById(R.id.editTextText);

        Button button3 = view.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                constraintLayoutNewTask.setVisibility(View.GONE);

                editText.setText("");
            }
        });

        Button button4 = view.findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Matter can't be empty", Toast.LENGTH_SHORT).show();
                } else {
                    int image = R.mipmap.cat2;

                    TaskRecycler taskRecycler = new TaskRecycler(editText.getText().toString(), false, image);
                    taskRecyclerArrayList.add(taskRecycler);

                    editText.setText("");

                    button.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.VISIBLE);
                    constraintLayoutNewTask.setVisibility(View.GONE);
                }
            }
        });

        return view;
    }
}