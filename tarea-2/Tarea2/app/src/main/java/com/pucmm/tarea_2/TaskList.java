package com.pucmm.tarea_2;

import java.util.ArrayList;
import java.util.List;

import kotlinx.coroutines.scheduling.Task;

public class TaskList {

    String matter;
    Boolean completed;

    public TaskList(String matter, Boolean completed) {
        this.matter = matter;
        this.completed = completed;
    }

    public static ArrayList<TaskList> getTaskLists() {
        ArrayList<TaskList> taskListArrayList =  new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            TaskList taskList;

            if (i % 2 == 0) {
                taskList = new TaskList("Dummy Text - " + i, false);
            } else {
                taskList = new TaskList("Dummy Text - " + i, true);
            }

            taskListArrayList.add(taskList);
        }

        return taskListArrayList;
    }
}
