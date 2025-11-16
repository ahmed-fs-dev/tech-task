package org.example.techtaskserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.techtaskserver.dto.TaskGet;
import org.example.techtaskserver.dto.TaskInsert;
import org.example.techtaskserver.dto.TaskListGet;
import org.example.techtaskserver.dto.TaskUpdate;
import org.example.techtaskserver.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
@Tag(name = "Tasks", description = "Operations related to tasks")
public class TaskController {

    private final TaskService taskService;

    @Operation(summary = "Get task by id", description = "Returns a single task by its id")
    @ApiResponse(responseCode = "200", description = "Task found")
    @ApiResponse(responseCode = "404", description = "Task not found")
    @GetMapping("/{id}")
    public ResponseEntity<TaskGet> getTaskById(@Parameter(description = "Id of task to retrieve") @PathVariable("id") Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @Operation(summary = "Delete task", description = "Deletes a task by id")
    @ApiResponse(responseCode = "204", description = "Task deleted")
    @ApiResponse(responseCode = "404", description = "Task not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@Parameter(description = "Id of task to delete") @PathVariable("id") Long id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "List tasks", description = "Returns a list of tasks")
    @ApiResponse(responseCode = "200", description = "List returned")
    @GetMapping
    public ResponseEntity<TaskListGet> listTasks() {
        return ResponseEntity.ok(taskService.listTasks());
    }

    @Operation(summary = "Update task", description = "Updates an existing task")
    @ApiResponse(responseCode = "200", description = "Task updated")
    @ApiResponse(responseCode = "404", description = "Task not found")
    @PutMapping("/{id}")
    public ResponseEntity<TaskGet> updateTask(@Parameter(description = "Id of task to update") @PathVariable Long id,
                                              @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Task payload to update") @Validated @RequestBody TaskUpdate task) {
        return ResponseEntity.ok(taskService.updateTask(id, task));
    }

    @Operation(summary = "Create task", description = "Creates a new task")
    @ApiResponse(responseCode = "201", description = "Task created")
    @PostMapping
    public ResponseEntity<TaskGet> createTask(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Task payload to create") @Validated @RequestBody TaskInsert task) {
        return ResponseEntity.status(201).body(taskService.createTask(task));
    }
}
