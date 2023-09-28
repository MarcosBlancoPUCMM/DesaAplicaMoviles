package com.pucmm.tarea_2;

import java.util.ArrayList;

public class TaskRecycler extends TaskList {

    int image;

    public TaskRecycler(String matter, Boolean completed, int image) {
        super(matter, completed);
        this.image = image;
    }

    public static ArrayList<TaskRecycler> getTaskRecyclers() {
        ArrayList<TaskRecycler> taskRecyclerArrayList =  new ArrayList<>();

        int image = R.mipmap.cat;

        for (int i = 0; i < 3; i++) {
            TaskRecycler taskRecycler;

            if (i % 2 == 0) {
                taskRecycler = new TaskRecycler("Dummy Text - " + i, false, image);
            } else {
                taskRecycler = new TaskRecycler("Dummy Text - " + i, true, image);
            }

            taskRecyclerArrayList.add(taskRecycler);
        }

        return taskRecyclerArrayList;
    }
}
