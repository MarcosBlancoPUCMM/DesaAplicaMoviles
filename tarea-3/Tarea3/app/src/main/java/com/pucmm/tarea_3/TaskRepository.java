package com.pucmm.tarea_3;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    private TaskDao taskDao;
    private LiveData<List<Task>> tasks;

    TaskRepository(Application application) {
        Database database = Database.getInstance(application);
        taskDao = database.taskDao();
        tasks = taskDao.getTasks();
    }

    LiveData<List<Task>> getTasks() {
        return tasks;
    }

    void insert(Task task) {
        Database.databaseWriteExecutor.execute(() -> {
            taskDao.insert(task);
        });
    }

    void update(Task task) {
        Database.databaseWriteExecutor.execute(() -> {
            taskDao.update(task);
        });
    }

    void delete(Task task) {
        Database.databaseWriteExecutor.execute(() -> {
            taskDao.delete(task);
        });
    }
}
