package org.example.techtaskserver.service;

import org.example.techtaskserver.dto.TaskGet;
import org.example.techtaskserver.dto.TaskInsert;
import org.example.techtaskserver.dto.TaskListGet;
import org.example.techtaskserver.dto.TaskUpdate;
import org.example.techtaskserver.exception.ResourceNotFoundException;
import org.example.techtaskserver.model.Task;
import org.example.techtaskserver.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {
    private TaskRepository taskRepository;
    private ModelMapper modelMapper;
    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp() {
        taskRepository = mock(TaskRepository.class);
        modelMapper = new ModelMapper();
        taskService = new TaskServiceImpl(taskRepository, modelMapper);
    }

    @Test
    void testGetTaskById_Success() {
        Task task = Task.builder().id(1L).title("Test").description("Desc").completed(false).build();
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        TaskGet expected = modelMapper.map(task, TaskGet.class);
        TaskGet result = taskService.getTaskById(1L);
        assertEquals(expected, result);
    }

    @Test
    void testGetTaskById_NotFound() {
        when(taskRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> taskService.getTaskById(2L));
    }

    @Test
    void testDeleteTaskById_Success() {
        when(taskRepository.existsById(1L)).thenReturn(true);
        doNothing().when(taskRepository).deleteById(1L);
        assertDoesNotThrow(() -> taskService.deleteTaskById(1L));
        verify(taskRepository).deleteById(1L);
    }

    @Test
    void testDeleteTaskById_NotFound() {
        when(taskRepository.existsById(2L)).thenReturn(false);
        assertThrows(ResourceNotFoundException.class, () -> taskService.deleteTaskById(2L));
    }

    @Test
    void testListTasks() {
        Task task = Task.builder().id(1L).title("Test").description("Desc").completed(false).build();
        when(taskRepository.findAll()).thenReturn(List.of(task));
        TaskGet taskGet = modelMapper.map(task, TaskGet.class);
        TaskListGet result = taskService.listTasks();
        assertEquals(1, result.getTasks().size());
        assertEquals(taskGet, result.getTasks().get(0));
    }

    @Test
    void testCreateTask() {
        TaskInsert insert = TaskInsert.builder().title("New").description("Desc").completed(false).build();
        Task savedTask = Task.builder().id(1L).title("New").description("Desc").completed(false).build();
        when(taskRepository.save(any(Task.class))).thenReturn(savedTask);
        TaskGet expected = modelMapper.map(savedTask, TaskGet.class);
        TaskGet result = taskService.createTask(insert);
        assertEquals(expected, result);
    }

    @Test
    void testUpdateTask() {
        TaskUpdate update = TaskUpdate.builder().title("Updated").description("Desc").completed(true).build();
        Task updatedTask = modelMapper.map(update, Task.class);
        updatedTask.setId(1L);
        Task savedTask = Task.builder().id(1L).title("Updated").description("Desc").completed(true).build();
        when(taskRepository.save(any(Task.class))).thenReturn(savedTask);
        TaskGet expected = modelMapper.map(savedTask, TaskGet.class);
        TaskGet result = taskService.updateTask(1L, update);
        assertEquals(expected, result);
    }
}

