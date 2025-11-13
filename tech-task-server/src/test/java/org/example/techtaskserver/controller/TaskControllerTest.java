package org.example.techtaskserver.controller;

import org.example.techtaskserver.dto.TaskGet;
import org.example.techtaskserver.dto.TaskInsert;
import org.example.techtaskserver.dto.TaskListGet;
import org.example.techtaskserver.dto.TaskUpdate;
import org.example.techtaskserver.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskControllerTest {
    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    public TaskControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListTasks() {
        TaskListGet listGet = TaskListGet.builder().tasks(Collections.emptyList()).build();
        when(taskService.listTasks()).thenReturn(listGet);
        ResponseEntity<TaskListGet> response = taskController.listTasks();
        assertEquals(200, response.getStatusCode().value());
        assertEquals(listGet, response.getBody());
    }

    @Test
    void testGetTaskById() {
        TaskGet taskGet = TaskGet.builder().id(1L).title("A").build();
        when(taskService.getTaskById(1L)).thenReturn(taskGet);
        ResponseEntity<TaskGet> response = taskController.getTaskById(1L);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(taskGet, response.getBody());
    }

    @Test
    void testCreateTask() {
        TaskInsert insert = TaskInsert.builder().title("A").build();
        TaskGet taskGet = TaskGet.builder().id(1L).title("A").build();
        when(taskService.createTask(insert)).thenReturn(taskGet);
        ResponseEntity<TaskGet> response = taskController.createTask(insert);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(taskGet, response.getBody());
    }

    @Test
    void testUpdateTask() {
        TaskUpdate update = TaskUpdate.builder().title("Updated").build();
        TaskGet taskGet = TaskGet.builder().id(1L).title("Updated").build();
        when(taskService.updateTask(1L, update)).thenReturn(taskGet);
        ResponseEntity<TaskGet> response = taskController.updateTask(1L, update);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(taskGet, response.getBody());
    }

    @Test
    void testDeleteTaskById() {
        doNothing().when(taskService).deleteTaskById(1L);
        ResponseEntity<Void> response = taskController.deleteTaskById(1L);
        assertEquals(204, response.getStatusCode().value());
        assertNull(response.getBody());
    }
}
