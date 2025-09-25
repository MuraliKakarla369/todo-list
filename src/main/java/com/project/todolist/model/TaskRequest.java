package com.project.todolist.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class TaskRequest {

    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    private boolean completed;
}
