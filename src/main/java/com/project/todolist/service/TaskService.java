package com.project.todolist.service;

import com.project.todolist.model.TaskRequest;
import com.project.todolist.model.Task;

import java.util.List;

public interface TaskService {
    Task save(TaskRequest taskRequest);

    List<Task> getTasks();

    Task getTaskById(Long id);

    void updateTaskById(Long id, TaskRequest taskRequest);

    void deleteTaskById(Long id);
}
