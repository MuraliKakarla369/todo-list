package com.project.todolist.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;

public class CreateTaskRequest {

    @JsonProperty("task")
    @NonNull
    private Task task;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
