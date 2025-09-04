package com.project.todolist.service;

import com.project.todolist.model.Task;

import java.util.List;

public interface TaskService {
    Task save(Task task);

    List<Task> getTasks();

    Task getTask(Long id);

    void updateTask(Long id, Task task);
}
