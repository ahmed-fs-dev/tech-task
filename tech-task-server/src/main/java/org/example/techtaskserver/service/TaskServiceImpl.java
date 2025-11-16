package org.example.techtaskserver.service;

import lombok.RequiredArgsConstructor;
import org.example.techtaskserver.dto.TaskGetDto;
import org.example.techtaskserver.dto.TaskInsertDto;
import org.example.techtaskserver.dto.TaskListGetDto;
import org.example.techtaskserver.dto.TaskUpdateDto;
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

    public TaskGetDto getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(task -> modelMapper.map(task, TaskGetDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("No task found with id: " + id));
    }

    public void deleteTaskById(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("No task found with id: " + id);
        }
        taskRepository.deleteById(id);
    }

    public TaskListGetDto listTasks() {
        var tasks = taskRepository.findAll()
                .stream()
                .map(task -> modelMapper.map(task, TaskGetDto.class))
                .toList();
        return TaskListGetDto.builder()
                .tasks(tasks)
                .build();

    }

    public TaskGetDto createTask(TaskInsertDto task) {
        Task newTask = modelMapper.map(task, Task.class);
        Task savedTask = taskRepository.save(newTask);
        return modelMapper.map(savedTask, TaskGetDto.class);
    }

    public TaskGetDto updateTask(Long id, TaskUpdateDto task) {
        Task updatedTask = modelMapper.map(task, Task.class);
        updatedTask.setId(id);
        Task savedTask = taskRepository.save(updatedTask);
        return modelMapper.map(savedTask, TaskGetDto.class);
    }
}
