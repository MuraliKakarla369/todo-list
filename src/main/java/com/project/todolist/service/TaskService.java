package com.project.todolist.service;

import com.project.todolist.model.Task;

import java.util.List;

public interface TaskService {
    Task save(Task task);

    List<Task> getTasks();

    Task getTaskById(Long id);

    void updateTaskById(Long id, Task task);

    void deleteTaskById(Long id);
}
