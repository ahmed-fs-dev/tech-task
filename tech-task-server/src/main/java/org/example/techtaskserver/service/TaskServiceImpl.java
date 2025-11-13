package org.example.techtaskserver.service;

import lombok.RequiredArgsConstructor;
import org.example.techtaskserver.dto.TaskGet;
import org.example.techtaskserver.dto.TaskInsert;
import org.example.techtaskserver.dto.TaskListGet;
import org.example.techtaskserver.dto.TaskUpdate;
import org.example.techtaskserver.exception.ResourceNotFoundException;
import org.example.techtaskserver.model.Task;
import org.example.techtaskserver.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    public TaskGet getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(task -> modelMapper.map(task, TaskGet.class))
                .orElseThrow(() -> new ResourceNotFoundException("No task found with id: " + id));
    }

    public void deleteTaskById(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("No task found with id: " + id);
        }
        taskRepository.deleteById(id);
    }

    public TaskListGet listTasks() {
        var tasks = taskRepository.findAll()
                .stream()
                .map(task -> modelMapper.map(task, TaskGet.class))
                .toList();
        return TaskListGet.builder()
                .tasks(tasks)
                .build();

    }

    public TaskGet createTask(TaskInsert task) {
        Task newTask = modelMapper.map(task, Task.class);
        Task savedTask = taskRepository.save(newTask);
        return modelMapper.map(savedTask, TaskGet.class);
    }

    public TaskGet updateTask(Long id, TaskUpdate task) {
        Task updatedTask = modelMapper.map(task, Task.class);
        updatedTask.setId(id);
        Task savedTask = taskRepository.save(updatedTask);
        return modelMapper.map(savedTask, TaskGet.class);
    }
}
