package com.example.tasklist.util;

import com.example.tasklist.entity.Task;

public class ValidateTask {
    private Task task;

    public ValidateTask(Task task) {
        this.task = task;
    }

    public boolean isValid() {
        if (task == null)
            return false;

        if(task.getTitulo() == null || task.getTitulo().equalsIgnoreCase(""))
            return false;

        if (task.getStatus() == null || task.getStatus().toString().equals(""))
            return false;

        return true;
    }
}
