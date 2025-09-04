package com.project.todolist.model;

import lombok.NonNull;

public class UpdateTaskRequest {
    @NonNull
    private Task task;

    public @NonNull Task getTask() {
        return task;
    }

    public void setTask(@NonNull Task task) {
        this.task = task;
    }
}
