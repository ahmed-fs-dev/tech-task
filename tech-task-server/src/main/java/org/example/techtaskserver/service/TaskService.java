package org.example.techtaskserver.service;

import org.example.techtaskserver.dto.TaskGetDto;
import org.example.techtaskserver.dto.TaskInsertDto;
import org.example.techtaskserver.dto.TaskListGetDto;
import org.example.techtaskserver.dto.TaskUpdateDto;

public interface TaskService {
    public void deleteTaskById(Long id);
    public TaskGetDto getTaskById(Long id);
    public TaskListGetDto listTasks();
    public TaskGetDto createTask(TaskInsertDto task);
    public TaskGetDto updateTask(Long id, TaskUpdateDto task);
}
