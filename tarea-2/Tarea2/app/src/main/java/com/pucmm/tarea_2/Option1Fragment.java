package com.pucmm.tarea_2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class Option1Fragment extends Fragment {

    private static ArrayList<TaskList> taskListArrayList = TaskList.getTaskLists();
    ViewListAdapter viewListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_option1, container, false);

        viewListAdapter = new ViewListAdapter(getContext(), taskListArrayList);

        ListView listView = view.findViewById(R.id.listView);
        listView.setAdapter(viewListAdapter);

        View constraintLayoutNewTask = view.findViewById(R.id.constraintLayoutForNewTask);
        constraintLayoutNewTask.setVisibility(View.GONE);

        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setVisibility(View.GONE);
                listView.setVisibility(View.GONE);
                constraintLayoutNewTask.setVisibility(View.VISIBLE);
            }
        });

        EditText editText = view.findViewById(R.id.editTextText);

        Button button3 = view.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setVisibility(View.VISIBLE);
                listView.setVisibility(View.VISIBLE);
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
                    TaskList taskList = new TaskList(editText.getText().toString(), false);
                    taskListArrayList.add(taskList);
                    viewListAdapter.notifyDataSetChanged();

                    editText.setText("");

                    button.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.VISIBLE);
                    constraintLayoutNewTask.setVisibility(View.GONE);
                }
            }
        });

        return view;
    }

    public void onResume() {
        super.onResume();

        if (viewListAdapter != null) {
            viewListAdapter.notifyDataSetChanged();
        }
    }
}