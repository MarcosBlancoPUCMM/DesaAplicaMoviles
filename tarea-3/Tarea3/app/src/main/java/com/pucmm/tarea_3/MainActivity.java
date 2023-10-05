package com.pucmm.tarea_3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<Task> tasks = new ArrayList<>();
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView recyclerView;
    ConstraintLayout constraintLayout;
    TaskViewModel taskViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("To Do List");

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, tasks, taskViewModel);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        LiveData<List<Task>> taskListLiveData = taskViewModel.getTasks();
        taskListLiveData.observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                recyclerViewAdapter.updateTasks((ArrayList<Task>) tasks);
            }
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setTitle("New Task");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.custom_dialog);

                Button button1 = dialog.findViewById(R.id.button2);
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditText editText = dialog.findViewById(R.id.editTextText);

                        if (editText.getText().toString().isEmpty()) {
                            Toast.makeText(MainActivity.this, "Matter can't be empty", Toast.LENGTH_SHORT).show();
                        } else {
                            Task task = new Task(editText.getText().toString(), false);
                            taskViewModel.insert(task);

                            dialog.dismiss();

                            Toast.makeText(MainActivity.this, "New Task Added", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                dialog.show();
            }
        });

        constraintLayout = findViewById(R.id.constraintLayout);

        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);
    }

    ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            Task task = recyclerViewAdapter.tasks.get(viewHolder.getAdapterPosition());

            taskViewModel.delete(task);
            Toast.makeText(MainActivity.this, "Task Removed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

            if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                View itemView = viewHolder.itemView;

                int color = ContextCompat.getColor(MainActivity.this, R.color.red);

                Paint paint = new Paint();
                paint.setColor(color);

                RectF background;

                int margin = 16;

                if (dX > 0) {
                    background = new RectF(
                            itemView.getLeft(),
                            itemView.getTop() + margin,
                            itemView.getLeft() + dX,
                            itemView.getBottom() - margin
                    );
                } else {
                    background = new RectF(
                            itemView.getRight() + dX,
                            itemView.getTop() + margin,
                            itemView.getRight(),
                            itemView.getBottom() - margin
                    );
                }

                c.drawRect(background, paint);
            }

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };
}