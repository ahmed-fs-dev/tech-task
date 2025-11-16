package org.example.techtaskserver.controller;

import org.example.techtaskserver.dto.TaskGetDto;
import org.example.techtaskserver.dto.TaskInsertDto;
import org.example.techtaskserver.dto.TaskListGetDto;
import org.example.techtaskserver.dto.TaskUpdateDto;
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
        TaskListGetDto listGet = TaskListGetDto.builder().tasks(Collections.emptyList()).build();
        when(taskService.listTasks()).thenReturn(listGet);
        ResponseEntity<TaskListGetDto> response = taskController.listTasks();
        assertEquals(200, response.getStatusCode().value());
        assertEquals(listGet, response.getBody());
    }

    @Test
    void testGetTaskById() {
        TaskGetDto taskGetDto = TaskGetDto.builder().id(1L).title("A").build();
        when(taskService.getTaskById(1L)).thenReturn(taskGetDto);
        ResponseEntity<TaskGetDto> response = taskController.getTaskById(1L);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(taskGetDto, response.getBody());
    }

    @Test
    void testCreateTask() {
        TaskInsertDto insert = TaskInsertDto.builder().title("A").build();
        TaskGetDto taskGetDto = TaskGetDto.builder().id(1L).title("A").build();
        when(taskService.createTask(insert)).thenReturn(taskGetDto);
        ResponseEntity<TaskGetDto> response = taskController.createTask(insert);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(taskGetDto, response.getBody());
    }

    @Test
    void testUpdateTask() {
        TaskUpdateDto update = TaskUpdateDto.builder().title("Updated").build();
        TaskGetDto taskGetDto = TaskGetDto.builder().id(1L).title("Updated").build();
        when(taskService.updateTask(1L, update)).thenReturn(taskGetDto);
        ResponseEntity<TaskGetDto> response = taskController.updateTask(1L, update);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(taskGetDto, response.getBody());
    }

    @Test
    void testDeleteTaskById() {
        doNothing().when(taskService).deleteTaskById(1L);
        ResponseEntity<Void> response = taskController.deleteTaskById(1L);
        assertEquals(204, response.getStatusCode().value());
        assertNull(response.getBody());
    }
}
