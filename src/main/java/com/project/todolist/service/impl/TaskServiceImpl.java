package com.project.todolist.service.impl;

import com.project.todolist.model.TaskRequest;
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
    public Task save(TaskRequest createTaskRequest) {
        Task task = new Task(createTaskRequest.getTitle(), createTaskRequest.getDescription(), createTaskRequest.isCompleted());
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return checkAndGetIfExists(id);
    }

    private Task checkAndGetIfExists(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "task with " + id + " is not found.");
        }
        return optionalTask.get();
    }

    @Override
    public void updateTaskById(Long id, TaskRequest taskRequest) {
        Task existingTask = checkAndGetIfExists(id);
        existingTask.setTitle(taskRequest.getTitle());
        existingTask.setDescription(taskRequest.getDescription());
        existingTask.setCompleted(taskRequest.isCompleted());
        existingTask.setUpdatedAt(LocalDateTime.now());
        taskRepository.save(existingTask);
    }

    @Override
    public void deleteTaskById(Long id) {
        checkAndGetIfExists(id);
        taskRepository.deleteById(id);
    }
}
