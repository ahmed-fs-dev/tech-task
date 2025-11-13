package org.example.techtaskserver.service;

import org.example.techtaskserver.dto.TaskGet;
import org.example.techtaskserver.dto.TaskInsert;
import org.example.techtaskserver.dto.TaskListGet;
import org.example.techtaskserver.dto.TaskUpdate;

public interface TaskService {
    public void deleteTaskById(Long id);
    public TaskGet getTaskById(Long id);
    public TaskListGet listTasks();
    public TaskGet createTask(TaskInsert task);
    public TaskGet updateTask(Long id, TaskUpdate task);
}
