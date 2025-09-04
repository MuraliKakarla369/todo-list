package com.project.todolist.service.impl;

import com.project.todolist.model.Task;
import com.project.todolist.repository.TaskRepository;
import com.project.todolist.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTask(Long id) {
        return checkIfExists(id);
    }

    private Task checkIfExists(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "order with " + id + " is not found.");
        }
        return optionalTask.get();
    }

    @Override
    public void updateTask(Long id, Task task) {
        Task existingTask = checkIfExists(id);
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setCompleted(task.isCompleted());
        existingTask.setUpdatedAt(LocalDateTime.now());
        taskRepository.save(existingTask);
    }
}
