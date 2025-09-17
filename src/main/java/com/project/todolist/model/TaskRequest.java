package com.project.todolist.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NonNull;

@Data
public class TaskRequest {

    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    @NonNull
    private boolean completed;
}
