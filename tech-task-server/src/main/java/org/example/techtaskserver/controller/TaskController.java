package org.example.techtaskserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.techtaskserver.dto.TaskGet;
import org.example.techtaskserver.dto.TaskInsert;
import org.example.techtaskserver.dto.TaskListGet;
import org.example.techtaskserver.dto.TaskUpdate;
import org.example.techtaskserver.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/{id}")
    public ResponseEntity<TaskGet> getTaskById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable("id") Long id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<TaskListGet> listTasks() {
        return ResponseEntity.ok(taskService.listTasks());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskGet> updateTask(@PathVariable Long id, @RequestBody TaskUpdate task) {
        return ResponseEntity.ok(taskService.updateTask(id, task));
    }

    @PostMapping
    public ResponseEntity<TaskGet> createTask(@RequestBody TaskInsert task) {
        return ResponseEntity.status(201).body(taskService.createTask(task));
    }
}
