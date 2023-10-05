package com.pucmm.tarea_3;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(tableName = "tasks")
public class Task {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String matter;
    private boolean completed;

    public Task(String matter, boolean completed) {
        this.matter = matter;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatter() {
        return matter;
    }

    public void setMatter(String matter) {
        this.matter = matter;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public static ArrayList<Task> generateTasks() {
        ArrayList<Task> tasks = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Task task = new Task("Dummy Task - " + i, i % 2 == 0);
            tasks.add(task);
        }

        return tasks;
    }
}
