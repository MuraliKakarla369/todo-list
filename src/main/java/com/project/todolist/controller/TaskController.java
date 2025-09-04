package com.project.todolist.controller;

import com.project.todolist.model.CreateTaskRequest;
import com.project.todolist.model.Task;
import com.project.todolist.repository.TaskRepository;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/tasks")
public class TaskController {
    private TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody @NonNull CreateTaskRequest createTaskRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskRepository.save(createTaskRequest.getTask()));
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            return ResponseEntity.ok(taskOptional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("resource with id " + id + " not found");
    }

}
