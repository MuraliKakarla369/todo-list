package com.project.todolist.controller;

import com.project.todolist.model.Task;
import com.project.todolist.model.TaskRequest;
import com.project.todolist.service.TaskService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@OpenAPIDefinition(info = @Info(title = "Todo list application",
        description = "A simple todo list app for keeping track of everyday tasks on memory",
        version = "1.0"))
@Tag(name = "todo-list")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @Operation(
            summary = "Create a new task",
            description = "Creates a new task in the system with the given title, description, and status."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Successfully created an entry",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Task.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskRequest taskRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.save(taskRequest));
    }

    @Operation(
            summary = "get all tasks",
            description = "fetches all task from the system."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully fetched all entries",
                    content = @Content(mediaType = "application/json")),
    })
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getTasks();
    }

    @Operation(
            summary = "get a task",
            description = "fetches a task from the system with the given id."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully fetched an entry",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "No resource found")
    })
    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @Operation(
            summary = "update a task",
            description = "updates a task from the system with the given id.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody
                    (
                            description = "task with updated info(title, description, completed).",
                            content = @Content(schema = @Schema(implementation = TaskRequest.class))
                    )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully updated an entry",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "No resource found")
    })
    @PutMapping("/{id}")
    public void updateTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        taskService.updateTaskById(id, taskRequest);
    }

    @Operation(
            summary = "delete a task",
            description = "deletes a task from the system with the given id."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully deleted an entry",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "No resource found")
    })
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTaskById(id);
    }

}
