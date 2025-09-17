package com.project.todolist.controller;

import com.project.todolist.model.TaskRequest;
import com.project.todolist.model.Task;
import com.project.todolist.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskRequest taskRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.save(taskRequest));
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getTasks();
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("/{id}")
    public void updateTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        taskService.updateTaskById(id, taskRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTaskById(id);
    }

}
